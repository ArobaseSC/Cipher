package org.arobase.cipher;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

public class Controller {

    @FXML
    protected void onSwitchCesarScene(MouseEvent event) {
        Application.loadScene("cesar.fxml", "Cesar");
    }

    @FXML
    protected void onSwitchVigenereScene(MouseEvent event) {
        Application.loadScene("vigenere.fxml", "Vigenere");
    }

    @FXML
    protected void onSwitchBrutForceScene(MouseEvent event){
        Application.loadScene("brutforce.fxml", "Brut-Force");
    }

    @FXML
    protected void onSwitchAffineScene(MouseEvent event){
        Application.loadScene("affine.fxml", "Affine");
    }

    @FXML
    protected void onSwitchCesarBlockScene(MouseEvent event){
        Application.loadScene("cesarblock.fxml", "Cesar-Block");
    }

    @FXML
    protected void onButtonEnter(Event event) {
        Button button = (Button) event.getTarget();
        button.setPrefWidth(165);
        button.setPrefHeight(110);
    }

    @FXML
    protected void onButtonExit(Event event){
        Button button = (Button) event.getTarget();
        button.setPrefWidth(150);
        button.setPrefHeight(100);
    }

    @FXML
    protected void onChiffrement(Event event) {
        TextArea textArea = (TextArea) Application.getScene().lookup("#result");
        TextField value = (TextField) Application.getScene().lookup("#value");
        TextField key = (TextField) Application.getScene().lookup("#key");

        switch (Application.getStage().getTitle()){
            case "Cesar" -> {
                textArea.setText(Algorithme.cesarChiffrement(value.getText(), Integer.parseInt(key.getText())));
            }
            case "Vigenere" -> {
                textArea.setText(Algorithme.vigenereChiffrement(value.getText(), key.getText()));
            }
            case "Cesar-Block" -> {
                textArea.setText(Algorithme.cesarBlockChiffrementParChiffre(value.getText(), Integer.parseInt(key.getText())));
            }
            case "Affine" -> {
                TextField key1 = (TextField) Application.getScene().lookup("#key1");
                textArea.setText(Algorithme.cesarAffineChiffrement(value.getText(), Integer.parseInt(key.getText()), Integer.parseInt(key1.getText())));
            }
        }
    }

    @FXML
    protected void onDechiffrement(Event event) {
        TextArea textArea = (TextArea) Application.getScene().lookup("#result");
        TextField value = (TextField) Application.getScene().lookup("#value");
        TextField key = (TextField) Application.getScene().lookup("#key");
        switch (Application.getStage().getTitle()) {
            case "Cesar" -> {
                textArea.setText(Algorithme.cesarDechiffrement(value.getText(), Integer.parseInt(key.getText())));
            }
            case "Vigenere" -> {
                textArea.setText(Algorithme.vigenereDechiffrement(value.getText(), key.getText()));
            }
            case "Brut-Force" -> {
                textArea.setText(Algorithme.brutForce(value.getText()).toString().replace("[", "").replace("]", "").replace(", ", "\n"));
            }
            case "Cesar-Block" -> {
                textArea.setText(Algorithme.cesarBlockDechiffrementParChiffre(value.getText(), Integer.parseInt(key.getText())));
            }
        }
    }

    @FXML
    protected void onBackMainMenu(Event event) {
        Application.loadScene("main.fxml", "Arobase-Chiffrement");
    }
}
