package org.arobase.cipher;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
            case "Brut-Force" -> {
                textArea.setText(Algorithme.brutForce(value.getText()).toString());
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
        }
    }

    @FXML
    protected void onBackMainMenu(Event event) {
        Application.loadScene("main.fxml", "Arobase-Chiffrement");
    }
}
