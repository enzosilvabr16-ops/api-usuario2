package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.components.CryptoComponent;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.repositories.PerfilRepository;
import br.com.cotiinformatica.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired //injeção de dependencia
    private PerfilRepository perfilRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    /*metodo pra criar usuario*/

    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) throws Exception{

        //verificando se o email ja existe
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException("O email informado já eiste. Tente outro.");
        }


        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.encrypt(request.senha()));
        usuario.setDataHoraCadastro(LocalDateTime.now());

        var perfil = perfilRepository.findByNome("Operador");
        if (perfil == null) { //se perfil nao foi criado
            perfil = new Perfil(); //cria um perfil novo
            perfil.setNome("Operador");
            //cadastra perfil no bd
            perfilRepository.save(perfil);
        }
        //associa o user no perfil
        usuario.setPerfil(perfil);
        //salva o user no bd
        usuarioRepository.save(usuario);

        //Retornar os dados do DTO de resposta
        return new CriarUsuarioResponse(
                "Usuário cadastrado com sucesso.",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                perfil.getNome(),
                LocalDateTime.now()
        );
    }

    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) throws Exception{

    return null;
    }
}
