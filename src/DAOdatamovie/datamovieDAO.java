/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatamovie;

import java.sql.*;
import java.util.*;
import model.*;
import koneksi.connector;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAOimplement.datamovieimplement;
import javax.swing.JOptionPane;

/**
 *
 * @author hikmal haqiqi
 */
public class datamovieDAO implements datamovieimplement {

    Connection connection;
    final String select = "select * from movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "update movie set alur=?, penokohan=?,akting=?,nilai=?  where judul=?";
    final String delete = "delete from movie where judul=?;";

    public datamovieDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(datamovie p) {
        PreparedStatement statement = null;

        try {

            if (validasiinput(p.getAlur()) && validasiinput(p.getPenokohan()) && validasiinput(p.getAkting())) {

                statement = connection.prepareStatement(update);

                statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, p.getJudul());
                statement.setDouble(2, p.getAlur());
                statement.setDouble(3, p.getPenokohan());
                statement.setDouble(4, p.getAkting());
                statement.setDouble(5, p.getNilai());

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Ditambahkan", "sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Input alur, penokohan, dan akting harus antara 0 dan 5", "error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Judul movie tersebut sudah ditambahkan", "error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(datamovie p) {
        PreparedStatement statement = null;
        try {
            if (validasiinput(p.getAlur()) && validasiinput(p.getPenokohan()) && validasiinput(p.getAkting())) {

                statement = connection.prepareStatement(update);

                statement.setDouble(1, p.getAlur());
                statement.setDouble(2, p.getPenokohan());
                statement.setDouble(3, p.getAkting());
                statement.setDouble(4, p.getNilai());
                statement.setString(5, p.getJudul());

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Update", "sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Input alur, penokohan, dan akting harus antara 0 dan 5", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datamovie> getAll() {
        List<datamovie> dm = null;
        try {
            dm = new ArrayList<datamovie>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                datamovie movie = new datamovie();
                movie.setJudul(result.getString("judul"));
                movie.setAlur(result.getDouble("alur"));
                movie.setPenokohan(result.getDouble("penokohan"));
                movie.setAkting(result.getDouble("akting"));
                movie.setNilai(result.getDouble("nilai"));
                dm.add(movie);

            }
            result.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(datamovieDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return dm;
    }

    private boolean validasiinput(double input) {
        return input >= 0 && input <= 5;
    }

}
