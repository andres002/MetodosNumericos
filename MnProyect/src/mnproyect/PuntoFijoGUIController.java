/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class PuntoFijoGUIController implements Initializable {

    @FXML
    private ComboBox serultOp;
    @FXML
    private TextField XEntry, funcionEntry, nEntry, tolEntry, kEntry;
    @FXML
    private TextArea textArea;

    private BigDecimal inicialP;
    private double tol;
    private int n;
    private BigDecimal p;
    private Parser f = new Parser();

    @FXML
    private void verficar() {
        if (!(XEntry.getText().replaceAll(" ", "").equals("")
                || funcionEntry.getText().replaceAll(" ", "").equals("")
                || nEntry.getText().replaceAll(" ", "").equals("")
                || tolEntry.getText().replaceAll(" ", "").equals(""))) {
            try {
                //double aux = Double.parseDouble(XEntry.getText().replaceAll(" ", ""));
                double aux3 = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
                int aux4 = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
            } catch (Exception E) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Ha introducido los datos mal");
                alert.setContentText("Los Campos A y B reciben solamente numeros");
                alert.showAndWait();
            }
            setVariables();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No ha introducido uno o mas datos");
            alert.setContentText("Todos los campos deben ser rellenados");
            alert.showAndWait();
        }

    }

    private void setVariables() {
        switch (serultOp.getValue() + "") {
            case "Truncamiento":
                f.opcion = 2;
                break;
            case "Redondeo":
                f.opcion = 1;
                break;
            case "Todos los dígitos":
                f.opcion = 3;
                break;
        }
        if(!kEntry.getText().replaceAll(" ", "").equals("")){
            int aux=0;
            try {
                aux =Integer.parseInt(kEntry.getText().replaceAll(" ", "")); 
                f.k =aux;
            } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(" ha introducido el valor de K Mal");
                alert.setContentText("K recibe unicamente numeros enteros");
                alert.showAndWait();
                return;
            }
        }

        f.function = XEntry.getText();
        f.X = f.Resultado(f.Postfijo(f.depurar()));
        inicialP = new BigDecimal(f.X);
        //a = new BigDecimal(aEntry.getText().replaceAll(" ", ""));
        //b = new BigDecimal(f.redonTrunc(bEntry.getText().replaceAll(" ", "")));
        f.function = funcionEntry.getText();
        this.tol = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
        this.n = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
       

        calculate();
    }

    public void calculate() {
        double respaldo = 0;
        int i = 1;
        while (i <= this.n) {

            this.p = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
            BigDecimal aux, aux2;
            aux = new BigDecimal(Math.abs(Double.parseDouble(f.redonTrunc(p.subtract(inicialP) + ""))));
            if (aux.doubleValue() < this.tol) {
                textArea.setText(p.doubleValue() + "");
                return;
            }
            aux = null;
            i = i + 1;
            this.inicialP = this.p;

            f.X = this.p + "";
            respaldo = p.doubleValue();
            this.p = null;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("El metodo ha fallado");
        alert.setContentText("El metodo falló despues de " + this.n + " Iteraciones");
        alert.showAndWait();
        textArea.setText(respaldo + "");
        return;

    }

    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }

    private void openWindowWithOption(String file) {

        Stage stage2 = (Stage) XEntry.getScene().getWindow();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serultOp.getItems().addAll(
                "Truncamiento", "Redondeo", "Todos los dígitos");
        serultOp.setValue("Redondeo");
    }

}
