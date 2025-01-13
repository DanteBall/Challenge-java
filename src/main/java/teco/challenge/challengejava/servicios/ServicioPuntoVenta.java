package teco.challenge.challengejava.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;

import java.util.List;

@Service
public class ServicioPuntoVenta {

    @Autowired
    private RepoPuntoVenta repoPuntoVenta;

    @Cacheable(value = "puntosVenta", unless = "#result.isEmpty()")
    public List<PuntoDeVenta> getPuntosVenta() {
        List<PuntoDeVenta> puntosVenta = repoPuntoVenta.findAll().stream().filter(p -> !p.getBorrado()).toList();
        puntosVenta.forEach(this::cargarCache);
        return puntosVenta;
    }

    @CachePut(value = "puntosVenta", key = "#puntoVenta.id")
    public void cargarCache(PuntoDeVenta puntoVenta) {
    }

    @Cacheable(value = "puntosVenta", key = "#id", unless = "#result == null")
    public PuntoDeVenta getPuntoVenta(Long id) {
        return repoPuntoVenta.findById(id).filter(p -> !p.getBorrado()).orElse(null);
    }

    @CachePut(value = "puntosVenta", key = "#result.id")
    public PuntoDeVenta savePuntoVenta(PuntoDeVenta puntoVenta) {
        return repoPuntoVenta.save(puntoVenta);
    }

    @CacheEvict(value = "puntosVenta", key = "#id", condition = "#result != null")
    public PuntoDeVenta deletePuntoVenta(Long id) {
        PuntoDeVenta punto = repoPuntoVenta.findById(id).filter(p -> !p.getBorrado()).orElse(null);
        if (punto != null) {
            punto.setBorrado(true);
            repoPuntoVenta.save(punto);
        }
        return punto;
    }


    public ServicioPuntoVenta(RepoPuntoVenta repoPuntoVenta) {
        this.repoPuntoVenta = repoPuntoVenta;
    }

}
