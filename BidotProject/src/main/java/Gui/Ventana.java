package Gui;

import Logica.Archivo;
import Logica.Nivel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/* import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream; */
public class Ventana extends JFrame implements ActionListener {

    private CardLayout card = new CardLayout();
    private JPanel contenedor = new JPanel();
    private PanelInicio panelInicio = new PanelInicio();
    private Archivo archivo = new Archivo();

    private Nivel nivel1 = new Nivel();
    private Nivel nivel2 = new Nivel();
    private Nivel nivel3 = new Nivel();
    private Nivel nivel4 = new Nivel();
    private Nivel nivel5 = new Nivel();
    private Nivel nivel6 = new Nivel();
    private Nivel nivel7 = new Nivel();
    private Nivel nivel8 = new Nivel();
    private Nivel nivel9 = new Nivel();

    private JPanel panelBotones = new JPanel();
    private JButton reinicio = new JButton(new ImageIcon("reiniciar.jpg"));
    private String posicion = " ";

    public Ventana() {
        super("Bidot");
        setSize(515, 994);
        addKeyListener(nivel1);
        addKeyListener(nivel2);
        addKeyListener(nivel3);
        addKeyListener(nivel4);
        addKeyListener(nivel5);
        addKeyListener(nivel6);
        addKeyListener(nivel7);
        addKeyListener(nivel8);
        addKeyListener(nivel9);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);

        añadirContenedor();
        añadirBotones();
        setVisible(true);
    }

    public void añadirContenedor() {
        contenedor.setLayout(card);
        panelInicio.btIniciar.addActionListener(this);
        reinicio.addActionListener(this);
        nivel1.getBtnCambioDeNivel().addActionListener(this);
        nivel2.getBtnCambioDeNivel().addActionListener(this);
        nivel3.getBtnCambioDeNivel().addActionListener(this);
        nivel4.getBtnCambioDeNivel().addActionListener(this);
        nivel5.getBtnCambioDeNivel().addActionListener(this);
        nivel6.getBtnCambioDeNivel().addActionListener(this);
        nivel7.getBtnCambioDeNivel().addActionListener(this);
        nivel8.getBtnCambioDeNivel().addActionListener(this);
        nivel9.getBtnCambioDeNivel().addActionListener(this);
        contenedor.add(panelInicio, "0");
        card.show(contenedor, "0");
        add(contenedor);
    }

    public void añadirBotones() {
        panelBotones.setPreferredSize(new Dimension(50, 55));
        reinicio.setPreferredSize(new Dimension(50, 45));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        reinicio.setEnabled(false);
        reinicio.setBackground(Color.WHITE);
        panelBotones.add(reinicio);
        panelBotones.setBackground(Color.WHITE);
        add(panelBotones, BorderLayout.NORTH);
    }

    public void añadirNivel(int documento, Nivel nivel, String posicion, int tiempo) {
        archivo.leerDatosDeTxt(documento);
        reinicio.setEnabled(true);
        nivel.setModoDeJuego(archivo.getModoDeJuego());
        nivel.agregarBordes(archivo.getPosX_y_PosY_DeBordes());
        nivel.agregarObstaculos(archivo.getPosX_y_PosY_DeObstaculos(), Integer.parseInt(archivo.getAnchoDeObstaculo()), Integer.parseInt(archivo.getAltoDeObstaculo()));
        nivel.agregarPelotas(archivo.getPosX_y_PosY_DePelotas(), archivo.getColoresDePelotas(), archivo.getDireccionDePelotas(), archivo.getVelocidadDePelotas());
        nivel.setMinutos(tiempo);
        contenedor.add(nivel, posicion);
        card.show(contenedor, posicion);
        Thread hilo = new Thread(nivel);
        hilo.start();
    }

    public void resetearNivel(Nivel nivel, int minuto, int segundo) {
        boolean verificar;
        nivel.resetearPelotas();
        nivel.resetearObstaculos();
        nivel.setMinutos(minuto);
        nivel.setSegundos(segundo);
        verificar = nivel.isBandera();
        if (verificar) {
            nivel.cambiarBandera();
            Thread hilo = new Thread(nivel);
            hilo.start();
        }
    }

    /*public void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        
        try {
            InputStream test = new FileInputStream("007JamesBondTheme.wav");        
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        MGP.start(loop);
    }
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelInicio.btIniciar)) {
            /*AudioClip sonido;
            sonido = java.applet.Applet.newAudioClip(www.youtube.com/watch?v=pqTcHtSSfUE&list=RDMMpqTcHtSSfUE&start_radio=1);
            sonido.play();*/
            // music();

            añadirNivel(1, nivel1, "1", 1);
            posicion = "nivel1";
        }
        if (e.getSource().equals(nivel1.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(1, nivel1, "1", 1);
            posicion = "nivel1";
        }
        if (e.getSource().equals(nivel2.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(3, nivel3, "3", 3);
            posicion = "nivel3";
        }
        if (e.getSource().equals(nivel3.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(4, nivel4, "4", 4);
            posicion = "nivel4";
        }
        if (e.getSource().equals(nivel4.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(5, nivel5, "5", 5);
            posicion = "nivel5";
        }
        if (e.getSource().equals(nivel5.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(6, nivel6, "6", 5);
            posicion = "nivel6";
        }
        if (e.getSource().equals(nivel6.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(7, nivel7, "7", 5);
            posicion = "nivel7";
        }
        if (e.getSource().equals(nivel7.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(8, nivel8, "8", 5);
            posicion = "nivel8";
        }
        if (e.getSource().equals(nivel8.getBtnCambioDeNivel())) {
            archivo.resetearDatos();
            añadirNivel(9, nivel9, "9", 5);
            posicion = "nivel9";
        }
        if (e.getSource().equals(reinicio)) {
            reinicio.setFocusable(false);
            try {
                switch (posicion) {
                    case "nivel1":
                        if (nivel1.isBandera() == true) {
                            nivel1.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel1, 1, 0);
                        break;
                    case "nivel2":
                        if (nivel2.isBandera()) {
                            nivel2.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel2, 2, 0);
                        break;
                    case "nivel3":
                        if (nivel3.isBandera()) {
                            nivel3.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel3, 3, 0);
                        break;
                    case "nivel4":
                        if (nivel4.isBandera()) {
                            nivel4.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel4, 4, 0);
                        break;
                    case "nivel5":
                        if (nivel5.isBandera()) {
                            nivel5.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel5, 4, 0);
                        break;
                    case "nivel6":
                        if (nivel6.isBandera()) {
                            nivel6.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel6, 4, 0);
                        break;
                    case "nivel7":
                        if (nivel7.isBandera()) {
                            nivel7.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel7, 4, 0);
                        break;
                    case "nivel8":
                        if (nivel8.isBandera()) {
                            nivel8.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel8, 4, 0);
                        break;
                    case "nivel9":
                        if (nivel9.isBandera()) {
                            nivel9.getPanel().setVisible(false);
                        }
                        resetearNivel(nivel9, 4, 0);
                        break;
                    default:
                }
            } catch (Exception ke) {
            }

        }
    }

    class PanelInicio extends JPanel {

        private JButton btIniciar = new JButton(new ImageIcon("play.jpg"));
        private JLabel logo = new JLabel(new ImageIcon("logo.jpg"));

        public PanelInicio() {
            setLayout(null);
            setBackground(Color.WHITE);
            btIniciar.setBounds(220, 450, 63, 64);
            logo.setBounds(90, 290, 346, 123);
            add(btIniciar);
            add(logo);
        }

    }

    public static void main(String[] args) {
        new Ventana();
    }
}
