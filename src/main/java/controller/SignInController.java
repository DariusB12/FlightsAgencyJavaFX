package controller;

import exception.Message;
import exception.ServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import service.UserService;
import uitls.factory.Container;
import uitls.factory.Factory;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    private UserService userService;
    private Container container;

    @FXML
    public TextField textFieldUsername;
    @FXML
    public Button buttonSignIn;
    @FXML
    public Button buttonSignUp;
    @FXML
    public PasswordField passwordFieldPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setResources(Container container){
        this.container = container;
        this.userService = container.getUserService();
    }

    public void handleSignIn(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = passwordFieldPassword.getText();
        try{
            User user = userService.signIn(username,password);
            createNewDialog(user);
        } catch (ServiceException e) {
            Message.showError(e.getMessage());
            passwordFieldPassword.clear();
        }catch (Exception e){
            Message.showError("Error at opening the user window\n" + e.getMessage());
        }
    }

    private void createNewDialog(User user) throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Flights Agency - User: " + user.getUsername());

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/layoutsFXML/main-window.fxml"));
        AnchorPane myPane = (AnchorPane) loader.load();

        //initialize the controller with the container that contains the services
        MainController controller = loader.getController();
        controller.setResources(Factory.getContainer(),stage);

        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);

        stage.show();
    }

    public void handleSignUp(ActionEvent actionEvent) {
        //TODO: EXTRA SCHOOL REQUIREMENT ADD SIGNUP + ISUSERNAMETAKEN REAL TIME FEATURE WHEN SIGNUP
    }
}
