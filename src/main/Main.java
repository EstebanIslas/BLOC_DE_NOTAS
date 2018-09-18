/*
 * ISLAS SANTOS ESTEBAN
 * UNIVERSIDAD TECNOLÓGICA DE TULANCINGO
 */
package main;

import model.ModelBlocNotas;
import view.ViewBlocNotas;
import controller.ControllerBlocNotas;
/**
 *
 * @author USUARIO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelBlocNotas modelBlocNotas = new ModelBlocNotas();
        ViewBlocNotas viewBlocNotas = new ViewBlocNotas();
        ControllerBlocNotas controllerBlocNotas = new ControllerBlocNotas(modelBlocNotas, viewBlocNotas);
        
        
    }
    
}
