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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
                abrirArchivo();
            } else if (e.getSource() == viewBlocNotas.jmi_guardar) {
                escribirArchivo();
            } else if (e.getSource() == viewBlocNotas.jmi_cifrar) {
                cifrarArchivo();
            } else if (e.getSource() == viewBlocNotas.jmi_descifrar) {
                descifrarArchivo();
            }
        }
    };

    public ControllerBlocNotas(ModelBlocNotas modelBlocNotas, ViewBlocNotas viewBlocNotas) {
        this.modelBlocNotas = modelBlocNotas;
        this.viewBlocNotas = viewBlocNotas;
        InitComponents();
        this.viewBlocNotas.jmi_leer.addActionListener(actionListener);
        this.viewBlocNotas.jmi_guardar.addActionListener(actionListener);
        this.viewBlocNotas.jmi_cifrar.addActionListener(actionListener);
        this.viewBlocNotas.jmi_descifrar.addActionListener(actionListener);

    }

    /**
     * El metodo consiste en abrir un Panel de opcion para que el usuario escoja
     * el archivo que quiere editar, seguido se hace una decision que determina
     * si se ha seleccionado un archivo o no de ser verdadero abre el archivo e
     * imprime las lineas que contiene.
     */
    public void abrirArchivo() {

        JFileChooser archivo = new JFileChooser(); //Objeto que trae al filechooser
        archivo.setFileSelectionMode(JFileChooser.FILES_ONLY); // solo visualizara documentos en la variable de objeto
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos TXT", "txt");//filtro
        archivo.setFileFilter(filtro);

        if (JFileChooser.APPROVE_OPTION == archivo.showOpenDialog(viewBlocNotas)) { // Desicion para saber si se selecciono un archivo
            File file = archivo.getSelectedFile(); // Objeto que obtiene el archivo seleccionado desde la variable archivo

            try {
                String row;
                FileReader leer_archivo = new FileReader(file); // Objeto que trae la opcion para leer un archivo
                BufferedReader bufferedReader = new BufferedReader(leer_archivo); //Crea un espacio para el archivo abierto y se manda a leer_archivo
                StringBuilder contenido = new StringBuilder(); //

                while ((row = bufferedReader.readLine()) != null) { //Lee linea por linea del archivo
                    contenido.append(row); //contenido guarda las lineas que existen
                    contenido.append("\n");

                }
                viewBlocNotas.jta_notas.setText(String.valueOf(contenido)); //el jta obtiene el valor de contenido (las lineas del archivo)
                bufferedReader.close();

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());
            }
        }
    }

    // *********************************** First Version to Open Files ****************************************************//
    /*public void LeerArchivo() {
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
    }*/
    /**
     * Este metodo obtiene lo que tiene el jta_notas y posterior el usuario
     * edita el archivo abierto y se actualiza el archivo
     */
    public void escribirArchivo() {
        modelBlocNotas.setText(viewBlocNotas.jta_notas.getText()); // Se obtiene el texto introducido por el usuario del jta

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // solo visualizara documentos en la variable de objeto
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos TXT", "txt");//filtro
        fileChooser.setFileFilter(filtro);
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewBlocNotas)) { // Desicion para saber si se selecciono un archivo

            try {
                File file = fileChooser.getSelectedFile(); //Objeto que llama a File y como parametro la url del archivo
                FileWriter fileWriter = new FileWriter(file, false);//Objeto ue llama al writer para editar el archivo
                try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    printWriter.println(modelBlocNotas.getText()); //Imprime lo que tiene la variable text de model al archivo
                    JOptionPane.showMessageDialog(null, "Archivo Guardado!!");
                    printWriter.close();
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());

            }
        }
    }

    public void cifrarArchivo() {
        modelBlocNotas.setText(viewBlocNotas.jta_notas.getText()); // Se obtiene el texto introducido por el usuario del jta

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // solo visualizara documentos en la variable de objeto
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos TXT", "txt");//filtro
        fileChooser.setFileFilter(filtro);
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewBlocNotas)) { // Desicion para saber si se selecciono un archivo

            try {
                String guarda = "";
                File file = fileChooser.getSelectedFile(); //Objeto que llama a File y como parametro la url del archivo
                FileWriter fileWriter = new FileWriter(file, false);//Objeto ue llama al writer para editar el archivo
                try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    guarda = modelBlocNotas.Cifrado(viewBlocNotas.jta_notas.getText()); //Escribe en el archivo, el contenido del jta
                    printWriter.println(guarda);
                    printWriter.close();
                    try {
                        String row;
                        FileReader leer_archivo = new FileReader(file); // Objeto que trae la opcion para leer un archivo
                        BufferedReader bufferedReader = new BufferedReader(leer_archivo); //Crea un espacio para el archivo abierto y se manda a leer_archivo
                        StringBuilder contenido = new StringBuilder(); //

                        while ((row = bufferedReader.readLine()) != null) { //Lee linea por linea del archivo
                            contenido.append(row); //contenido guarda las lineas que existen
                            contenido.append("\n");

                        }
                        viewBlocNotas.jta_notas.setText(String.valueOf(contenido)); //el jta obtiene el valor de contenido (las lineas del archivo)
                        bufferedReader.close();

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Archivo Cifrado");

                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());

            }
        }
    }

    public void descifrarArchivo() {
        modelBlocNotas.setText(viewBlocNotas.jta_notas.getText()); // Se obtiene el texto introducido por el usuario del jta

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // solo visualizara documentos en la variable de objeto
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos TXT", "txt");//filtro
        fileChooser.setFileFilter(filtro);
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewBlocNotas)) { // Desicion para saber si se selecciono un archivo

            try {
                String guarda = "";
                File file = fileChooser.getSelectedFile(); //Objeto que llama a File y como parametro la url del archivo
                FileWriter fileWriter = new FileWriter(file, false);//Objeto ue llama al writer para editar el archivo
                try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    guarda = modelBlocNotas.Descifrado(viewBlocNotas.jta_notas.getText()); //Escribe en el archivo, el contenido del jta
                    printWriter.println(guarda);
                    printWriter.close();
                    try {
                        String row;
                        FileReader leer_archivo = new FileReader(file); // Objeto que trae la opcion para leer un archivo
                        BufferedReader bufferedReader = new BufferedReader(leer_archivo); //Crea un espacio para el archivo abierto y se manda a leer_archivo
                        StringBuilder contenido = new StringBuilder(); //

                        while ((row = bufferedReader.readLine()) != null) { //Lee linea por linea del archivo
                            contenido.append(row); //contenido guarda las lineas que existen
                            contenido.append("\n");

                        }
                        viewBlocNotas.jta_notas.setText(String.valueOf(contenido)); //el jta obtiene el valor de contenido (las lineas del archivo)
                        bufferedReader.close();

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Archivo Descifrado");

                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());

            }
        }
    }

    public void InitComponents() {
        viewBlocNotas.setVisible(true);
        viewBlocNotas.setLocationRelativeTo(null);

    }
}
