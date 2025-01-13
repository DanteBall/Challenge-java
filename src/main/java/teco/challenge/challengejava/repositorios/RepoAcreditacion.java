package teco.challenge.challengejava.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teco.challenge.challengejava.dominio.Acreditacion;

import java.util.List;

@Repository
public interface RepoAcreditacion extends JpaRepository<Acreditacion, Long> {

    @Query("SELECT a FROM Acreditacion a WHERE a.puntoDeVenta.id = :idPuntoVenta AND a.borrado = false")
    List<Acreditacion> findByPuntoDeVenta(@Param("idPuntoVenta") Long id);
}
