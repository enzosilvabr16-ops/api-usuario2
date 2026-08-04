package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
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

    /*metodo pra criar usuario*/

    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) throws Exception{

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setDataHoraCadastro(LocalDateTime.now());
        //TODO definir perfil do user

        //salvar o user no bd

        return null;
    }

    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) throws Exception{

    return null;
    }
}
