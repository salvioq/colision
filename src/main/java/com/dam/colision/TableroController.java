package com.dam.colision;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableroController implements Initializable {

    //modelo
    private int lifeTicks;// iteraciones

    private SnakeFX mainApp;
    private Scene scene;
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
        lifeTicks =0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void iniciar(AnchorPane panel, ArrayList<CuerpoSnake> serpiente,
                        int DIMENSION, SnakeFX mainApp) {
        this.mainApp=mainApp;
        this.vivo = true;
        this.panel =panel;
        this.DIMENSION = DIMENSION;
        this.serpiente = serpiente;
        this.scene = panel.getScene();
        //tableroVista.setStyle ();
        tableroVista.setBackground(Background.fill(Color.LIGHTCYAN));
        tableroVista.setVgap(5.0);
        tableroVista.setHgap(5.0);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
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
            // avance tras pulsacion usuario
            avanzar();
            // posible creacion de fruta
        });
    }

    private void avanzar() {
        serpiente.get(0).setBoolContenido(false);
        int xx=serpiente.get(0).getX();
        int yy=serpiente.get(0).getY();
        switch (dirActual) {
            case NORTE -> {
                if (yy >= 1)  {
                    serpiente.add(0, new CuerpoSnake(xx,yy-1, true));
                    serpiente.remove(serpiente.size()-1);
                }
                else vivo=false;

            }
            case SUR -> {
                if (yy < DIMENSION) {
                    serpiente.add(0, new CuerpoSnake(xx,yy+1, true));
                    serpiente.remove(serpiente.size()-1);
                } else vivo=false;

            }
            case ESTE -> {
                if (xx < DIMENSION) {
                    serpiente.add(0,new CuerpoSnake(xx+1,yy, true));
                    serpiente.remove(serpiente.size()-1);
                } else vivo=false;

            }
            case OESTE -> {
                if (xx >= 1) {
                    serpiente.add(0, new CuerpoSnake(xx-1, yy, true));
                    serpiente.remove(serpiente.size()-1);
                }
                else vivo=false;
            }

        }
        redibujar();
        if (!vivo) {
            // Alert
            finalizar();

        }
    }

    public void redibujar(){
        // panel score
        lbEstado.setText("Iteración " + this.lifeTicks++ + vivo) ;
        // log
        System.out.println("Iteración" + this.lifeTicks + vivo) ;
        tableroVista.getChildren().clear();

        int lon=0;
        for (CuerpoSnake c : serpiente ) {
            Circle element = new Circle(10.0);
            element.setFill((c.getBoolContenido())?Color.RED:Color.BLUE);
//          con buttons...
//            new Button((c.getBoolContenido())?"@":"+");
//            l.setCenterShape(true);

            GridPane.setColumnIndex(element,c.getX());
            GridPane.setRowIndex(element,c.getY());
            tableroVista.getChildren().add(element);
            // log
            System.out.format("Serp Lon %d, X %d, Y %d \n ", lon++ ,c.getX() ,c.getY() );
        }
    }

    public void cambiaDireccion(ActionEvent e) {
        Button b;
        b = (Button)e.getSource();
        String sid = b.getId();
        System.out.println("sid"+ sid);
        switch (sid) {
            case "N" -> dirActual = Direction.NORTE;
            case "S" -> dirActual = Direction.SUR;
            case "O" -> dirActual = Direction.OESTE;
            case "E" -> dirActual = Direction.ESTE;
        }
        avanzar();
    }

    @FXML
    public void finalizar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(null);
        alert.setTitle("Finalizando");
        alert.setHeaderText("Serpiente " + vivo);
        alert.setContentText("Has durado "+ lifeTicks + "iteraciones.\n"+
                " Hasta la próxima...");
        alert.showAndWait();
        mainApp.stop();

    }
}
