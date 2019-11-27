package Controllers;


import Connection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class bookNowController implements Initializable {

    private static int IDinputFinal;
    public String bookdriver;
    public String bookmake;
    public String bookmobile;
    private float bookrate;


    //https://www.youtube.com/watch?v=AkymBF0U7yw
    //TABLE VIEW TUTORIAL
    private double price;
    @FXML
    private JFXButton myAcc;
    @FXML
    private JFXButton logOut;
    @FXML
    private JFXButton exitbtn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private JFXComboBox<String> pickDay;
    @FXML
    private TableView<Taxi> tableView;
    @FXML
    private TableColumn<Taxi, Integer> id;
    @FXML
    private TableColumn<Taxi, String> driver;
    @FXML
    private TableColumn<Taxi, String> make;
    @FXML
    private TableColumn<Taxi, String> Tlocation;
    @FXML
    private TableColumn<Taxi, String> mobile;
    @FXML
    private TableColumn<Taxi, Float> rate;
    @FXML
    private TableColumn<Taxi, String> days;
    @FXML
    private JFXTextField IDinput;
    @FXML
    private Label whereLabel;
    @FXML
    private JFXComboBox<String> DestinationCombo;
    @FXML
    private Label priceLabel;
    @FXML
    private Label Price;
    @FXML
    private JFXButton BookNow;


    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        DestinationCombo.setVisible(false);
        whereLabel.setVisible(false);


        priceLabel.setVisible(false);
        Price.setVisible(false);


        tableView.setVisible(false);


        // Treeview that shows specific taxi drivers when picked a date which would be converted into a day
        // which would be searched in the database for availability and the users location as well

        handler = new DBHandler();


        final ObservableList<Taxi> listView = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        driver.setCellValueFactory(new PropertyValueFactory<>("driver"));
        make.setCellValueFactory(new PropertyValueFactory<>("make"));
        Tlocation.setCellValueFactory(new PropertyValueFactory<>("Tlocation"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        days.setCellValueFactory(new PropertyValueFactory<>("days"));


        /**
         * COMBO BOX PICK DAY
         */


        //Populate pick a day combo box

        pickDay.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        pickDay.setOnAction(e -> {

            //upon clicking a day

            tableView.getItems().clear();

            DestinationCombo.setVisible(false);
            DestinationCombo.getItems().clear();
            whereLabel.setVisible(false);

            priceLabel.setVisible(false);
            Price.setVisible(false);


            String DAYS = pickDay.getValue();

            tableView.setVisible(true);
            //DISPLAY TAXIS ON SET DAY AND YOUR LOCATION

            String sql = "SELECT * FROM taxis WHERE daysAvailable LIKE ? and taxiLocation=?";
            try {


                connection = handler.getConnection();
                pst = connection.prepareStatement(sql);
                pst.setString(1, DAYS);
                //location gotten from login details
                pst.setString(2, loginController.locationT);
                ResultSet rs = pst.executeQuery();


                //populate table with the taxi drivers available on chosen day
                while (rs.next()) {
                    listView.add(new Taxi(rs.getString("taxiID"),
                            rs.getString("driver"),
                            rs.getString("make"),
                            rs.getString("taxiLocation"),
                            rs.getString("mobile"),
                            rs.getString("rate") + " $/h",
                            rs.getString("daysAvailable")));
                }
                tableView.setItems(listView);

            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
    }


    @FXML
    void exitAction(ActionEvent event) {

        System.exit(0);

    }


    @FXML
    void BookNowAction(ActionEvent event) {

        //GET ID IN TEXTFIELD

        IDinputFinal = Integer.parseInt(IDinput.getText());


        //LOOK FOR DATABASE WITH ID

        String sql = "SELECT * FROM taxis WHERE taxiID=?";

        try {

            connection = handler.getConnection();
            pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(IDinputFinal));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                bookrate = rs.getFloat("rate");
            }


        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        DestinationCombo.setVisible(true);
        whereLabel.setVisible(true);


        DestinationCombo.getItems().addAll("Killarney");
        DestinationCombo.getItems().addAll("Cork");
        DestinationCombo.getItems().addAll("Dublin");

        DestinationCombo.setOnAction(e -> {


            String DEST = DestinationCombo.getValue();

            if (DEST.equals("Killarney")) {

                price = bookrate * 33.8;
            }
            if (DEST.equals("Cork")) {

                price = bookrate * 115;
            }
            if (DEST.equals("Dublin")) {

                price = bookrate * 340;
            }

            //MORE VALIDATION ON DESTINATIONS
            //SET
            priceLabel.setVisible(true);
            Price.setText(String.format("%.2f", price) + " Euro");
            Price.setVisible(true);


        });


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


}
