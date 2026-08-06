package br.com.cotiinformatica.api_usuarios.controllers;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
    public ResponseEntity<?> autenticar(@Valid  @RequestBody AutenticarUsuarioRequest request) {

        return ResponseEntity.ok().build();
    }
}