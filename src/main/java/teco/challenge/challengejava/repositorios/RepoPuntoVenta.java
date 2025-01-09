package teco.challenge.challengejava.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import teco.challenge.challengejava.dominio.PuntoDeVenta;

public interface RepoPuntoVenta extends JpaRepository<PuntoDeVenta, Long> {

}
