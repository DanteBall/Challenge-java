package teco.challenge.challengejava.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import teco.challenge.challengejava.dominio.Camino;

public interface RepoCamino extends JpaRepository<Camino, Long> {

}
