package teco.challenge.challengejava.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.cache.CacheCaminos;
import teco.challenge.challengejava.cache.CachePuntoVenta;
import teco.challenge.challengejava.dominio.CalculadoraCostos;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dominio.ResultadoCosto;
import teco.challenge.challengejava.dto.DTOCamino;
import teco.challenge.challengejava.repositorios.RepoCamino;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;

import java.util.List;

@Service
public class ServicioCamino {

    @Autowired
    private RepoCamino repoCamino;


    public List<Camino> getCaminos() {

        return CacheCaminos.getAll();
    }

    public Camino getCamino(Long id) {

        return CacheCaminos.get(id);
    }

    public Camino saveCamino(DTOCamino dtoCamino) {
        List<PuntoDeVenta> cache = CachePuntoVenta.getAll();
        PuntoDeVenta puntoA = cache.stream()
                .filter(p -> p.getId().equals(dtoCamino.getIdPuntoVentaA()))
                .findFirst().orElse(null);

        PuntoDeVenta puntoB = cache.stream()
                .filter(p -> p.getId().equals(dtoCamino.getIdPuntoVentaB()))
                .findFirst().orElse(null);
        if (puntoA == null || puntoB == null) {
            throw new RuntimeException("Punto de venta no encontrado");
        }
        Camino camino = new Camino(puntoA, puntoB, dtoCamino.getCosto());
        Camino savedCamino = repoCamino.save(camino);
        CacheCaminos.put(savedCamino.getId(), savedCamino);
        return savedCamino;
    }

    public void deleteCamino(Long id) {
        Camino camino = repoCamino.findById(id).orElse(null);
        if (camino != null) {
            camino.setBorrado(true);
            repoCamino.save(camino);
            CacheCaminos.remove(id);
        }
    }

    public ResultadoCosto calcularCostoMinimo(Long idPuntoA, Long idPuntoB) {
        List<Camino> caminos = CacheCaminos.getAll();
        List<PuntoDeVenta> puntos = CachePuntoVenta.getAll();
        PuntoDeVenta puntoA = puntos.stream().filter(p -> p.getId().equals(idPuntoA)).findFirst().orElse(null);
        PuntoDeVenta puntoB = puntos.stream().filter(p -> p.getId().equals(idPuntoB)).findFirst().orElse(null);
        CalculadoraCostos calculadoraCostos = new CalculadoraCostos();
        calculadoraCostos.setCaminos(caminos);
        return calculadoraCostos.calcularCosto(puntoA, puntoB);
    }

    public List<Camino> getCaminosDirectos(Long puntoA) {
        List<Camino> caminos = getCaminos();
        return caminos.stream()
                .filter(c -> (c.getPuntoA().getId().equals(puntoA) || c.getPuntoB().getId().equals(puntoA))
                        && !c.getBorrado()).toList();
    }

    public ServicioCamino() {}


}