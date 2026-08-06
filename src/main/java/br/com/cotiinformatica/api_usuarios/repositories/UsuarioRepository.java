package br.com.cotiinformatica.api_usuarios.repositories;

import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository                                         /*campo no bd, tipo id*/
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    /*
       Consulta usando a sintaxe JPQL para retornar true ou false
       caso um email já esteja cadastrado na tabela de usuários
    */
    @Query("""
        SELECT CASE
            WHEN COUNT(u) > 0 THEN true
            ELSE false
        END
        FROM Usuario u
        WHERE u.email = :email
    """)
    boolean existsByEmail(@Param("email") String email);


    /*
       Consulta usando JPQL para retornar do banco de dados
       1 usuário através do email e da senha informados
    */
    @Query("""
        SELECT u 
        FROM Usuario u
        WHERE u.email = :email
        AND u.senha = :senha   
    """)
    Usuario findByEmailAndSenha(
            @Param("email") String email,
            @Param("senha") String senha);

}
