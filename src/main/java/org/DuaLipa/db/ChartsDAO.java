package org.DuaLipa.db;

import org.DuaLipa.api.*;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ChartsDAO {

    public ChartsDAO() {

        }

    private String readSQL = "SELECT * FROM DSN_DuaLipa";

    private final MySQLConnection mysql = new MySQLConnection();

    public List<Results> read() {
        Connection conexao = mysql.getConnection();
        List<Results> r = new ArrayList();

        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Results results = new Results();
                results.setWeek(rs.getString("Week"));
                results.setPosition(rs.getInt("Position"));
                r.add(results);
            }

            return r;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return r;
    }

    /*
    Charts dataBase;
    public ChartsDAO() {
        this.dataBase = new Charts();

        System.out.println("ChartsDAO - Lendo dados do arquivo CSV");

        try (Scanner scanner = new Scanner(new File("./resources/DSNCharts.csv"));) {

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] cols = line.split(";");
                Date week = null;

                try {
                    week = sdf.parse(cols[0]);
                } catch(Exception e) { /*Não é uma data}

                if (week != null) {
                    Results results = new Results();
                    results.setDate(cols[0]);
                    results.setValue(Double.parseDouble(cols[1]));
                    this.dataBase.getResults().add(results);

                    // Lendo cabecalho
                }  else if(cols.length > 0 && (cols[0].equals("Week") || cols[0].equals("Semana"))) {
                    this.dataBase.setTerm(cols[1]);
                }
            }

            System.out.println("Charts - Leitura realizada");
        } catch (Exception ex) {
            this.dataBase = new Charts();
            ex.printStackTrace();
            System.out.println("Charts - Erro na leitura do CSV");
        }
    }*/
}
