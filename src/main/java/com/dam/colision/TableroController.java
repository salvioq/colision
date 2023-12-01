package com.dam.colision;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.controlsfx.glyphfont.FontAwesome;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableroController implements Initializable {

    //modelo
    private int i;// iteración
    private AnchorPane panel;
    private ArrayList<CuerpoSnake> serpiente;
    private Direction dirActual;
    private int DIMENSION;
    private boolean vivo;

    @FXML
    public GridPane tableroVista;
    @FXML
    public Label lbEstado;


    // constructor del controller, aqui se pueden inicializar variables
    public TableroController(){
        i=0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void iniciar(AnchorPane panel, ArrayList<CuerpoSnake> serpiente, int DIMENSION) {
        this.vivo = true;
        this.panel =panel;
        this.DIMENSION = DIMENSION;
        this.serpiente = serpiente;
        Scene s = panel.getScene();
        s.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            KeyCode k = key.getCode();
            if (k == KeyCode.W || k == KeyCode.UP) {
                dirActual = Direction.NORTE;
            }
            if (k == KeyCode.A || k == KeyCode.LEFT) {
                dirActual = Direction.OESTE;
            }
            if (k == KeyCode.S || k == KeyCode.DOWN) {
                dirActual = Direction.SUR;
            }
            if (k == KeyCode.D || k == KeyCode.RIGHT) {
                dirActual = Direction.ESTE;
            }
            avanzar();
        });
    }

    private void avanzar() {
        serpiente.get(0).setBoolContenido(false);
        int xx=serpiente.get(0).getX();
        int yy=serpiente.get(0).getY();
        switch (dirActual) {
            case NORTE -> {
                if (yy > 1) serpiente.add(new CuerpoSnake(xx,yy-1, true));
                else vivo=false;

            }
            case SUR -> {
                if (yy < DIMENSION) serpiente.add(new CuerpoSnake(xx,yy+1, true));
                else vivo=false;

            }
            case ESTE -> {
                if (xx < DIMENSION) serpiente.add(new CuerpoSnake(xx+1,yy, true));
                else vivo=false;

            }
            case OESTE -> {
                if (xx > 1) serpiente.add(new CuerpoSnake(xx-1, yy, true));
                else vivo=false;
            }

        }
        redibujar();
        if (!vivo) {
                // Alert
                Platform.exit();
                }
    }

    public void redibujar(){
        // panel
        lbEstado.setText("Iteración " + this.i + vivo) ;
        // log
        System.out.println("Iteración" + this.i + vivo) ;
        tableroVista.getChildren().clear();
        int lon=0;
        for (CuerpoSnake c : serpiente ) {
            Label l = new Label((c.getBoolContenido())?"@":"+");

            GridPane.setColumnIndex(l,c.getX());
            GridPane.setRowIndex(l,c.getY());
            tableroVista.getChildren().add(l);
            // log
            System.out.println("Serp Lon %1$d, X %2$d, Y %3$d" + lon++ +c.getX() + c.getY()+ c.getBoolContenido());
        }
    }

    public void cambiaDireccion(ActionEvent e) {
        Button b;
        b = (Button)e.getSource();

    }
}
