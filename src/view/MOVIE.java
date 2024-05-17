/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author hikmal haqiqi
 */
public class MOVIE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mainview v = new Mainview();
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
