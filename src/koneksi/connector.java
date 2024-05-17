/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hikmal haqiqi
 */
public class connector {

    private static Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://localhost/movie_database";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Koneksi berhasil");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Koneksi gagal ") ;
        }
    }

    public static Connection connection() {
        return connection;
    }
}
