package Logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BordesDeTablero extends Rectangle {

   private int posX;
   private int posY;
   private int alto;
   private int ancho;
   private Color color;

    public BordesDeTablero(Color color, int posX, int posY, int ancho, int alto) {
        this.posX = posX;
        this.posY = posY;
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
    }

    public void dibujarBordes(Graphics g) {
        this.x = this.posX;
        this.y = this.posY;
        this.width = this.ancho;
        this.height = this.alto;
        g.setColor(color);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public int getposX() {
        return posX;
    }

    public void setposX(int posX) {
        this.posX = posX;
    }

    public int getposY() {
        return posY;
    }

    public void setposY(int posY) {
        this.posY = posY;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
