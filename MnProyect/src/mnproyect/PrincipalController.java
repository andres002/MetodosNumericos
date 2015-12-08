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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andres_002
 */
public class PrincipalController implements Initializable {

    @FXML
    Button btn_bi, btn_pu, btn_new, btn_lag, btn_po, btn_sec, btn_acerca, btn_help;

    @FXML
    public void openWindowBi(ActionEvent e) {
        openWindowWithOption("BiseccionGUI.fxml");
    }
    
     @FXML
    public void openWindowPu(ActionEvent e) {
        //openWindowWithOption("PuntoGUI.fxml");
    }
     @FXML
    public void openWindowNew(ActionEvent e) {
        //openWindowWithOption("NewtonGUI.fxml");
    }
    
     @FXML
    public void openWindowLag(ActionEvent e) {
        //openWindowWithOption("LagrangeGUI.fxml");
    }
    @FXML
    public void openWindowPo(ActionEvent e) {
        //openWindowWithOption("PosicionGUI.fxml");
    }
    @FXML
    public void openWindowSec(ActionEvent e) {
        //openWindowWithOption("SecanteGUI.fxml");
    }
    @FXML
    public void openWindowAcerca(ActionEvent e) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ACERCA DE...");
        alert.setHeaderText("UNIVERSIDAD POLITÉCNICA DE CHIAPAS");
        alert.setContentText("\t METODOS NUMÉRICOS\n\nAndrés Aguilar Cruz 143385\nErnesto Sandoval Becerra 143374\n"
                + "Carlos Alejandro Zenteno Robles 143382\n Luis Fernando Herrera Pimentel 143402");  
        alert.showAndWait();
    }
    
    @FXML
    public void openWindowHelp(ActionEvent e) {
        //openWindowWithOption("HelpGUI.fxml");
    }
    

    private void openWindowWithOption(String file) {
         
        Stage stage2 = (Stage) btn_help.getScene().getWindow();
        stage2.close();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(file));
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
