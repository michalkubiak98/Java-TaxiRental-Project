package Controllers;

import Connection.DBHandler;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

//connection


public class signUpController implements Initializable {


    @FXML
    private JFXTextField location;

    @FXML
    private JFXTextField Susername;

    @FXML
    private JFXPasswordField Spassword;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXRadioButton maleRadio;

    @FXML
    private JFXRadioButton femaleRadio;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton exit;

    @FXML
    private JFXSpinner spinner;


    @FXML
    private Label allFieldsRequiredSignUp;


    //Variables of types Connection,DBHandler,PreparedStatement
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinner.setVisible(false);
        allFieldsRequiredSignUp.setVisible(false);


        handler = new DBHandler();

    }


    /**
     * login Click Action
     */

    @FXML
    void loginAction(ActionEvent event) throws IOException {


        //switched the signUp show window code for login
        signUp.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/login.fxml"));
        Parent root = fxmlLoader.load();
        Stage signUp = new Stage();
        signUp.initStyle(StageStyle.UNDECORATED);
        signUp.setScene(new Scene(root));
        signUp.show();
        signUp.setResizable(false);

    }

    /**
     * Sign Up Click Action
     */
    @FXML

    //explain throws exceptions
    void signUpAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        spinner.setVisible(true);

        //VALIDATION WITH REG EXPRESSIONS MAYBE?


        if (!Susername.getText().equals("") && !Spassword.getText().equals("") && !location.getText().equals("") && !getGender().equals("other")) {


            //if fields not empty


            //Could not get to work
            //Same username validation
            //if (userExists()) {


            //Saving Data
            String insert = "INSERT INTO users(username,password,location,gender)"
                    + "VALUES(?,?,?,?);";

            //connect
            connection = handler.getConnection();
            pst = connection.prepareStatement(insert);
            //Get text from text boxes and radio buttons and place into the question marks
            pst.setString(1, Susername.getText());
            pst.setString(2, Spassword.getText());
            pst.setString(3, location.getText());
            pst.setString(4, getGender());

            //execute query
            int x = pst.executeUpdate();

            //if query is executed
            if (x > 0) {

                //Next Page (Login)

                //switched the signUp show window code for login show window
                signUp.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/login.fxml"));
                Parent root = null;

                //had to wrap into try catch cause it was causing problems
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage signUp = new Stage();
                signUp.initStyle(StageStyle.UNDECORATED);
                assert root != null;
                signUp.setScene(new Scene(root));
                signUp.show();
                signUp.setResizable(false);

            }
        } else {
            //All Fields Required label
            allFieldsRequiredSignUp.setVisible(true);
            spinner.setVisible(false);


        }
    }


    /**
     * Get Gender Method for Sign Up
     */
    private String getGender() {

        String gen;

        if (maleRadio.isSelected()) {
            gen = "male";
        } else if (femaleRadio.isSelected()) {
            gen = "female";
        } else if (femaleRadio.isSelected() && maleRadio.isSelected()) {
            gen = "both";
        } else {
            gen = "other";
        }

        return gen;
    }


    /**
     * Exit button
     */
    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);

    }


}
