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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author andres_002
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox cb_opcion, cb_trunc;
    @FXML
    private Label lbl_metodos, lbl_first, lbl_b, lbl_f, lbl_n, lbl_tol, lbl_fprima;
    @FXML
    private Pane pane_Bi, pane_punto, pane_Newton;
    @FXML
    private TextField txt_a, txt_b, txt_f, txt_n, txt_tol, txt_fprima;
    
    private void combo() {
        pane_Bi.setVisible(false);
        pane_Newton.setVisible(false);
        pane_punto.setVisible(false);
        lbl_metodos.setVisible(true);
        cb_trunc.getItems().add("Truncamiento");
        cb_trunc.getItems().add("Redondeo");
        cb_trunc.setValue("Redondeo");
        cb_opcion.getItems().add("Bisección");
        cb_opcion.getItems().add("Punto fijo");
        cb_opcion.getItems().add("Newton-Raphson");
        cb_opcion.getItems().add("Lagrange");
        cb_opcion.setOnAction(a -> {
            lbl_metodos.setVisible(false);            
            String opc = cb_opcion.getValue().toString();
            pane_Bi.setVisible(false);
            pane_Newton.setVisible(false);
            pane_punto.setVisible(false);
            switch (opc) {
                case "Bisección":
                    pane_Bi.setVisible(true);
                    System.out.println("Biseccion");
                    break;
                case "Punto fijo":
                    pane_punto.setVisible(true);
                    txt_a.setPromptText("Introduzca el valor inicial de X");
                    
                    System.out.println("Punto Fijo");
                    break;
                case "Newton-Raphson":
                    pane_Newton.setVisible(true);
                    System.out.println("Newton");
                    break;
                case "Lagrange":
                    pane_Bi.setVisible(true);
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
