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
        openWindowWithOption("PuntoFijoGUI.fxml");
    }

    @FXML
    public void openWindowNew(ActionEvent e) {
        openWindowWithOption("NewtonRaphsonGUI.fxml");
    }

    @FXML
    public void openWindowLag(ActionEvent e) {
        openWindowWithOption("LagrangeGUI.fxml");
    }

    @FXML
    public void openWindowPo(ActionEvent e) {
        openWindowWithOption("PosicionFalsaGUI.fxml");
    }

    @FXML
    public void openWindowSec(ActionEvent e) {
        openWindowWithOption("SecanteGUI.fxml");
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
        openWindowWithOption("AyudaGUI.fxml");
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
        btn_bi.setOnMouseEntered(a -> {
            btn_bi.setPrefWidth(250);
            btn_bi.setPrefHeight(100);
        });
        
         btn_bi.setOnMouseExited(a -> {
            btn_bi.setPrefWidth(238);
            btn_bi.setPrefHeight(85);
        });

        btn_lag.setOnMouseEntered(a -> {
            btn_lag.setPrefWidth(250);
            btn_lag.setPrefHeight(100);
        });
        
        btn_lag.setOnMouseExited(a -> {
            btn_lag.setPrefWidth(238);
            btn_lag.setPrefHeight(85);
        });

        btn_new.setOnMouseEntered(a -> {
            btn_new.setPrefWidth(250);
            btn_new.setPrefHeight(100);
        });
        
         btn_new.setOnMouseExited(a -> {
            btn_new.setPrefWidth(235);
            btn_new.setPrefHeight(85);
        });

        btn_po.setOnMouseEntered(a -> {
            btn_po.setPrefWidth(250);
            btn_po.setPrefHeight(100);
        });
        
        btn_po.setOnMouseExited(a -> {
            btn_po.setPrefWidth(235);
            btn_po.setPrefHeight(85);
        });

        btn_pu.setOnMouseEntered(a -> {
            btn_pu.setPrefWidth(250);
            btn_pu.setPrefHeight(100);
        });
        
        btn_pu.setOnMouseExited(a -> {
            btn_pu.setPrefWidth(235);
            btn_pu.setPrefHeight(85);
        });

        btn_sec.setOnMouseEntered(a -> {
            btn_sec.setPrefWidth(250);
            btn_sec.setPrefHeight(100);
        });
        
         btn_sec.setOnMouseExited(a -> {
            btn_sec.setPrefWidth(235);
            btn_sec.setPrefHeight(85);
        });
    }

}
