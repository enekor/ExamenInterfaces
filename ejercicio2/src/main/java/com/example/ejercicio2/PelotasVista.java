package com.example.ejercicio2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PelotasVista extends BorderPane {

    private Circle pelota1,pelota2;
    private Rectangle cubo1, cubo2,barrera,suelo;
    private StackPane pista;
    private Button boton;
    private PelotasController controller;

    PelotasVista(){
        init();
    }

    private void init(){

        pista = new StackPane();
        pista.setMinSize(0,0);

        pelota1 = new Circle();
        pelota1.setRadius(8);

        pelota2 = new Circle();
        pelota2.setRadius(8);

        cubo1 = new Rectangle();
        cubo1.heightProperty().bind(pista.heightProperty().divide(8));
        cubo1.widthProperty().bind(pista.heightProperty().divide(8));

        cubo2 = new Rectangle();
        cubo2.heightProperty().bind(pista.heightProperty().divide(8));
        cubo2.widthProperty().bind(pista.heightProperty().divide(8));

        boton = new Button();
        boton.setText("Salto");

        barrera = new Rectangle();
        barrera.heightProperty().bind(pista.heightProperty().divide(800));
        barrera.widthProperty().bind(pista.widthProperty());

        suelo = new Rectangle();
        suelo.heightProperty().bind(pista.heightProperty().divide(800));
        suelo.widthProperty().bind(pista.heightProperty().divide(8));



        boton.setEffect(new InnerShadow());

        alignment();

        this.setCenter(pista);


        controller = new PelotasController(pelota1,pelota2,cubo1,cubo2,pista,boton,barrera,suelo);
    }

    private void alignment(){
        pista.getChildren().addAll(pelota1,pelota2,cubo1,cubo2,boton,barrera,suelo);
        pista.setAlignment(pelota1, Pos.CENTER_LEFT);
        pista.setAlignment(cubo1, Pos.CENTER_LEFT);

        pista.setAlignment(pelota2,Pos.CENTER_RIGHT);
        pista.setAlignment(cubo2,Pos.CENTER_RIGHT);

        pista.setAlignment(boton,Pos.CENTER);

        pista.setAlignment(barrera,Pos.TOP_LEFT);

        pista.setAlignment(suelo,Pos.CENTER_LEFT);
    }
}
