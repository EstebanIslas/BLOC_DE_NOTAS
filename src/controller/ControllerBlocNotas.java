/*
 * ISLAS SANTOS ESTEBAN
 * UNIVERSIDAD TECNOLÓGICA DE TULANCINGO
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import model.ModelBlocNotas;
import view.ViewBlocNotas;

/**
 *
 * @author USUARIO
 */
public class ControllerBlocNotas {

    ModelBlocNotas modelBlocNotas;
    ViewBlocNotas viewBlocNotas;
    

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewBlocNotas.jmi_leer) {
                LeerArchivo();
            }else if(e.getSource() == viewBlocNotas.jmi_guardar){
                escribirArchivo();
                JOptionPane.showMessageDialog(null, "Guardado con exito!!");
            }
        }
    };

    public ControllerBlocNotas(ModelBlocNotas modelBlocNotas, ViewBlocNotas viewBlocNotas) {
        this.modelBlocNotas = modelBlocNotas;
        this.viewBlocNotas = viewBlocNotas;
        InitComponents();
        this.viewBlocNotas.jmi_leer.addActionListener(actionListener);
        this.viewBlocNotas.jmi_guardar.addActionListener(actionListener);

    }

    public void LeerArchivo() {
       try {
            String row;
            StringBuilder contenido = new StringBuilder();
            try (FileReader file = new FileReader(modelBlocNotas.getPath())) {
                BufferedReader bufferedReader = new BufferedReader(file);
                int i = 0;
                while ((row = bufferedReader.readLine()) != null) {
                    contenido.append(row);
                    contenido.append("\n");
                    
                }
                viewBlocNotas.jta_notas.setText(String.valueOf(contenido));
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
                System.err.println("File Not Found!! " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.err.println("Error I/O Operation " + ex.getMessage());
        }
    }
    /**
     * Este metodo obtiene lo que tiene el jta_notas y posterior
     * el usuario edita el archivo abierto y se actualiza el archivo
     */
    public void escribirArchivo() {
        modelBlocNotas.setText(viewBlocNotas.jta_notas.getText());
        try {
            File file = new File(modelBlocNotas.getPath());
            FileWriter fileWriter = new FileWriter(file, false);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(modelBlocNotas.getText());
                
                printWriter.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File Not Found!! " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error I/O Operation " + ex.getMessage());
        }
    }
    

    public void InitComponents() {
        viewBlocNotas.setVisible(true);
        viewBlocNotas.setLocationRelativeTo(null);

    }
}
