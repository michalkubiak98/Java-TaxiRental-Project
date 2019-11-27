package Controllers;

import Connection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    @FXML
    private JFXTextField rate;

    @FXML
    private JFXTextField driver;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton exit;

    @FXML
    private JFXButton dashboard;

    @FXML
    private JFXTextField make;

    @FXML
    private JFXTextArea daysAvailable;

    @FXML
    private JFXTextField taxiMobile;

    @FXML
    private JFXButton exitbtn;

    @FXML
    private JFXTextField taxiLocation;

    @FXML
    private Label allFieldsRequired;

    @FXML
    private Label success;


    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        handler = new DBHandler();
        allFieldsRequired.setVisible(false);
        success.setVisible(false);
    }

    @FXML
    void dashboardAction(ActionEvent event) throws IOException {

        // DASHBOARD ENTER

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Stage dash = new Stage();
        dash.initStyle(StageStyle.UNDECORATED);
        dash.setScene(new Scene(root));
        dash.show();
        dash.setResizable(false);

        //hide this window - stack overflow
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void submitAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        if (!rate.getText().equals("") && !driver.getText().equals("") && !make.getText().equals("") &&
                !daysAvailable.getText().equals("") && !taxiLocation.getText().equals("") && !taxiMobile.getText().equals("")) {


            String insert = "INSERT INTO taxis(driver,make,taxiLocation,mobile,rate,daysAvailable)"
                    + "VALUES(?,?,?,?,?,?);";

            //connect
            connection = handler.getConnection();
            pst = connection.prepareStatement(insert);
            //Get text from text boxes and radio buttons and place into the question marks
            pst.setString(1, driver.getText());
            pst.setString(2, make.getText());
            pst.setString(3, taxiLocation.getText());
            pst.setString(4, taxiMobile.getText());
            //float
            pst.setFloat(5, Float.parseFloat(rate.getText()));
            pst.setString(6, daysAvailable.getText());

            //execute query
            int x = pst.executeUpdate();

            //if query is executed
            if (x > 0) {

                success.setVisible(true);
                allFieldsRequired.setVisible(false);

                //CREATE TAXI OBJECT FOR TABLE
                //Not needed anymore
                //Taxi taxiTABLE = new Taxi();
                //taxiTABLE.setDriver(driver.getText());
                //taxiTABLE.setMake(make.getText());
                //taxiTABLE.setTaxiLocation(taxiLocation.getText());
                //taxiTABLE.setTaxiMobile(taxiMobile.getText());
                //taxiTABLE.setRate(Float.parseFloat(rate.getText()));
                //taxiTABLE.setDaysAvailable(daysAvailable.getText());

            }
        } else {
            //All Fields Required label
            allFieldsRequired.setVisible(true);


        }

    }


}
