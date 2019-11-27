package Controllers;

import Connection.DBHandler;
import com.jfoenix.controls.JFXButton;
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

public class myAccountController implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton exitbtn;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField confirmPassword;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXButton update;

    @FXML
    private Label nameLabel11;

    @FXML
    private Label nameLabel111;

    @FXML
    private JFXButton dashboard;

    @FXML
    private Label allFieldsRequired;

    @FXML
    private Label header;


    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allFieldsRequired.setVisible(false);

        handler = new DBHandler();

    }

    @FXML
    void dashboardAction(ActionEvent event) throws IOException {
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
    void logOutAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/login.fxml"));
        Parent root = fxmlLoader.load();
        Stage dash = new Stage();
        dash.initStyle(StageStyle.UNDECORATED);
        dash.setScene(new Scene(root));
        dash.show();
        dash.setResizable(false);

        //hide this window - stack overflow
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    /**
     * UPDATES USER DATA
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void updateAction(ActionEvent event) throws Exception {

        header.setText("Profile");


        if (!username.getText().equals("") && !password.getText().equals("") && !confirmPassword.getText().equals("") && !location.getText().equals("")) {

            if (password.getText().equals(confirmPassword.getText())) {


                String update = "UPDATE users set username =  ? , password = ? , location = ? WHERE username = ? ";

                //connect
                connection = handler.getConnection();
                pst = connection.prepareStatement(update);

                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                pst.setString(3, location.getText());
                pst.setString(4, username.getText());


                //execute query
                int x = pst.executeUpdate();

                //if query is executed
                if (x > 0) {

                    header.setText("Update Successful!");
                    allFieldsRequired.setVisible(false);


                }


            } else {
                allFieldsRequired.setText("Passwords do not match");
                allFieldsRequired.setVisible(true);
            }


        } else {
            allFieldsRequired.setVisible(true);
        }


    }

}
