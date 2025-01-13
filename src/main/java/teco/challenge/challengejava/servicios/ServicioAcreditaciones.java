package teco.challenge.challengejava.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teco.challenge.challengejava.dominio.Acreditacion;
import teco.challenge.challengejava.repositorios.RepoAcreditacion;

import java.util.List;

@Service
public class ServicioAcreditaciones {

    @Autowired
    private RepoAcreditacion repoAcreditacion;

    public Acreditacion saveAcreditacion(Acreditacion acreditacion) {
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



    public ServicioAcreditaciones(RepoAcreditacion repoAcreditacion) {
        this.repoAcreditacion = repoAcreditacion;
    }
}
