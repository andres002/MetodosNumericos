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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlos
 */
public class LagrangeGUIController implements Initializable {

    @FXML
    private ComboBox serultOp, nodesOp;
    @FXML
    private TextField x0Entry, x1Entry, x2Entry, x3Entry, funcionEntry, nEntry, tolEntry, kEntry;
    @FXML
    private TextArea textArea;
    private Lagrangre lagrange = new Lagrangre();
    double a[][] = new double[4][2];
    ;
    private Parser f = new Parser();
    private Parser f1 = new Parser();
    private Parser f2 = new Parser();
    private Parser f3 = new Parser();

    @FXML
    private void verficar() {
        if (!funcionEntry.getText().replaceAll(" ", "").equals("")) {
            try {
                //double aux = Double.parseDouble(XEntry.getText().replaceAll(" ", ""));
                //double aux3 = Double.parseDouble(tolEntry.getText().replaceAll(" ", ""));
                //int aux4 = Integer.parseInt(nEntry.getText().replaceAll(" ", ""));
            } catch (Exception E) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Ha introducido los datos mal");
                alert.setContentText("");
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
                f1.opcion = 2;
                f2.opcion = 2;
                f3.opcion = 2;
                break;
            case "Redondeo":
                f.opcion = 1;
                f1.opcion = 1;
                f2.opcion = 1;
                f3.opcion = 1;
                break;
            case "Todos los dígitos":
                f.opcion = 3;
                f1.opcion = 3;
                f2.opcion = 3;
                f3.opcion = 3;
                break;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                 a[i][j]=0;
            }
        }
        
        if (!kEntry.getText().replaceAll(" ", "").equals("")) {
            int aux = 0;
            try {
                aux = Integer.parseInt(kEntry.getText().replaceAll(" ", ""));
                f.k = aux;
                f1.k = aux;
                f2.k = aux;
                f3.k = aux;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(" ha introducido el valor de K Mal");
                alert.setContentText("K recibe unicamente numeros enteros");
                alert.showAndWait();
                return;
            }
        }
        if (nodesOp.getValue().equals("2")) {
           
            f.function = x0Entry.getText();
            f.X = f.Resultado(f.Postfijo(f.depurar()));
            this.a[0][0] = Double.parseDouble(f.X);
            f1.function = x1Entry.getText();
            f1.X = f1.Resultado(f1.Postfijo(f1.depurar()));
            this.a[1][0] = Double.parseDouble(f1.X);

            f.function = funcionEntry.getText();
            f1.function = funcionEntry.getText();

            this.a[0][1] = Double.parseDouble(f.Resultado(f.Postfijo(f.depurar())));
            this.a[1][1] = Double.parseDouble(f1.Resultado(f1.Postfijo(f1.depurar())));

        } else if (nodesOp.getValue().equals("3")) {
            f.function = x0Entry.getText();
            f.X = f.Resultado(f.Postfijo(f.depurar()));
            this.a[0][0] = Double.parseDouble(f.X);
            f1.function = x1Entry.getText();
            f1.X = f1.Resultado(f1.Postfijo(f1.depurar()));
            this.a[1][0] = Double.parseDouble(f1.X);
            f2.function = x2Entry.getText();
            f2.X = f2.Resultado(f2.Postfijo(f2.depurar()));
            this.a[2][0] = Double.parseDouble(f2.X);

            f.function = funcionEntry.getText();
            f1.function = funcionEntry.getText();
            f2.function = funcionEntry.getText();

            this.a[0][1] = Double.parseDouble(f.Resultado(f.Postfijo(f.depurar())));
            this.a[1][1] = Double.parseDouble(f1.Resultado(f1.Postfijo(f1.depurar())));
            this.a[2][1] = Double.parseDouble(f2.Resultado(f2.Postfijo(f2.depurar())));

        } else {
            f.function = x0Entry.getText();
            f.X = f.Resultado(f.Postfijo(f.depurar()));
            this.a[0][0] = Double.parseDouble(f.X);
            f1.function = x1Entry.getText();
            f1.X = f1.Resultado(f1.Postfijo(f1.depurar()));
            this.a[1][0] = Double.parseDouble(f1.X);
            f2.function = x2Entry.getText();
            f2.X = f2.Resultado(f2.Postfijo(f2.depurar()));
            this.a[2][0] = Double.parseDouble(f2.X);
            f3.function = x3Entry.getText();
            f3.X = f3.Resultado(f3.Postfijo(f3.depurar()));
            this.a[3][0] = Double.parseDouble(f3.X);

            f.function = funcionEntry.getText();
            f1.function = funcionEntry.getText();
            f2.function = funcionEntry.getText();
            f3.function = funcionEntry.getText();
            this.a[0][1] = Double.parseDouble(f.Resultado(f.Postfijo(f.depurar())));
            this.a[1][1] = Double.parseDouble(f1.Resultado(f1.Postfijo(f1.depurar())));
            this.a[2][1] = Double.parseDouble(f2.Resultado(f2.Postfijo(f2.depurar())));
            this.a[3][1] = Double.parseDouble(f3.Resultado(f3.Postfijo(f3.depurar())));
        }

        core();
    }

    private void core() {

        int n = Integer.parseInt((String) nodesOp.getValue());
        System.out.println("el finaaaaaaaaalll------ " + lagrange.MetodoInterLagrange(n, this.a));
        textArea.setText(lagrange.MetodoInterLagrange(n, this.a));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nodesOp.getItems().addAll(
                "2", "3", 4);
        nodesOp.setValue("2");
        x0Entry.setDisable(false);
        x1Entry.setDisable(false);
        x2Entry.setDisable(true);
        x3Entry.setDisable(true);
        serultOp.getItems().addAll(
                "Truncamiento", "Redondeo", "Todos los dígitos");
        serultOp.setValue("Redondeo");
        nodesOp.setOnAction(event -> {
            if (nodesOp.getValue().equals("2")) {
                x0Entry.setDisable(false);
                x1Entry.setDisable(false);
                x2Entry.setDisable(true);
                x3Entry.setDisable(true);
            } else if (nodesOp.getValue().equals("3")) {
                x0Entry.setDisable(false);
                x1Entry.setDisable(false);
                x2Entry.setDisable(false);
                x3Entry.setDisable(true);
            } else {
                x0Entry.setDisable(false);
                x1Entry.setDisable(false);
                x2Entry.setDisable(false);
                x3Entry.setDisable(false);
            }
        });
    }

    @FXML
    public void openWindowBack(ActionEvent e) {
        openWindowWithOption("Principal.fxml");
    }

    private void openWindowWithOption(String file) {

        Stage stage2 = (Stage) x1Entry.getScene().getWindow();
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
