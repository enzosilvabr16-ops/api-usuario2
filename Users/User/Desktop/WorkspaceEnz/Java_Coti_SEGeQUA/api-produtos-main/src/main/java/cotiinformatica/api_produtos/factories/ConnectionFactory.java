package cotiinformatica.api_produtos.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws Exception {

        //variaveis pra conexao com bd
        var host ="jdbc:postgresql://localhost:5435/bd-produtos";
        var user ="postgres";
        var pass ="coti";

        //abrindo e retornando a conexão com o bd
        return DriverManager.getConnection(host,user,pass);
    }
}
