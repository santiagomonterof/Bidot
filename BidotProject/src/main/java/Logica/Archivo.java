package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

    private ArrayList<String> PosX_y_PosY_DePelotas = new ArrayList<>();
    private ArrayList<String> PosX_y_PosY_DeObstaculos = new ArrayList<>();
    private ArrayList<String> PosX_y_PosY_DeBordes = new ArrayList<>();
    private ArrayList<String> coloresDePelotas = new ArrayList<>();
    private ArrayList<String> direccionDePelotas = new ArrayList<>();
    private ArrayList<String> velocidadDePelotas = new ArrayList<>();
    String modoDeJuego;
    String anchoDeObstaculo;
    String altoDeObstaculo;
    

    public void leerDatosDeTxt(int nivel) {
        int contador = 0;
        try {
            File archivo = new File("Nivel" + nivel + ".txt");
            FileReader lector = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(lector);

            String linea = buffer.readLine();

            while (linea != null) {
                if (contador == 0) {
                    modoDeJuego = linea.split("=")[1];
                }
                if (contador == 1) {
                    String[] aux = linea.split("=")[1].split("-");
                    for (int i = 0; i < aux.length; i++) {
                        PosX_y_PosY_DePelotas.add(aux[i]);
                    }
                }
                if (contador == 2) {
                    String[] aux = linea.split("=")[1].split("-");
                    for (int i = 0; i < aux.length; i++) {
                        direccionDePelotas.add((aux[i]));
                    }
                }
                if (contador == 3) {
                    String[] aux = linea.split("=")[1].split("-");
                    for (int i = 0; i < aux.length; i++) {
                        coloresDePelotas.add((aux[i]));
                    }
                }
                if (contador == 4) {
                    String[] aux = linea.split("=")[1].split(" ");
                    for (int i = 0; i < aux.length; i++) {
                        PosX_y_PosY_DeObstaculos.add(aux[i]);
                    }
                }
                if (contador == 5) {
                    String[] aux = linea.split("=")[1].split("-");
                    for (int i = 0; i < aux.length; i++) {
                        PosX_y_PosY_DeBordes.add(aux[i]);
                    }
                }
                if (contador == 6) {
                    anchoDeObstaculo = (linea.split("=")[1].split("\\|")[0]);
                    altoDeObstaculo = (linea.split("=")[1].split("\\|")[1]);
                }
                if (contador == 7) {
                    String[] aux = linea.split("=")[1].split("\\|");
                    for (int i = 0; i < aux.length; i++) {
                        velocidadDePelotas.add(aux[i]);
                    }
                }

                contador++;
                linea = buffer.readLine();
            }

            buffer.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resetearDatos() {
        PosX_y_PosY_DePelotas.removeAll(PosX_y_PosY_DePelotas);
        PosX_y_PosY_DeObstaculos.removeAll(PosX_y_PosY_DeObstaculos);
        direccionDePelotas.removeAll(direccionDePelotas);
        coloresDePelotas.removeAll(coloresDePelotas);
        PosX_y_PosY_DeBordes.removeAll(PosX_y_PosY_DeBordes);
    }

    public ArrayList<String> getPosX_y_PosY_DePelotas() {
        return PosX_y_PosY_DePelotas;
    }

    public void setPosX_y_PosY_DePelotas(ArrayList<String> PosX_y_PosY_DePelotas) {
        this.PosX_y_PosY_DePelotas = PosX_y_PosY_DePelotas;
    }

    public ArrayList<String> getDireccionDePelotas() {
        return direccionDePelotas;
    }

    public void setDireccionDePelotas(ArrayList<String> direccionDePelotas) {
        this.direccionDePelotas = direccionDePelotas;
    }

    public ArrayList<String> getColoresDePelotas() {
        return coloresDePelotas;
    }

    public void setColoresDePelotas(ArrayList<String> coloresDePelotas) {
        this.coloresDePelotas = coloresDePelotas;
    }

    public ArrayList<String> getPosX_y_PosY_DeObstaculos() {
        return PosX_y_PosY_DeObstaculos;
    }

    public void setPosX_y_PosY_DeObstaculos(ArrayList<String> PosX_y_PosY_DeObstaculos) {
        this.PosX_y_PosY_DeObstaculos = PosX_y_PosY_DeObstaculos;
    }

    public ArrayList<String> getPosX_y_PosY_DeBordes() {
        return PosX_y_PosY_DeBordes;
    }

    public void setPosX_y_PosY_DeBordes(ArrayList<String> PosX_y_PosY_DeBordes) {
        this.PosX_y_PosY_DeBordes = PosX_y_PosY_DeBordes;
    }

    public String getAnchoDeObstaculo() {
        return anchoDeObstaculo;
    }

    public void setAnchoDeObstaculo(String anchoDeObstaculo) {
        this.anchoDeObstaculo = anchoDeObstaculo;
    }

    public String getAltoDeObstaculo() {
        return altoDeObstaculo;
    }

    public void setAltoDeObstaculo(String altoDeObstaculo) {
        this.altoDeObstaculo = altoDeObstaculo;
    }

    public String getModoDeJuego() {
        return modoDeJuego;
    }

    public void setModoDeJuego(String modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }

    public void setVelocidadDePelotas(ArrayList<String> velocidadDePelotas) {
        this.velocidadDePelotas = velocidadDePelotas;
    }

    public ArrayList<String> getVelocidadDePelotas() {
        return velocidadDePelotas;
    }
    
    
    

}
