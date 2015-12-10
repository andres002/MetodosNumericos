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
public class NewtonRaphsonGUIController implements Initializable {
    
    @FXML private ComboBox serultOp;
    @FXML private TextField XEntry,funcionEntry,derivadaEntry,nEntry,tolEntry,kEntry;
    @FXML private TextArea textArea;
    
    
    private BigDecimal inicialP;
    private double tol;
    private int n;
    private BigDecimal p;
    private Parser f = new Parser();
    private Parser f2 = new Parser();
    private Parser f3 = new Parser();
    
    
    
    
    /**
     * Metodo que se encarga de verificar que los campos de entrada hayan sido rellenados de forma correcta
     * @return void
     */
     @FXML
    private void verficar() {
        if (!(XEntry.getText().replaceAll(" ", "").equals("")
                || derivadaEntry.getText().replaceAll(" ", "").equals("")
                || funcionEntry.getText().replaceAll(" ", "").equals("")
                || nEntry.getText().replaceAll(" ", "").equals("")
                || tolEntry.getText().replaceAll(" ", "").equals(""))) {
            f.function = funcionEntry.getText().trim();
            if (f.verifica_parentesis()) {
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
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Ha introducido los datos mal");
                alert.setContentText("Verifique los parentesis");
                alert.showAndWait();
            }
            
        } else {
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
                f3.opcion = 2;
                break;
            case "Redondeo":
                f.opcion = 1;
                f2.opcion = 1;
                f3.opcion = 1;
                break;
            case "Todos los dígitos":
                f.opcion = 3;
                f2.opcion = 3;
                f3.opcion = 3;
                break;
        }
        if(!kEntry.getText().replaceAll(" ", "").equals("")){
            int aux=0;
            try {
                aux =Integer.parseInt(kEntry.getText().replaceAll(" ", "")); 
                f.k =aux;
                f2.k =aux;
                f3.k =aux;
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
        f2.X = f.Resultado(f.Postfijo(f.depurar()));//esta es la funcion de X
        f3.X =  f.Resultado(f.Postfijo(f.depurar()));//es es la derivada
        inicialP = new BigDecimal(f2.X);
        //a = new BigDecimal(aEntry.getText().replaceAll(" ", ""));
        //b = new BigDecimal(f.redonTrunc(bEntry.getText().replaceAll(" ", "")));
        f2.function = funcionEntry.getText();
        f3.function = derivadaEntry.getText();
        this.tol = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
        this.n = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
       

        calculate();
    }
    
    /**
     * Metodo donde se implementa el algoritmo de Newton Raphson
     * @return void 
     */
    private void calculate(){
        int i =1;
        BigDecimal aux,aux1,aux2;
        BigDecimal respaldo = new BigDecimal("0");
        while(i<=n){
            aux = new BigDecimal(f2.Resultado(f2.Postfijo(f2.depurar())));//resultado de la funcion de x
            aux1 = new BigDecimal(f3.Resultado(f3.Postfijo(f3.depurar())));//resultado de la funcion de la derivada de X
            //System.out.println("aux1-------- " + aux1);
            //System.out.println("aux---------- " + aux.divide(aux1));
             MathContext m;
            if (f3.opcion == 1) {
                m = new MathContext(f3.k, RoundingMode.HALF_EVEN);//redondeo
            } else {
                m = new MathContext(f3.k, RoundingMode.DOWN);//truncamiento
            }
            aux2 = new BigDecimal(f3.redonTrunc(aux.divide(aux1,m)+""));//división de la función sobre su derivada
            this.p = new BigDecimal(f3.redonTrunc(inicialP.subtract(aux2)+""));//valor de P
            aux= null;
            aux =  new BigDecimal(f3.redonTrunc(p.subtract(inicialP)+""));
            if (Math.abs(aux.doubleValue()) < tol) {
                textArea.setText(Kernel.conversor(p));
                return;
            }
            i = i+1;
            this.inicialP = this.p;
            f2.X = this.p + "";
            f3.X =  this.p + "";
            respaldo = p;
            this.p = null;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("El metodo ha fallado");
        alert.setContentText("El metodo falló despues de " + this.n + " Iteraciones");
        alert.showAndWait();
        textArea.setText(Kernel.conversor(respaldo));
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
        "Truncamiento","Redondeo");
        serultOp.setValue("Redondeo");
    }    
    
}
