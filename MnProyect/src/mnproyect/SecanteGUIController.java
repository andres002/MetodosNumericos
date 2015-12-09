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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class SecanteGUIController implements Initializable {
    
    @FXML private ComboBox serultOp;
    @FXML private TextField x0Entry,x1Entry,funcionEntry,nEntry,tolEntry,kEntry;
    @FXML private TextArea textArea;
    
    private BigDecimal x1, fx1;
    private BigDecimal x0, fx0,x2;
    private Parser f = new Parser();
    private Parser f2 = new Parser();
    private Double tol;
    private int n =0; 
    
    
     /**
     * Metodo que se encarga de verificar que los campos de entrada hayan sido rellenados de forma correcta
     * @return void
     */
     @FXML
    private void verficar() {
        if (!(x0Entry.getText().replaceAll(" ", "").equals("")
                || x1Entry.getText().replaceAll(" ", "").equals("")
                || funcionEntry.getText().replaceAll(" ", "").equals("")
                || nEntry.getText().replaceAll(" ", "").equals("")
                || tolEntry.getText().replaceAll(" ", "").equals(""))) {
            try {
               // double aux = Double.parseDouble(aEntry.getText().replaceAll(" ", ""));
                //double aux2 = Double.parseDouble(bEntry.getText().replaceAll(" ", ""));
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
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No ha introducido uno o mas datos");
                alert.setContentText("Todos los campos deben ser rellenados");
                alert.showAndWait();
        }

    }
    
    /**
     * Metodo que se ocupa de asignar los valores a las expresiones segun las entradas en los campos de entrada de datos
     * @return void 
     */
    private void setVariables() {

        switch (serultOp.getValue() + "") {
            case "Truncamiento":
                f.opcion = 2;
                f2.opcion = 2;
                break;
            case "Redondeo":
                f.opcion = 1;
                f2.opcion = 1;
                break;
            case "Todos los dígitos":
                f.opcion = 3;
                f2.opcion = 3;
                break;
        }
        if(!kEntry.getText().replaceAll(" ", "").equals("")){
            int aux=0;
            try {
                aux =Integer.parseInt(kEntry.getText().replaceAll(" ", "")); 
                f.k =aux;
                f2.k =aux;
            } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(" ha introducido el valor de K Mal");
                alert.setContentText("K recibe unicamente numeros enteros");
                alert.showAndWait();
                return;
            }
           
        }
        f.function = x0Entry.getText();
        x0 = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        f2.function = x1Entry.getText();
        x1 = new BigDecimal(f2.Resultado(f2.Postfijo(f2.depurar())));
        f.X = x0+"";
        f2.X = x1+"";
        System.out.println("primera X--------" + f.X);
        f.function = funcionEntry.getText();
        f2.function = funcionEntry.getText();
        this.tol = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
        this.n = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
        

        calculate();
    }
    
    
     /**
     * Metodo donde se implementa el algoritmo de Newton Raphson
     * @return void 
     */
        public void calculate() {
        double respaldo = 0;
        int i = 0;
        BigDecimal aux,aux1,aux2,aux3;
        while (i <= this.n) {
            fx0 = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
            fx1 = new BigDecimal(f2.Resultado(f2.Postfijo(f2.depurar())));
            aux = new BigDecimal(f.redonTrunc(x1.subtract(x0)+""));
            aux2 = new BigDecimal(f.redonTrunc(fx1.subtract(fx0)+""));
             MathContext m;
            if (f.opcion == 1) {
                m = new MathContext(f.k, RoundingMode.HALF_EVEN);//redondeo
            } else {
                m = new MathContext(f.k, RoundingMode.DOWN);//truncamiento
            }
            aux3 = new BigDecimal(f.redonTrunc(aux.divide(aux2,m)+""));
            aux1 = new BigDecimal(f.redonTrunc(aux3.multiply(fx1)+""));
            x2 = new BigDecimal(f.redonTrunc(x1.subtract(aux1)+""));
            //System.out.println("EL VALOR DE X1 ES: "+x1);
            
            if(x2.doubleValue() < tol){
                textArea.setText(x1.doubleValue()+"");
                return;
            }
            x0 = x1;
            x1 = x2;
            f.X = x0+"";
            f2.X = x1+"";
            i++;
            }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("El metodo ha fallado");
        alert.setContentText("El metodo falló despues de " + this.n + " Iteraciones");
        alert.showAndWait();
        textArea.setText(x1 + "");
        return;

    }

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serultOp.getItems().addAll(
        "Truncamiento","Redondeo");
        serultOp.setValue("Redondeo");
    }    
    
    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }
    
    private void openWindowWithOption(String file) {
         
        Stage stage2 = (Stage) x0Entry.getScene().getWindow();
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
