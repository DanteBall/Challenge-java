package teco.challenge.challengejava.cache;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import teco.challenge.challengejava.cache.CacheCaminos;
import teco.challenge.challengejava.cache.CachePuntoVenta;
import teco.challenge.challengejava.repositorios.RepoCamino;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;
import teco.challenge.challengejava.servicios.ServicioCamino;
import teco.challenge.challengejava.servicios.ServicioPuntoVenta;

@Component
public class SchedulerCache {

    private RepoCamino repoCamino;

    private RepoPuntoVenta repoPuntoVenta;


    @Scheduled(fixedRate = 600000)
    public void limpiarCache() {
        System.out.println("Limpiando caché");
        CacheCaminos.clear();
        CachePuntoVenta.clear();
        repoCamino.findAll().stream()
                .filter(c -> !c.getBorrado())
                .forEach(c -> CacheCaminos.put(c.getId(), c));
        repoPuntoVenta.findAll().stream()
                .filter(p -> !p.getBorrado())
                .forEach(p -> CachePuntoVenta.put(p.getId(), p));
    }

    public SchedulerCache(RepoCamino repoCamino, RepoPuntoVenta repoPuntoVenta) {
        this.repoCamino = repoCamino;
        this.repoPuntoVenta = repoPuntoVenta;
    }
}
