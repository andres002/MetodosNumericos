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
public class PosicionFalsaGUIController implements Initializable {
    
    @FXML private ComboBox serultOp;
    @FXML private TextField p0Entry,p1Entry,funcionEntry,nEntry,tolEntry,kEntry;
    @FXML private TextArea textArea;
    
    
    private BigDecimal p0,q0,q;
    private BigDecimal p1,q1,p;
    
    private Parser f = new Parser();
    private Parser f2 = new Parser();
     private Parser f3 = new Parser();
    private Double tol;
    private int n =0; 
    
    
    
     @FXML
    private void verficar() {
        if (!(p0Entry.getText().replaceAll(" ", "").equals("")
                || p1Entry.getText().replaceAll(" ", "").equals("")
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
                f2.k=aux;
                f3.k=aux;
            } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(" ha introducido el valor de K Mal");
                alert.setContentText("K recibe unicamente numeros enteros");
                alert.showAndWait();
                return;
            }
           
        }
        f.function = p0Entry.getText();
        p0 = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        f.X =p0+"";
        f2.function = p1Entry.getText();
        p1 = new BigDecimal(f2.Resultado(f2.Postfijo(f2.depurar())));
        f2.X = p1+"";
        f.function = funcionEntry.getText();
        f2.function = funcionEntry.getText();
        f3.function = funcionEntry.getText();
        this.tol = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
        this.n = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
        

        calculate();
    }
     
     public void calculate(){

        int i = 2;
        this.q0 = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        this.q1 = new BigDecimal(f2.Resultado(f2.Postfijo(f2.depurar())));
        BigDecimal aux,aux1,aux2,aux3;
        while (i <= this.n) {
            aux = new BigDecimal(f.redonTrunc(p1.subtract(p0)+""));
            aux1 = new BigDecimal(f.redonTrunc(q0.multiply(aux)+""));
            aux2 = new BigDecimal(f.redonTrunc(q1.subtract(q0)+""));
            MathContext m;
            if (f.opcion == 1) {
                m = new MathContext(f.k, RoundingMode.HALF_EVEN);//redondeo
            } else {
                m = new MathContext(f.k, RoundingMode.DOWN);//truncamiento
            }
            System.out.println("aux2 -------------> " + aux2);
            aux3 = new BigDecimal(f.redonTrunc(aux1.divide(aux2,m)+""));
           this.p = new BigDecimal(f.redonTrunc(p0.subtract(aux3)+""));
           aux=null;
           aux = new BigDecimal(f.redonTrunc(p.subtract(p1)+""));
            if (Math.abs(aux.doubleValue()) < this.tol) {
                textArea.setText(p.doubleValue() + "");
                return;
            }
            i = i +1;
            f3.X = p+"";
            this.q = new BigDecimal(f3.Resultado(f3.Postfijo(f3.depurar())));
            if (Double.parseDouble(f.redonTrunc(q.multiply(q1)+"")) < 0) {
                this.p0 = p1;
                this.q0 = q1;
            }
            this.p1 = p;
            this.q1 = q;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("El metodo ha fallado");
        alert.setContentText("El metodo falló despues de " + this.n + " Iteraciones");
        alert.showAndWait();
        textArea.setText(p + "");
        return;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serultOp.getItems().addAll(
        "Truncamiento","Redondeo","Todos los dígitos");
        serultOp.setValue("Redondeo");
    }   
    
    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }
    
    private void openWindowWithOption(String file) {
         
        Stage stage2 = (Stage) p0Entry.getScene().getWindow();
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
