package com.dam.colision;

public class CuerpoSnake {

    private int x;
    private int y;
    private Contenido contenido;

    public CuerpoSnake(int x, int y, boolean cabeza){
        this.x =x;
        this.y =y;
        this.contenido=(cabeza)?Contenido.CABEZA:Contenido.CUERPO;   //true ->CABEZA: false-->CUERPO
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Contenido getContenido() {
        return contenido;
    }
    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }
    public Boolean getBoolContenido(){
        return (contenido.equals(Contenido.CABEZA));
    }
    public void setBoolContenido(Boolean cabeza){
        this.contenido=(cabeza)?Contenido.CABEZA:Contenido.CUERPO;
    }



}
