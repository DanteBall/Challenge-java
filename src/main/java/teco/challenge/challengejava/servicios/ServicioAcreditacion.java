package teco.challenge.challengejava.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.cache.CachePuntoVenta;
import teco.challenge.challengejava.dominio.Acreditacion;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dto.DTOAcreditacion;
import teco.challenge.challengejava.repositorios.RepoAcreditacion;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;

import java.util.List;

@Service
public class ServicioAcreditacion {

    @Autowired
    private RepoAcreditacion repoAcreditacion;

    @Autowired
    private RepoPuntoVenta repoPuntoVenta;


    public Acreditacion saveAcreditacion(DTOAcreditacion DTOacreditacion) {
        Acreditacion acreditacion = new Acreditacion();
        acreditacion.setImporte(DTOacreditacion.getImporte());
        acreditacion.setFechaRecepcion(DTOacreditacion.getFechaRecepcion());
        PuntoDeVenta p = CachePuntoVenta.get(DTOacreditacion.getIdPuntoVenta());
        if (p == null) {
            p = repoPuntoVenta.findById(DTOacreditacion.getIdPuntoVenta()).orElse(null);
        }
        acreditacion.setPuntoDeVenta(p);
        acreditacion.setNombrePuntoVenta(p.getNombre());

        return repoAcreditacion.save(acreditacion);
    }

    public Acreditacion getAcreditacion(Long id) {

        return repoAcreditacion.findById(id).orElse(null);

    }

    public List<Acreditacion> getAcreditaciones() {

        return repoAcreditacion.findAll().stream()
                .filter(p -> !p.getBorrado()).toList();

    }

    public List<Acreditacion> getAcreditacionesPorPuntoVenta(Long idPuntoVenta) {

        return repoAcreditacion.findByPuntoDeVenta(idPuntoVenta);

    }



    public ServicioAcreditacion(RepoAcreditacion repoAcreditacion) {
        this.repoAcreditacion = repoAcreditacion;
    }
}
