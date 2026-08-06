package br.com.cotiinformatica.api_usuarios.repositories;

import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository                                      /*campo no bd, tipo id*/
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    /*
       Consulta JPQL que deverá retornar os dados
       de um perfil através do nome informado
    */
    @Query("""
        SELECT p 
        FROM Perfil p
        WHERE p.nome = :nome
    """)
    Perfil findByNome(@Param("nome") String nome);
}
