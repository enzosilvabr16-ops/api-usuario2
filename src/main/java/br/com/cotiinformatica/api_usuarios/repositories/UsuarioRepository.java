package br.com.cotiinformatica.api_usuarios.repositories;

import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                         /*campo no bd, tipo id*/
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
