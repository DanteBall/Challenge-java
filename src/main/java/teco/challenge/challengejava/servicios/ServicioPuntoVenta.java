package teco.challenge.challengejava.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.cache.CacheCaminos;
import teco.challenge.challengejava.cache.CachePuntoVenta;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.repositorios.RepoCamino;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;

import java.util.List;

@Service
public class ServicioPuntoVenta {

    @Autowired
    private RepoPuntoVenta repoPuntoDeVenta;

    @Autowired
    private RepoCamino repoCamino;

    public List<PuntoDeVenta> getPuntosVenta() {
        List<PuntoDeVenta> puntosDeVenta = CachePuntoVenta.getAll();
        if (puntosDeVenta.isEmpty()) {
            puntosDeVenta = repoPuntoDeVenta.findAll().stream().filter(p -> !p.getBorrado()).toList();
            puntosDeVenta.forEach(puntoDeVenta -> CachePuntoVenta.put(puntoDeVenta.getId(), puntoDeVenta));
        }
        return puntosDeVenta;
    }

    public PuntoDeVenta getPuntoVenta(Long id) {
        PuntoDeVenta puntoDeVenta = CachePuntoVenta.get(id);
        if (puntoDeVenta == null) {
            puntoDeVenta = repoPuntoDeVenta.findById(id).filter(p -> !p.getBorrado()).orElse(null);
            if (puntoDeVenta != null) {
                CachePuntoVenta.put(id, puntoDeVenta);
            }
        }
        return puntoDeVenta;
    }

    public PuntoDeVenta savePuntoVenta(PuntoDeVenta puntoDeVenta) {
        PuntoDeVenta savedPuntoDeVenta = repoPuntoDeVenta.save(puntoDeVenta);
        CachePuntoVenta.put(savedPuntoDeVenta.getId(), savedPuntoDeVenta);
        return savedPuntoDeVenta;
    }

    public void deletePuntoVenta(Long id) {
        PuntoDeVenta puntoDeVenta = repoPuntoDeVenta.findById(id).orElse(null);
        if (puntoDeVenta != null) {

            puntoDeVenta.setBorrado(true);
            repoPuntoDeVenta.save(puntoDeVenta);
            CachePuntoVenta.remove(id);

            List<Camino> caminos = repoCamino.findByPuntoAOrPuntoB(puntoDeVenta, puntoDeVenta);
            for (Camino camino : caminos) {
                camino.setBorrado(true);
                repoCamino.save(camino);
                CacheCaminos.remove(camino.getId());
            }
        }
    }
}