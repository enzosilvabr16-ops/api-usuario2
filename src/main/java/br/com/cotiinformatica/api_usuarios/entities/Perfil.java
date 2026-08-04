package br.com.cotiinformatica.api_usuarios.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome",length = 25, nullable = false, unique = true)
    /*ele auto nomeia com o nome do atributo*/
    private String name;

    @OneToMany(mappedBy = "perfil") //nome do atributo da classe Usuario
    private List<Usuario> usuarios;
}
