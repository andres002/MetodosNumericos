/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class BiseccionGUIController implements Initializable {

    @FXML
    private ComboBox serultOp;
    @FXML
    private TextField aEntry, bEntry, funcionEntry, nEntry, tolEntry, kEntry;
    @FXML
    private TextArea textArea;

    private Double resultado;
    //private Double x;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal fa, fp;
    private Parser f = new Parser();
    private Double tol;
    private int n = 0;

    /**
     * Metodo que se encarga de verificar que los campos de entrada hayan sido
     * rellenados de forma correcta
     *
     * @return void
     */
    @FXML
    private void verficar() {
        if (!(aEntry.getText().replaceAll(" ", "").equals("")
                || bEntry.getText().replaceAll(" ", "").equals("")
                || funcionEntry.getText().replaceAll(" ", "").equals("")
                || nEntry.getText().replaceAll(" ", "").equals("")
                || tolEntry.getText().replaceAll(" ", "").equals(""))) {
            f.function = funcionEntry.getText().trim();
            if (f.verifica_parentesis()) {

                try {
                    // double aux = Double.parseDouble(aEntry.getText().replaceAll(" ", ""));
                    //double aux2 = Double.parseDouble(bEntry.getText().replaceAll(" ", ""));
                    double aux3 = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
                    int aux4 = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));

                } catch (Exception E) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Ha introducido los datos mal");
                    alert.setContentText("Los Campos A y B reciben solamente numeros");
                    alert.showAndWait();
                }
                setVariables();
                
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Ha introducido los datos mal");
                    alert.setContentText("Verifique los parentesis");
                    alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No ha introducido uno o mas datos");
            alert.setContentText("Todos los campos deben ser rellenados");
            alert.showAndWait();
        }

    }

    /**
     * Metodo que se ocupa de asignar los valores a las expresiones segun las
     * entradas en los campos de entrada de datos
     *
     * @return void
     */
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
        if (!kEntry.getText().replaceAll(" ", "").equals("")) {
            int aux = 0;
            try {
                aux = Integer.parseInt(kEntry.getText().replaceAll(" ", ""));
                f.k = aux;
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(" ha introducido el valor de K Mal");
                alert.setContentText("K recibe unicamente numeros enteros");
                alert.showAndWait();
                return;
            }

        }
        f.function = bEntry.getText();
        b = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        System.out.println("b----" + b);
        f.function = aEntry.getText();
        a = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        f.X = a + "";
        System.out.println("primera X--------" + f.X);
        f.function = funcionEntry.getText();
        this.tol = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
        this.n = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));

        calculate();
    }

    /**
     * Metodo donde se implementa el algoritmo de Biseccion
     *
     * @return void
     */
    private void calculate() {
        int i = 1;
        fa = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        BigDecimal p;
        BigDecimal aux, aux2, num2;
        num2 = new BigDecimal("2");
        BigDecimal respaldo = new BigDecimal("0");
        while (i <= this.n) {
            aux = new BigDecimal(f.redonTrunc(b.subtract(a) + ""));
            MathContext m;
            if (f.opcion == 1) {
                m = new MathContext(f.k, RoundingMode.HALF_EVEN);//redondeo
            } else {
                m = new MathContext(f.k, RoundingMode.DOWN);//truncamiento
            }
            aux2 = new BigDecimal(f.redonTrunc(aux.divide(num2, m) + ""));
            p = new BigDecimal(f.redonTrunc(a.add(aux2) + ""));
            System.out.println("p----" + p);
            f.X = p + "";
            System.out.println("X------" + f.X);
            fp = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
            //System.out.println("fp------" + fp.doubleValue());
            //System.out.println("aux2-------" + aux2.doubleValue());
            //System.out.println("tol-------" + tol);
            if (fp.doubleValue() == 0 || aux2.doubleValue() < tol) {
                textArea.setText(Kernel.conversor(p));
                return;
            }
            i += 1;
            aux = null;
            aux = new BigDecimal(f.redonTrunc(fa.multiply(fp) + ""));
            if (aux.doubleValue() > 0) {
                this.a = p;
                fa = fp;
            } else {
                b = p;
            }
            respaldo = p;
        }

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("El metodo ha fallado");
        alert.setContentText("El metodo falló despues de " + this.n + " Iteraciones");
        alert.showAndWait();
        textArea.setText(Kernel.conversor(respaldo));
    }

    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }

    private void openWindowWithOption(String file) {

        Stage stage2 = (Stage) aEntry.getScene().getWindow();
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
