/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class NewtonRaphsonGUIController implements Initializable {
    
    @FXML private ComboBox IteraTol,serultOp;
    @FXML private TextField XEntry,funcionEntry,derivadaEntry,nEntry,tolEntry;
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
    
}
