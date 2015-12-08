/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class PosicionFalsaGUIController implements Initializable {
    
    @FXML private ComboBox IteraTol,serultOp;
    @FXML private TextField p0Entry,p1Entry,funcionEntry,nEntry,tolEntry;
    @FXML private TextArea textArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IteraTol.getItems().addAll(
        "Tolerancia","Iteraciones");
        IteraTol.setValue("Tolerancia");
        serultOp.getItems().addAll(
        "Truncamiento","Redondeo");
        serultOp.setValue("Redondeo");
        IteraTol.setOnAction(a -> {
            if (IteraTol.getValue().equals("Tolerancia")) {
                nEntry.setDisable(true);
                tolEntry.setDisable(false);
            } else {
                tolEntry.setDisable(true);
                nEntry.setDisable(false);
            }
        });
    }   
    
    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }
    
    private void openWindowWithOption(String file) {
         
        Stage stage2 = (Stage) p0Entry.getScene().getWindow();
        stage2.close();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(file));
        } catch (IOException ex) {
            Logger.getLogger(BiseccionGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    
}