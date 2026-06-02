package fr.univ_amu.iut.bonus7;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Exercice 7 (bonus) - Balle rebondissante.
 *
 * <p>Objectif : découvrir les animations JavaFX. Une balle rebondit verticalement dans un panneau.
 * Quatre boutons permettent de démarrer, mettre en pause, reprendre et arrêter l'animation. Un
 * slider contrôle la vitesse en temps réel.
 *
 * <p>Concepts : {@link TranslateTransition}, {@link Animation}, {@link Slider}, binding de
 * propriétés.
 */
public class BalleRebondissante extends Application {

  @Override
  public void start(Stage primaryStage) {
    // TODO exercice 7 : animer une balle rebondissante.
    //
    // 1. Créer un VBox comme conteneur racine.
    //
    // 2. Créer un HBox avec 4 boutons :
    // - "Démarrer" (id: btn-start) → appelle transition.playFromStart()
    // - "Pause" (id: btn-pause) → appelle transition.pause()
    // - "Reprendre"(id: btn-resume) → appelle transition.play()
    // - "Stop" (id: btn-stop) → appelle transition.stop()
    //
    // 3. Créer un Slider (id: slider-vitesse) avec min=0.1, max=5, valeur=1.
    // Ajouter un listener sur sa propriété value pour ajuster
    // transition.setRate(newValue).
    //
    // 4. Créer un Circle (id: balle) de rayon 15, couleur rouge.
    // Le placer dans un Pane (id: zone-animation).
    //
    // 5. Créer un TranslateTransition :
    // - durée 1000ms
    // - noeud = le cercle
    // - fromY = 10, toY = 400
    // - autoReverse = true
    // - cycleCount = INDEFINITE
    //
    // 6. Assembler : HBox + Slider + Pane dans le VBox.
    // Créer une Scene, l'attacher au Stage, afficher.

    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Button demarrer = new Button("Démarrer");
    Button pause = new Button("Pause");
    Button reprendre = new Button("Reprendre");
    Button stop = new Button("Stop");

    demarrer.setId("btn-start");
    pause.setId("btn-pause");
    reprendre.setId("btn-resume");
    stop.setId("btn-stop");
    hbox.getChildren().addAll(demarrer, pause, reprendre, stop);

    Circle cercle = new Circle(15);
    cercle.setFill(Color.RED);
    cercle.setId("balle");
    Pane pane = new Pane();
    pane.setId("zone-animation");
    pane.getChildren().addAll(cercle);

    TranslateTransition vitesse = new TranslateTransition(Duration.millis(1000), cercle);
    vitesse.setFromY(10);
    vitesse.setToY(400);
    vitesse.setAutoReverse(true);
    vitesse.setCycleCount(Animation.INDEFINITE);

    demarrer.setOnAction(
        e -> {
          vitesse.playFromStart();
        });
    pause.setOnAction(
        e -> {
          vitesse.pause();
        });
    reprendre.setOnAction(
        e -> {
          vitesse.play();
        });
    stop.setOnAction(
        e -> {
          vitesse.stop();
        });

    Slider slider = new Slider(0.1, 5, 1);
    slider.setId("slider-vitesse");
    slider
        .valueProperty()
        .addListener((observable, oldValue, newValue) -> vitesse.setRate(newValue.doubleValue()));

    vbox.getChildren().addAll(hbox, slider, pane);
    Scene scene = new Scene(vbox);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
