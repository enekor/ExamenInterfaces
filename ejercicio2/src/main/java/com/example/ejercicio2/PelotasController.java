package com.example.ejercicio2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PelotasController {

    private Circle pelota1,pelota2;
    private Rectangle cubo1, cubo2,barrera,suelo;
    private StackPane pista;
    private Button boton;
    private Timeline animacion;

    private boolean arriba = true;
    private String sound = "C:\\Windows\\Media\\notify.wav";

    public PelotasController(Circle pelota1, Circle pelota2, Rectangle cubo1, Rectangle cubo2, StackPane pista, Button boton,Rectangle barrera,Rectangle suelo) {
        this.pelota1 = pelota1;
        this.pelota2 = pelota2;
        this.cubo1 = cubo1;
        this.cubo2 = cubo2;
        this.pista = pista;
        this.boton = boton;
        this.barrera=barrera;
        this.suelo=suelo;

        initGame();
    }

    private void initGame() {

        boton.setOnMouseClicked(x->{
            try {
                sonido();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }

            rojoRandom();
            animacion.play();
        });

        animacion = new Timeline(new KeyFrame(Duration.millis(17), t->{
            detectarColision();
            moverPelota();
        }));
        animacion.setCycleCount(Animation.INDEFINITE);
    }

    private void sonido() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void detectarColision(){
        if(pelota1.getBoundsInParent().intersects(barrera.getBoundsInParent())){
            arriba = false;
        }
        if(pelota1.getBoundsInParent().intersects(suelo.getBoundsInParent())){
            animacion.stop();
            arriba = true;
        }
    }

    private void moverPelota(){
        if(arriba) {
            pelota1.setTranslateY(pelota1.getTranslateY() - 4);
            pelota2.setTranslateY(pelota2.getTranslateY() - 4);
        }else{
            pelota1.setTranslateY(pelota1.getTranslateY() + 4);
            pelota2.setTranslateY(pelota2.getTranslateY() + 4);
        }
    }

    private void rojoRandom(){
        int ran =(int) (Math.random()*10)+1;
        if(ran>=5){
            pelota1.setFill(Color.RED);
            pelota2.setFill(Color.BLACK);
        }else{
            pelota2.setFill(Color.RED);
            pelota1.setFill(Color.BLACK);
        }
    }

}
