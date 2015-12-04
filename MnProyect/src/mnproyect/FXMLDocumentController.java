/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ComboBox cb_opcion, cb_trunc, cb_paro;
    @FXML
    private Label lbl_metodos, lbl_first, lbl_b, lbl_f, lbl_n, lbl_tol, lbl_fprima;
    @FXML
    private Pane pane_Bi, pane_punto, pane_Newton;
    @FXML
    private TextField txt_aBi, txt_bBi, txt_fBi, txt_nBi,txt_nNewton,txt_tolBi, txt_tolNewton, txt_fprima;

    private void combo() {
        txt_tolBi.setDisable(true);
        txt_nBi.setDisable(true);
        pane_Bi.setVisible(false);
        pane_Newton.setVisible(false);
        pane_punto.setVisible(false);
        lbl_metodos.setVisible(true);
        cb_trunc.getItems().add("Truncamiento");
        cb_trunc.getItems().add("Redondeo");
        cb_trunc.setValue("Redondeo");
        cb_paro.getItems().add("Iteraciones");
        cb_paro.getItems().add("Tolerancia");
        cb_paro.setValue("Tolerancia");
        cb_opcion.getItems().add("Bisección");
        cb_opcion.getItems().add("Punto fijo");
        cb_opcion.getItems().add("Newton-Raphson");
        cb_opcion.getItems().add("Lagrange");
        cb_paro.setOnAction(a -> {
            if (cb_paro.getValue().equals("Tolerancia")) {
                txt_nBi.setDisable(true);
                txt_tolBi.setDisable(false);
            } else {
                txt_tolBi.setDisable(true);
                txt_nBi.setDisable(false);
            }
        });
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
                    //txt_a.setPromptText("Introduzca el valor inicial de X");

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
    
    @FXML
    private void bisecc() {
        String a = txt_aBi.getText().replace(" ", "");
        String b = txt_bBi.getText().replace(" ", "");
        String function = txt_fBi.getText().replace(" ", "");
        function = function.toUpperCase();
        String functiona = function.replace("X", a+"");
        if(!(a.matches("[0-9]*") && b.matches("[0-9]*")) || (a.isEmpty()||b.isEmpty() || function.isEmpty())){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("ENTRADA INVALIDA");
            alert.setTitle("ERROR");
            alert.setContentText("Los campos A y B deben ser solo numeros y f(x) debe ser una entrada válida");
            alert.showAndWait();
        }
        Biseccion bi = new Biseccion();
        bi.funcion(functiona);
    }
    
    
    private void parser(String function, double a){
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo();
    }

}
