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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

//IMPORT ALL CONTROLS FROM JFOENIX LIBRARY
//connection


//Initializable is a JAVAFX interface
public class loginController implements Initializable {


    static String usernameT;
    static String locationT;

    //Had to put to public because it would not see it in dashboardController for me to reset its value once logged out
    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    public String locationHeader;
    public JFXButton exit;
    /**
     * Components are initialized here
     */
    @FXML
    private JFXCheckBox rememberMe;
    @FXML
    private ImageView imageViewUsers;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signUp;
    @FXML
    private Label userNotExist;


    //Variables of types Connection,DBHandler,PreparedStatement
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    //Initialization
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set the spinner to not visible and set input color
        spinner.setVisible(false);
        userNotExist.setVisible(false);
        handler = new DBHandler();


    }

    /**
     * login Click Action
     */

    @FXML
    void loginAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {


        if (!username.getText().equals("") && !password.getText().equals("")) {


            spinner.setVisible(true);

            //Straight to admin interface

            if (username.getText().equals("admin") || username.getText().equals("Admin") &&
                    password.getText().equals("admin") || password.getText().equals("Admin")) {

                login.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/admin.fxml"));
                Parent root = fxmlLoader.load();
                Stage admin = new Stage();
                admin.initStyle(StageStyle.UNDECORATED);
                admin.setScene(new Scene(root));
                admin.show();
                admin.setResizable(false);

            } else {


                //throws sqlexception and class not found generated auto from getConnection()
                connection = handler.getConnection();

                //select
                String query = "SELECT * FROM users WHERE username=? and password=?";
                pst = connection.prepareStatement(query);
                //substitute
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                //results
                ResultSet rs = pst.executeQuery();

                //LOCATION


                int count = 0;

                //if found data increase count
                while (rs.next()) {
                    count = count + 1;
                    //SET LOCATION
                    locationT = rs.getString("location");

                }
                if (count == 1) {

                    //debugging
                    //System.out.print("Success");
                    spinner.setVisible(false);
                    userNotExist.setVisible(false);


                    //DISPLAY USERNAME IN DASHBOARD
                    usernameT = username.getText();


                    // DASHBOARD ENTER
                    login.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/dashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage dash = new Stage();
                    dash.initStyle(StageStyle.UNDECORATED);
                    dash.setScene(new Scene(root));
                    dash.show();
                    dash.setResizable(false);


                } else {
                    //debugging
                    userNotExist.setVisible(true);
                    spinner.setVisible(false);
                }

                //close connection
                connection.close();


            }
        } else {
            userNotExist.setVisible(true);
        }
    }


    /**
     * Sign Up Click Action
     */
    @FXML
    void signUpAction(ActionEvent event) throws IOException {

        //hides login
        login.getScene().getWindow().hide();

        // -- this was the first attempt at the code
        //Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/signUp.fxml"));
        // -- set root as the new scene
        //Scene scene = new Scene(root);
        //signUp.setScene(scene);

        //root file changes to signUp form
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/signUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage signUp = new Stage();
        signUp.initStyle(StageStyle.UNDECORATED);
        signUp.setScene(new Scene(root));
        signUp.show();
        signUp.setResizable(false);
    }


    /**
     * Exit button
     */
    @FXML
    void exitAction(ActionEvent event) {

        System.exit(0);
    }


}
