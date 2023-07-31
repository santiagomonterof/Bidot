package Logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Nivel extends JPanel implements KeyListener, Runnable {

    private JPanel panel;
    private ArrayList<Pelota> misPelotas = new ArrayList<>();
    private ArrayList<Obstaculo> misObstaculos = new ArrayList<>();
    private ArrayList<BordesDeTablero> misBordes = new ArrayList<>();

    private ArrayList<String> PosX_y_PosY_DePelotasAlmacen = new ArrayList<>();
    private ArrayList<String> PosX_y_PosY_DeObstaculosAlmacen = new ArrayList<>();

    private JButton btnCambioDeNivel = new JButton(new ImageIcon("siguiente.jpg"));
    private JLabel lbMensajeVictoria = new JLabel(new ImageIcon("fondo.jpg"));
    private JLabel lbImagenVictoria = new JLabel(new ImageIcon("victoria1.jpg"));
    private JLabel lbTiempo = new JLabel(" Tiempo  01:00");

    private boolean bandera = false;
    private int minutos;
    private int segundos;
    private int milesimas;

    private String min = "";
    private String seg = "";
    private String modoDeJuego = "";

    public Nivel() {
        setLayout(null);
        setBackground(Color.WHITE);
        agregarTiempo();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < misObstaculos.size(); i++) {
            misObstaculos.get(i).dibujarObstaculo(g);
        }
        for (int i = 0; i < misBordes.size(); i++) {
            misBordes.get(i).dibujarBordes(g);
        }
        for (int i = 0; i < misPelotas.size(); i++) {
            misPelotas.get(i).dibujarPelota(g);
        }
        repaint();
    }

    public void agregarTiempo() {
        lbTiempo.setBounds(50, 10, 100, 50);
        lbTiempo.setForeground(Color.LIGHT_GRAY);
        add(lbTiempo);

    }

    public void agregarPelotas(ArrayList<String> posicion, ArrayList<String> color, ArrayList<String> direccion, ArrayList<String> velocidad) {

        for (int i = 0; i < posicion.size(); i++) {
            Pelota pelota = new Pelota(Integer.parseInt(posicion.get(i).split("\\|")[0]), Integer.parseInt(posicion.get(i).split("\\|")[1]),
                    10, Integer.parseInt(velocidad.get(i)), Color.decode(color.get(i)), Boolean.valueOf(direccion.get(i)));
            misPelotas.add(pelota);
        }
        PosX_y_PosY_DePelotasAlmacen = posicion;
    }

    public void agregarObstaculos(ArrayList<String> PosX_y_PosY_DeObstaculos, int ancho, int alto) {

        if (modoDeJuego.equals("3")) {
            Obstaculo obstaculo = new Obstaculo(Integer.parseInt(PosX_y_PosY_DeObstaculos.get(0).split("\\|")[0]), Integer.parseInt(PosX_y_PosY_DeObstaculos.get(0).split("\\|")[1]), ancho, alto, 7, Color.BLACK);
            misObstaculos.add(obstaculo);
            PosX_y_PosY_DeObstaculosAlmacen = PosX_y_PosY_DeObstaculos;
        } else {
            Obstaculo obsDerecha = new Obstaculo(Integer.parseInt(PosX_y_PosY_DeObstaculos.get(0).split("\\|")[0]), Integer.parseInt(PosX_y_PosY_DeObstaculos.get(0).split("\\|")[1]), ancho, alto, 7, Color.RED);
            misObstaculos.add(obsDerecha);
            Obstaculo obsIzquierda = new Obstaculo(Integer.parseInt(PosX_y_PosY_DeObstaculos.get(1).split("\\|")[0]), Integer.parseInt(PosX_y_PosY_DeObstaculos.get(1).split("\\|")[1]), ancho, alto, 7, Color.BLUE);
            misObstaculos.add(obsIzquierda);
            PosX_y_PosY_DeObstaculosAlmacen = PosX_y_PosY_DeObstaculos;
        }

    }

    public void agregarBordes(ArrayList<String> PosX_y_PosY_DeBordes) {
        for (int i = 0; i < PosX_y_PosY_DeBordes.size(); i++) {

            if (i <= 2) {
                BordesDeTablero bordes = new BordesDeTablero(Color.RED, Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[0]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[1]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[2]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[3]));
                misBordes.add(bordes);
            } else {
                BordesDeTablero bordes = new BordesDeTablero(Color.BLUE, Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[0]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[1]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[2]), Integer.parseInt(PosX_y_PosY_DeBordes.get(i).split("\\|")[3]));
                misBordes.add(bordes);
            }

        }
    }

    private void verificarColisionBordes(Pelota pelota) {
        if (modoDeJuego.equals("1") || modoDeJuego.equals("3")) {
            if (pelota.intersects(misBordes.get(0))) {
                pelota.cambiarHorizontal();
            } else if (pelota.intersects(misBordes.get(1))) {
                pelota.cambiarVertical();
            } else if (pelota.intersects(misBordes.get(2))) {
                pelota.cambiarHorizontal();
            } else if (pelota.intersects(misBordes.get(3))) {

                pelota.cambiarHorizontal();

            } else if (pelota.intersects(misBordes.get(4)) || pelota.getY() > misBordes.get(4).getposX() - 10) {
                pelota.setPosX(misBordes.get(4).getposX() - 10);
                pelota.cambiarVertical();
            } else if (pelota.intersects(misBordes.get(5))) {
                pelota.cambiarHorizontal();
            }

        } else {
            if (pelota.intersects(misBordes.get(0))) {
                pelota.cambiarVertical();
            } else if (pelota.intersects(misBordes.get(1))) {
                pelota.cambiarHorizontal();
            } else if (pelota.intersects(misBordes.get(2))) {
                pelota.cambiarVertical();
            } else if (pelota.intersects(misBordes.get(3))) {
                pelota.cambiarVertical();
            } else if (pelota.intersects(misBordes.get(4))) {
                pelota.cambiarHorizontal();
            } else if (pelota.intersects(misBordes.get(5))) {
                pelota.cambiarVertical();
            }
        }

    }

    public void verificarColisionObstaculosHorizontal(Pelota pelota) {
        if (!modoDeJuego.equals("3")) {
            if (pelota.intersects(misObstaculos.get(0)) || pelota.intersects(misObstaculos.get(1))) {
                pelota.cambiarVertical();
            }
        } else {
            if (pelota.intersects(misObstaculos.get(0))) {
                pelota.cambiarVertical();
            }
        }

    }

    public void verificarColisionObstaculosVertical(Pelota pelota) {
        if (pelota.intersects(misObstaculos.get(0)) || pelota.intersects(misObstaculos.get(1))) {
            pelota.cambiarHorizontal();

        }
    }

    public boolean verificarVictoriaHorizontal() {
        int contador = 0;
        for (int i = 0; i < misPelotas.size(); i++) {
            if (misPelotas.get(i).getColor().equals(Color.decode("#DF0101"))) {
                if (misPelotas.get(i).getPosY() < misObstaculos.get(0).getPosY() - misObstaculos.get(0).getTamañoAlto()) {
                    contador++;
                }
            }

            if (misPelotas.get(i).getColor().equals(Color.decode("#0404B4"))) {
                if (misPelotas.get(i).getPosY() >= misObstaculos.get(0).getPosY() + misObstaculos.get(0).getTamañoAlto()) {
                    contador++;
                }
            }
        }
        if (contador == misPelotas.size()) {
            return true;
        }
        return false;
    }

    public boolean verificarVictoriaVertical() {
        int contador = 0;
        for (int i = 0; i < misPelotas.size(); i++) {
            if (misPelotas.get(i).getColor().equals(Color.decode("#DF0101"))) {
                if (misPelotas.get(i).getPosX() <= 240) {
                    contador++;
                }
            }
            if (misPelotas.get(i).getColor().equals(Color.decode("#0404B4"))) {
                if (misPelotas.get(i).getPosX() >= 250) {
                    contador++;
                }
            }
        }
        if (contador == misPelotas.size()) {
            return true;
        }
        return false;
    }

    public void mensajeDeVictoria() {
        lbMensajeVictoria.setBounds(0, 0, 500, 900);
        btnCambioDeNivel.setBounds(200, 400, 100, 102);
        lbImagenVictoria.setBounds(50, 200, 400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(520, 914);
        panel.setBackground(Color.WHITE);
        panel.add(btnCambioDeNivel);
        panel.add(lbImagenVictoria);
        panel.add(lbMensajeVictoria);
        add(panel);

    }

    public void mensajeDeDerrotas() {
        lbMensajeVictoria.setBounds(0, 0, 500, 900);
        panel = new JPanel();

        panel.setLayout(null);
        panel.setSize(520, 914);
        panel.setBackground(Color.WHITE);
        panel.add(btnCambioDeNivel);
        panel.add(lbImagenVictoria);
        panel.add(lbMensajeVictoria);
        add(panel);
    }

    public void resetearPelotas() {
        for (int i = 0; i < misPelotas.size(); i++) {
            misPelotas.get(i).setPosX(Integer.parseInt(PosX_y_PosY_DePelotasAlmacen.get(i).split("\\|")[0]));
            misPelotas.get(i).setPosY(Integer.parseInt(PosX_y_PosY_DePelotasAlmacen.get(i).split("\\|")[1]));
        }
    }

    public void resetearObstaculos() {
        for (int i = 0; i < misObstaculos.size(); i++) {
            misObstaculos.get(i).setPosX(Integer.parseInt(PosX_y_PosY_DeObstaculosAlmacen.get(i).split("\\|")[0]));
            misObstaculos.get(i).setPosY(Integer.parseInt(PosX_y_PosY_DeObstaculosAlmacen.get(i).split("\\|")[1]));
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int codigoDeTecla = ke.getKeyCode();
        if (codigoDeTecla == KeyEvent.VK_RIGHT) {
            if (misObstaculos.size() > 0) {
                if (modoDeJuego.equals("1")) {
                    if (misObstaculos.get(0).getPosX() <= 480 && misObstaculos.get(1).getPosX() <= -65) {
                        misObstaculos.get(0).moverDerecha();
                        misObstaculos.get(1).moverDerecha();
                    }
                }

            }
        }
        if (codigoDeTecla == KeyEvent.VK_LEFT) {

            if (misObstaculos.size() > 0) {

                if (modoDeJuego.equals("1")) {
                    if (misObstaculos.get(1).getPosX() >= -480) {
                        misObstaculos.get(0).moverIzquierda();
                        misObstaculos.get(1).moverIzquierda();
                    }
                }

            }
        }
        if (codigoDeTecla == KeyEvent.VK_UP) {
            if (misObstaculos.size() > 0) {
                if (modoDeJuego.equals("2")) {
                    if (misObstaculos.get(1).getPosY() > -791) {
                        if (modoDeJuego.equals("2")) {
                            misObstaculos.get(0).moverArriba();
                            misObstaculos.get(1).moverArriba();

                        }
                    }
                }
            }
        }
        if (codigoDeTecla == KeyEvent.VK_DOWN) {
            if (misObstaculos.size() > 0) {
                if (modoDeJuego.equals("2")) {

                    if (misObstaculos.get(0).getPosY() <= 890) {
                        misObstaculos.get(0).moverAbajo();
                        misObstaculos.get(1).moverAbajo();
                    }
                }
            }
        }
        if (codigoDeTecla == KeyEvent.VK_SPACE) {
            if (misObstaculos.size() > 0) {
                misObstaculos.get(0).setPosX(500);
            }

        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (misObstaculos.size() > 0) {
                misObstaculos.get(0).setPosX(0);
            }
        }
    }

    public void cambiarBandera() {
        bandera = false;
    }

    public void cuentaAtras() {
        milesimas += 10;
        if (minutos >= 0) {
            if (milesimas == 1000) {
                milesimas = 0;
                segundos -= 1;
            }
            if (segundos == 0) {
                segundos = 59;
                minutos--;
            }
        } else {
            minutos = 0;
            segundos = 0;
        }
        if (minutos < 10) {
            min = "0" + minutos;
        } else {
            min = Integer.toString(minutos);
        }
        if (segundos < 10) {
            seg = "0" + segundos;
        } else {
            seg = Integer.toString(segundos);
        }

        lbTiempo.setText("Tiempo  " + min + ":" + seg);

    }

    @Override
    public void run() {

        while (!bandera) {
            try {
                cuentaAtras();
                for (int i = 0; i < misPelotas.size(); i++) {
                    misPelotas.get(i).mover();
                    verificarColisionBordes(misPelotas.get(i));
                    if (modoDeJuego.equals("1") || modoDeJuego.equals("3")) {
                        verificarColisionObstaculosHorizontal(misPelotas.get(i));
                    } else {
                        verificarColisionObstaculosVertical(misPelotas.get(i));
                    }
                    if (modoDeJuego.equals("1") || modoDeJuego.equals("3")) {
                        if (verificarVictoriaHorizontal() == true) {
                            mensajeDeVictoria();
                            bandera = true;
                            break;
                        }
                    } else {
                        if (verificarVictoriaVertical() == true) {
                            mensajeDeVictoria();
                            bandera = true;
                            break;
                        }
                    }

                }
                if (minutos == 0 && segundos == 0) {
                    bandera = true;
                    System.out.println(isBandera());
                    mensajeDeDerrotas();
                    JOptionPane.showMessageDialog(this, "Perdiste, dale al botón reiniciar para volver a intentarlo");

                    break;
                }
                Thread.sleep(10);

            } catch (InterruptedException ex) {
                Logger.getLogger(Nivel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setMisPelotas(ArrayList<Pelota> misPelotas) {
        this.misPelotas = misPelotas;
    }

    public ArrayList<BordesDeTablero> getMisBordes() {
        return misBordes;
    }

    public void setMisBordes(ArrayList<BordesDeTablero> misBordes) {
        this.misBordes = misBordes;
    }

    public ArrayList<Obstaculo> getMisObstaculos() {
        return misObstaculos;
    }

    public void setMisObstaculos(ArrayList<Obstaculo> misObstaculos) {
        this.misObstaculos = misObstaculos;
    }

    public JButton getBtnCambioDeNivel() {
        return btnCambioDeNivel;
    }

    public void setBtnCambioDeNivel(JButton btnCambioDeNivel) {
        this.btnCambioDeNivel = btnCambioDeNivel;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public String getModoDeJuego() {
        return modoDeJuego;
    }

    public void setModoDeJuego(String modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

}
