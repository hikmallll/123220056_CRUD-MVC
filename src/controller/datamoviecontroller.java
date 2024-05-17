/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import DAOdatamovie.datamovieDAO;
import DAOimplement.datamovieimplement;
import javax.swing.JOptionPane;
import model.*;
import view.Mainview;

/**
 *
 * @author hikmal haqiqi
 */
public class datamoviecontroller {

    private Double nilaiAlur;
    private Double nilaiPenokohan;
    private Double nilaiAkting;
    private Double nilairate;
    private boolean inputgagal = false;
    private boolean updategagal = false;

    Mainview frame;
    datamovieimplement impldatamovie;
    List<datamovie> dm;

    private boolean Cekinput() {
        return !frame.getInputJudul().getText().isEmpty()
                && !frame.getInputAlur().getText().isEmpty()
                && !frame.getInputPenokohan().getText().isEmpty()
                && !frame.getInputAkting().getText().isEmpty();
    }

    public datamoviecontroller(Mainview frame) {
        this.frame = frame;
        impldatamovie = new datamovieDAO();
        dm = impldatamovie.getAll();
    }

    public void inputTabel() {
        dm = impldatamovie.getAll();
        modeltabeldatamovie a = new modeltabeldatamovie(dm);
        frame.getTabelData().setModel(a);
    }

    public void insert() {
        if (Cekinput()) {
            try {
                datamovie dm = new datamovie();
                dm.setJudul(frame.getInputJudul().getText());
                dm.setAlur(Double.parseDouble(frame.getInputAlur().getText()));
                dm.setPenokohan(Double.parseDouble(frame.getInputPenokohan().getText()));
                dm.setAkting(Double.parseDouble(frame.getInputAkting().getText()));
                nilaiAlur = Double.parseDouble(frame.getInputAlur().getText());
                nilaiPenokohan = Double.parseDouble(frame.getInputPenokohan().getText());
                nilaiAkting = Double.parseDouble(frame.getInputAkting().getText());
                nilairate = (nilaiAlur + nilaiPenokohan + nilaiAkting) / 3;
                dm.setNilai(nilairate);

                impldatamovie.insert(dm);
                inputgagal = false;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
            inputgagal = true;
        }

    }

    public void update() {
        
        
        if (Cekinput()) {
            try {
                datamovie dm = new datamovie();
        dm.setJudul(frame.getInputJudul().getText());
        dm.setAlur(Double.parseDouble(frame.getInputAlur().getText()));
        dm.setPenokohan(Double.parseDouble(frame.getInputPenokohan().getText()));
        dm.setAkting(Double.parseDouble(frame.getInputAkting().getText()));
        nilaiAlur = Double.parseDouble(frame.getInputAlur().getText());
        nilaiPenokohan = Double.parseDouble(frame.getInputPenokohan().getText());
        nilaiAkting = Double.parseDouble(frame.getInputAkting().getText());
        nilairate = (nilaiAlur + nilaiPenokohan + nilaiAkting) / 3;
        dm.setNilai(nilairate);

        impldatamovie.update(dm);
                inputgagal = false;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
            inputgagal = true;
        }
        
        
    }

    public void delete() {

        String judul = frame.getInputJudul().getText();
        impldatamovie.delete(judul);
        JOptionPane.showMessageDialog(null, "Berhasil Terhapus", "sukses", JOptionPane.INFORMATION_MESSAGE);

    }

}
