package cotiinformatica.api_produtos.repositories;

import cotiinformatica.api_produtos.entities.Produto;
import cotiinformatica.api_produtos.factories.ConnectionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProdutoRepository {

    /*
        Método para inserir um produto no banco de dados
     */
    public void insert(Produto produto) throws Exception {

        //Abrindo conexão com o banco de dados
        try (var connection = ConnectionFactory.getConnection()) {

            //Escrevendo a query SQL para executar o cadastro do produto
            var statement = connection.prepareStatement("""
                 INSERT INTO produtos (id, nome, preco, quantidade, datahoracadastro, ativo)
                 VALUES(?, ?, ?, ?, ?, ?)
            """);
            statement.setObject(1, produto.getId());
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidade());
            statement.setTimestamp(5, Timestamp.valueOf(produto.getDataHoraCadastro()));
            statement.setBoolean(6, produto.getAtivo());
            statement.execute();
        }
    }

    /*
        Método para atualizar um produto no banco de dados
     */
    public boolean update(Produto produto) throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                UPDATE produtos
                SET
                    nome = ?,
                    preco = ?,
                    quantidade = ?
                WHERE
                    id = ?
            """);
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setObject(4, produto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    /*
        Método para fazer a exclusão lógica do produto (inativar o produto)
     */
    public boolean delete(UUID id) throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                 UPDATE  produtos
                 SET
                     ativo = false
                 WHERE
                     id = ?
            """);
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    /*
        Método para retornar uma lista de todos os produtos
        ativos no banco de dados
     */
    public List<Produto> findAll() throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                SELECT id, nome, preco, quantidade, datahoracadastro, ativo
                FROM produtos
                WHERE ativo = true
            """);
            var result = statement.executeQuery(); //capturando o resultado da consulta

            var lista = new ArrayList<Produto>();
            while(result.next()) {
                var produto = new Produto();
                produto.setId((UUID) result.getObject("id"));
                produto.setNome(result.getString("nome"));
                produto.setPreco(result.getDouble("preco"));
                produto.setQuantidade(result.getInt("quantidade"));
                produto.setDataHoraCadastro(result.getTimestamp("datahoracadastro").toLocalDateTime());
                produto.setAtivo(result.getBoolean("ativo"));
                lista.add(produto);
            }
            return lista;
        }
    }

}
