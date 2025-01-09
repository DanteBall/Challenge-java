package teco.challenge.challengejava.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teco.challenge.challengejava.dominio.Acreditacion;

@Repository
public interface RepoAcreditacion extends JpaRepository<Acreditacion, Long> {
    
}
