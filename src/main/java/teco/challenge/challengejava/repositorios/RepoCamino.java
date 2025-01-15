package teco.challenge.challengejava.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;

import java.util.List;

public interface RepoCamino extends JpaRepository<Camino, Long> {
    List<Camino> findByPuntoAOrPuntoB(PuntoDeVenta puntoA, PuntoDeVenta puntoB);
}
