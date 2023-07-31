package Logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota extends Rectangle {

    private int posX;
    private int posY;
    private int tamaño;
    private double velocidad;
    private Color color;
    private boolean irDerecha = true;
    private boolean irAbajo = true;

    public Pelota() {
    }

    public Pelota(int posX, int posY, int tamaño, double velociad, Color color, boolean direccionAbajo) {
        this.posX = posX;
        this.posY = posY;
        this.tamaño = tamaño;
        this.velocidad = velociad;
        this.color = color;
        this.irAbajo = direccionAbajo;
    }

    public void dibujarPelota(Graphics g) {
        this.x = this.getPosX();
        this.y = this.getPosY();
        this.width = this.tamaño;
        this.height = this.tamaño;

        g.setColor(color);
        g.fillOval(this.x, this.y, this.width, this.height);

    }

    public void mover() {
        if (irDerecha) {
            moverDerecha();
        } else {
            moverIzquierda();
        }
        if (irAbajo) {
            moverAbajo();
        } else {
            moverArriba();
        }
    }

    public void cambiarHorizontal() {
        irDerecha = !irDerecha;
        if (irDerecha) {
            posX += tamaño + 2;

        } else {
            posX -= tamaño - 2;
        }
    }

    public void cambiarVertical() {
        irAbajo = !irAbajo;

        if (irAbajo) {
            posY += tamaño + 2;

        } else {
            posY -= tamaño - 2;
        }
    }

    public void moverIzquierda() {
        posX -= velocidad;
    }

    public void moverDerecha() {
        posX += velocidad;
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

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamañoAncho) {
        this.tamaño = tamañoAncho;
    }

    public double getVelocidad() {
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

    public boolean isIrDerecha() {
        return irDerecha;
    }

    public void setIrDerecha(boolean irDerecha) {
        this.irDerecha = irDerecha;
    }

    public boolean isIrAbajo() {
        return irAbajo;
    }

    public void setIrAbajo(boolean irAbajo) {
        this.irAbajo = irAbajo;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
