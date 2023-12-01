package com.dam.colision;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;



public class SnakeFX extends Application {

    private ArrayList<CuerpoSnake> serpiente = new ArrayList<CuerpoSnake>();
    private final int DIMENSION = 5;
    private CuerpoSnake[][] tablero;

    AnchorPane panel;

    // ObservableArray

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        //
//        int randomx = RandomGenerator.getDefault().nextInt(DIMENSION);
//        int randomy = RandomGenerator.getDefault().nextInt(DIMENSION);

        int inicial_x = 2;
        int inicial_y = 2;
        //enfoque sucesion de CuerpoSnakes
        serpiente.add(new CuerpoSnake(inicial_x, inicial_y, true));

//        // enfoque tablero
//        for (int i = 0; i < DIMENSION ; i++) {
//            for (int j = 0; j < DIMENSION ; j++) {
//                tablero[i][j]=new CuerpoSnake(i,j);
//            }
//        }
//        tablero[inicial_x][inicial_y]=new CuerpoSnake(inicial_x,inicial_y,true);

    }

    @Override
    public void start(Stage primaryStage) {
                try {
            //   Carga en root directamente lo cargado por el loader
            //   Parent panel = FXMLLoader.load(getClass().getResource("FXMLVistaLibro.fxml"));
            // o
            //   Crea un loader para el .fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SnakeFX.class.getResource("tablero.fxml"));
            //   Carga el nodo raiz panel
            panel = (AnchorPane) loader.load();
            //   Toma el fxcontroler enlazado al .fxml para tener su referencia
            TableroController controller = loader.getController();
            Scene scene = new Scene(panel);

            controller.iniciar(panel,serpiente, DIMENSION);

            primaryStage.setTitle("SalvioSnakeFX");
            primaryStage.setScene(scene);
            primaryStage.show();
            //

        } catch (IOException e) {
            System.out.println("Posible error de carga de .fxml");
            System.out.println(e.getStackTrace());

            };
        }


}
