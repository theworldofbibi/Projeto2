package org.DuaLipa.db;

import org.DuaLipa.api.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ChartsDAO {
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
                } catch(Exception e) { /*Não é uma data*/ }

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
    }
    public Charts getAllCharts() {
        return this.dataBase;
    }
}
