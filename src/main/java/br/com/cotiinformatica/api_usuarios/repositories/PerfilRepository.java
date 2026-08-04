package br.com.cotiinformatica.api_usuarios.repositories;

import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                      /*campo no bd, tipo id*/
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
