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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author andres_002
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private ComboBox cb_opcion;
    @FXML private Label lbl_metodos;
    @FXML private Pane pane_Bi;
    
    private void combo(){
        pane_Bi.setVisible(false);
        lbl_metodos.setVisible(true);
        cb_opcion.getItems().add("Bisección");
        cb_opcion.getItems().add("Punto fijo");
        cb_opcion.getItems().add("Newton-Raphson");
        cb_opcion.getItems().add("Lagrange");
        cb_opcion.setOnAction(a ->{
            lbl_metodos.setVisible(false);
            pane_Bi.setVisible(false);
            String opc = cb_opcion.getValue().toString();
            switch(opc){
                case "Bisección":
                    pane_Bi.setVisible(true);
                    System.out.println("Biseccion");
                    break;
                case "Punto fijo":
                    System.out.println("Punto Fijo");
                    break;
                case "Newton-Raphson":
                    System.out.println("Newton");
                    break;
                case "Lagrange":
                    System.out.println("Lagrange");
                    break;
            }
            
                });
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo();
    }    
    
}
