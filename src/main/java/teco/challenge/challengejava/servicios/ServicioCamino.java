package teco.challenge.challengejava.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.dominio.CalculadoraCostos;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dominio.ResultadoCosto;
import teco.challenge.challengejava.repositorios.RepoCamino;

import java.util.List;

@Service
public class ServicioCamino {

    @Autowired
    private RepoCamino repoCamino;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(value = "caminos", unless = "#result.isEmpty()")
    public List<Camino> getCaminos() {
        List<Camino> puntosVenta = repoCamino.findAll().stream().filter(p -> !p.getBorrado()).toList();
        puntosVenta.forEach(this::cargarCache);
        return puntosVenta;
    }

    @CachePut(value = "caminos", key = "#camino.id")
    public void cargarCache(Camino camino) {
    }

    @Cacheable(value = "caminos", key = "#id", unless = "#result == null")
    public Camino getCamino(Long id) {
        return repoCamino.findById(id).
                filter(p -> !p.getBorrado()).orElse(null);
    }

    @CachePut(value = "caminos", key = "#result.id")
    public Camino saveCamino(Camino camino) {
        return repoCamino.save(camino);
    }

    public ResultadoCosto calcularCostoMinimo(PuntoDeVenta puntoA, PuntoDeVenta puntoB) {
        List<Camino> caminos = cacheCaminos();
        CalculadoraCostos calculadoraCostos = new CalculadoraCostos();
        calculadoraCostos.setCaminos(caminos);
        return calculadoraCostos.calcularCosto(puntoA, puntoB);
    }

    public List<Camino> getCaminosDirectos(PuntoDeVenta puntoA) {
        List<Camino> caminos = cacheCaminos();
        return caminos.stream().
                filter(c -> (c.getPuntoA().equals(puntoA) || c.getPuntoB().equals(puntoA))
                                                && !c.getBorrado()).toList();
    }

    private List<Camino> cacheCaminos(){
        List<Camino> caminos = null;
        try {
           caminos = cacheManager.getCache("caminos").get("caminos", List.class);
        }
        catch (NullPointerException e){
            caminos = getCaminos();
        }

        return caminos;

    }

    public ServicioCamino(RepoCamino repoCamino) {
        this.repoCamino = repoCamino;
    }

}
