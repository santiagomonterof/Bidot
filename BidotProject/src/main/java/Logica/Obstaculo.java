package Logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstaculo extends Rectangle {

    private int posX;
    private int posY;
    private int tamañoAncho;
    private int tamañoAlto;
    private int velocidad;
    private Color color;

    public Obstaculo() {
    }

    public Obstaculo(int posX, int posY, int tamañoAncho, int tamañoAlto, int velocidad, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.tamañoAlto = tamañoAlto;
        this.tamañoAncho = tamañoAncho;
        this.velocidad = velocidad;
        this.color = color;
    }

    public void dibujarObstaculo(Graphics g) {
        this.x = this.posX;
        this.y = this.posY;
        this.width = this.tamañoAncho;
        this.height = this.tamañoAlto;
        g.setColor(color);
        g.fillRect(this.x, this.y, this.width, this.height);
       
    }

    public void moverDerecha() {
        posX += velocidad;
    }

    public void moverIzquierda() {
        posX -= velocidad;
    }

    public void moverArriba() {
        posY -= velocidad;
    }

    public void moverAbajo() {
        posY += velocidad;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTamañoAncho() {
        return tamañoAncho;
    }

    public void setTamañoAncho(int tamañoAncho) {
        this.tamañoAncho = tamañoAncho;
    }

    public int getTamañoAlto() {
        return tamañoAlto;
    }

    public void setTamañoAlto(int tamañoAlto) {
        this.tamañoAlto = tamañoAlto;
    }

}
