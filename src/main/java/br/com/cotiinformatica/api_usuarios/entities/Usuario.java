package br.com.cotiinformatica.api_usuarios.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name="senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "data_hora_cadastro", nullable = false)
    private LocalDateTime dataHoraCadastro;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false) //chave estrangeira
    private Perfil perfil;
}
