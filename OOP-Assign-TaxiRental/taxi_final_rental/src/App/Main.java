/**
 * This is a taxi rental system application
 * <p>
 * Michal Kubiak T00205890
 * <p>
 * Started on 08/11/19
 */


package App;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //set root as login page
        Parent root = FXMLLoader.load(getClass().getResource("../Interfaces/login.fxml"));
        //set primaryStage scene to root
        primaryStage.setScene(new Scene(root));
        //window is now undecorated meaning i have to add exit button and min button, drag later
        primaryStage.initStyle(StageStyle.UNDECORATED);


        //DRAGGABLE WINDOW FROM STACK
        // https://stackoverflow.com/questions/13206193/how-to-make-an-undecorated-window-movable-draggable-in-javafx/13460743

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });




        primaryStage.show();
        //Non Resizable Window
        primaryStage.setResizable(false);
    }
}


//Pull combo box days from taxi drivers and location