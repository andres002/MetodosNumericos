/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.io.IOException;
import java.math.BigDecimal;
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
    
    
    @FXML private ComboBox serultOp;
    @FXML private TextField aEntry,bEntry,funcionEntry,nEntry,tolEntry;
    @FXML private TextArea textArea;

    private Double resultado;
    private Double x;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal fa;
    private Parser f = new Parser();
    private Double tol;
    
    @FXML
    private void verficar(){
        if (aEntry.getText().replaceAll(" ", "").equals("")||
                bEntry.getText().replaceAll(" ", "").equals("") ) {
            
        }
        try{
            double aux = Double.parseDouble(aEntry.getText().replaceAll(" ", ""));
            double aux2 = Double.parseDouble(bEntry.getText().replaceAll(" ", ""));
            setVariables();
        }catch(Exception E){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Ha introducido los datos mal");
            alert.setContentText("Los Campos A y B reciben solamente numeros");
            alert.showAndWait();
            
        }
        
    }
    
    private void setVariables(){
        //a = new BigDecimal(aEntry.getText().replaceAll(" ", ""));
        f.X = aEntry.getText().replaceAll(" ", "");
        b = new BigDecimal(bEntry.getText().replaceAll(" ", ""));
        f.function = funcionEntry.getText();
        
        switch(serultOp.getValue()+""){
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
    }
    
    private void core(){
        int i = 1;
        fa = new BigDecimal(f.Resultado(f.Postfijo(f.depurar())));
        
        
        
        
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
        "Truncamiento","Redondeo", "Todos los dígitos");
        serultOp.setValue("Redondeo");
        nEntry.setDisable(true);
    }    
    
}
