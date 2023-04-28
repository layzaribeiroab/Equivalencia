package br.com.equivalencia.dao;
import java.sql.*;

public class ModuloConexao {
    
    public static Connection conector(){
        //Método responsável por estabelecer a conexão com o banco de dados
        java.sql.Connection conexao = null;
        
        //A linha abaixo chama o driver importado de conexão 
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3308/equivalencia";
        String user = "root";
        String password = "";
        
        //Estabelendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }
}
