package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {


    @FXML
    private ImageView exit;

    @FXML
    private JFXButton bookNow;

    @FXML
    private Label nameLabel;

    @FXML
    private JFXButton myAcc;

    @FXML
    private JFXButton logOut;


    //Initialization
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        nameLabel.setText(loginController.usernameT);


    }

    @FXML
    void bookNowAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/bookNow.fxml"));
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

    @FXML
    void myAccAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/myAccount.fxml"));
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
    void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
