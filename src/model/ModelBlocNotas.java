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
    /**
     * Metodo que cifra una cadena de caracteres de tipo cifrado Cesar
     * @param cadena
     * @return El texto o cadena que el usuario desea cifrar
     */
    public String Cifrado(String cadena){
        char caracter ;
        String convers = "";
        for (int i = 0; i< cadena.length(); i++){
            caracter = cadena.charAt(i);
            int ascii = (int)caracter;
            ascii = ascii + 1;
            caracter = (char)ascii;
            convers += Character.toString(caracter);
        }
        return convers;
    }
    
    /**
     * Metodo que descifra una cadena de caracteres de tipo cifrado Cesar
     * @param cadena
     * @return El texto que el usuario ingresa o desea descifrar
     */
    public String Descifrado(String cadena){
        char caracter ;
        String convers = "";
        for (int i = 0; i< cadena.length(); i++){
            caracter = cadena.charAt(i);
            int ascii = (int)caracter;
            ascii = ascii - 1;
            caracter = (char)ascii;
            convers += Character.toString(caracter);
        }
        return convers;
    }
}
