package com.thomaskuenneth.webstartapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;

/**
 *
 * @author tkuenneth
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Hyperlink link;

    private int count;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        count += 1;
        update();
    }

    @FXML
    private void handleHyperlinkAction(ActionEvent event) {
        try {
            BasicService bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
            bs.showDocument(new URL(link.getText()));
        } catch (Exception ex) {
            // we do not catch MalformedURLException and
            // UnavailableServiceException to avoid ClassNotFoundException
            // if UnavailableServiceException is not on classpath
        }
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
