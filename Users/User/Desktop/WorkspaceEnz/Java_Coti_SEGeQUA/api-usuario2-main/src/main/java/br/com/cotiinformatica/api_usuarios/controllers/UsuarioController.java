package br.com.cotiinformatica.api_usuarios.controllers;

import br.com.cotiinformatica.api_usuarios.components.JwtTokenComponent;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenComponent  jwtTokenComponent;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@Valid @RequestBody CriarUsuarioRequest request) {
        try {
            var response = usuarioService.criarUsuario(request);
            return ResponseEntity.status(201).body(response); //201 = created
        }
        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(409).body(e.getMessage()); //409 = conflict
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@Valid @RequestBody AutenticarUsuarioRequest request) {

        try {
            var response = usuarioService.autenticarUsuario(request);
            return ResponseEntity.status(200).body(response);
        }
        catch (AcessoNegadoException e) {
            return ResponseEntity.status(401).body(e.getMessage());

        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
    @GetMapping("obter-dados")
    public ResponseEntity<?> obterDados(HttpServletRequest request) {
        try {
            //extraindo o email do usuario contido no token
            var emailusuario = jwtTokenComponent.getEmailUsuario(request);

            var response = usuarioService.obterDados(emailusuario);

            return ResponseEntity.status(200).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}