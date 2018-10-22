package com.thomaskuenneth.webstartapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author tkuenneth
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;

    private int count;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        count += 1;
        update();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        count = 0;
        update();
    }    

    private void update() {
        String s;
        switch (count) {
            case 0:
                s = "Not clicked yet";
                break;
            case 1:
                s = "Clicked once";
                break;
            case 2:
                s = "Clicked twice";
                break;
            default:
                s = String.format("Clicked %d times", count);
        }
        label.setText(s);
    }
}
