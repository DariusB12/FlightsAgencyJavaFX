import controller.SignInController;
import exception.Message;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uitls.factory.Factory;

import java.util.Optional;

public class FlightsFXMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Welcome to Flights Agency");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("layoutsFXML/signin-window.fxml"));
        AnchorPane myPane = (AnchorPane) loader.load();

        //initialize the controller with the container that contains the services
        SignInController controller = loader.getController();
        controller.setResources(Factory.getContainer());

        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);

        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
