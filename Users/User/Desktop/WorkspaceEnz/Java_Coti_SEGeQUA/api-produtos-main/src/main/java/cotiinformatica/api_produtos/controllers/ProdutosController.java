package cotiinformatica.api_produtos.controllers;

import cotiinformatica.api_produtos.dtos.ProdutoPostRequest;
import cotiinformatica.api_produtos.dtos.ProdutoPostResponse;
import cotiinformatica.api_produtos.entities.Produto;
import cotiinformatica.api_produtos.repositories.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @PostMapping
    public ResponseEntity<ProdutoPostResponse> post(@RequestBody ProdutoPostRequest request)
    {
        try {
             var produto = new Produto();

             produto.setId(UUID.randomUUID());
             produto.setNome(request.nome());
             produto.setPreco(request.preco());
             produto.setQuantidade(Integer.parseInt(request.quantidade()));
             produto.setDataHoraCadastro(LocalDateTime.now());
             produto.setAtivo(true);

             var produtoRepository = new ProdutoRepository();
             produtoRepository.insert(produto);

             var response = new ProdutoPostResponse(
                     201,
                     "Produto cadastrado com sucesso",
                     produto.getId(),
                     LocalDateTime.now()
             );
             return ResponseEntity.status(201).body(response);

        } catch (Exception e) {

            var response = new ProdutoPostResponse(
                    500,
                    "Falha ao inserir o produto: " + e.getMessage(),
                    null,
                    LocalDateTime.now()
            );
            return ResponseEntity.status(500).body(response);
        }

    }
    @PutMapping
    public ResponseEntity<?> put() {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> get() {
        try {
            var produtoRepository = new ProdutoRepository();
            var produtos = produtoRepository.findAll();
            return ResponseEntity.status(200).body(produtos);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Falha ao consultar produtos: " + e.getMessage());
        }
    }



}
