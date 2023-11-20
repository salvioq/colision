package com.dam.colision;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.random.RandomGenerator;


public class SnakeFX extends Application {

    private ArrayList<CuerpoSnake> serpiente;
    private final int DIMENSION = 5;
    private CuerpoSnake[][] tablero;

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
        // enfoque tablero
        for (int i = 0; i < DIMENSION ; i++) {
            for (int j = 0; j < DIMENSION ; j++) {
                tablero[i][j]=new CuerpoSnake(i,j);
            }
        }

        //enfoque sucesion de CuerpoSnakes
        serpiente.add(new CuerpoSnake(inicial_x, inicial_y));

        tablero[inicial_x][inicial_y]=new CuerpoSnake(inicial_x,inicial_y);

    }

    @Override
    public void start(Stage primaryStage) {


    }
}
