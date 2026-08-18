package cotiinformatica.api_produtos.dtos;

//Record->Registro
public record ProdutoPostRequest(
        String nome,
        String quantidade,
        Double preco
) {
}
