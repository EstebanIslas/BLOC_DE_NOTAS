/*
 * ISLAS SANTOS ESTEBAN
 * UNIVERSIDAD TECNOLÓGICA DE TULANCINGO
 */
package model;

/**
 *
 * @author USUARIO
 */
public class ModelBlocNotas {
    private String text= "";
    private String path= "C:\\Users\\USUARIO\\Pictures\\LEER_Archivos\\archivo.txt";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
