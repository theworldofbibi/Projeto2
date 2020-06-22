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

    private String createSQL = "INSERT INTO DSN_DuaLipa VALUES (?, ?)";
    private String readSQL = "SELECT * FROM DSN_DuaLipa";
    private String updateSQL = "UPDATE DSN_DuaLipa SET Position = ? WHERE Week=?";
    private String deleteSQL = "DELETE FROM DSN_DuaLipa WHERE Week = ?";

    private final MySQLConnection mysql = new MySQLConnection();//
    public int size(){
        List<Results> r = new ArrayList();

        return r.size();
    }
    public boolean create(Results results) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setString(1, results.getWeek());
            stm.setInt(2, results.getPosition());

            int registros = stm.executeUpdate();
            return registros > 0 ? true : false;

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
        return false;
    }
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
    public boolean update(Results results) {
        Connection conexao = mysql.getConnection();

        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);
            stm.setString(2, results.getWeek());
            stm.setInt(1, results.getPosition());

            int registros = stm.executeUpdate();
            return registros > 0 ? true : false;

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
        return false;
    }
    public boolean delete(String date) {
        Connection conexao = mysql.getConnection();

        try {
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);

            stm.setString(1, date);
            int registros = stm.executeUpdate();
            return registros > 0 ? true : false;

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
        return false;
    }
}