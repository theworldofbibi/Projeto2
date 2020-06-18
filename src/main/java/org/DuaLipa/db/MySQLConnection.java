package org.DuaLipa.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    String db="DuaLipaCharts";
    String url="jdbc:mysql://localhost:32771/"+db; //localhost:32771 necessário mudar para "localhost:32775" (ass: Danilo)
    String usuario = "root";
    String senha = "root";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
