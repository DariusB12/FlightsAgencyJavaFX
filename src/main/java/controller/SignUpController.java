package controller;

import exception.Message;
import exception.ServiceException;
import exception.ValidationException;
import javafx.event.ActionEvent;
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

public class SignUpController implements Initializable {
    private Container container;
    private Stage stage;
    private UserService userService;
    public TextField textFieldUsername;
    public PasswordField textFieldPassword;
    public PasswordField textFieldConfirmPassword;
    public Button buttonSignUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setResources(Container container, Stage stage){
        this.stage = stage;
        this.container = container;
        this.userService = container.getUserService();
    }

    public void handleButtonSignUp(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        String confirmPassword= textFieldConfirmPassword.getText();

        if(username == "" || username == null || password == null || password == "" || !password.equals(confirmPassword)){
            Message.showError("Invalid username or password");
            return;
        }
        User user = new User(username);
        user.setPassword(password);
        try{
            User userCreated = userService.signUp(user);
            createNewMainWindowDialog(userCreated);
            this.stage.close();
        } catch (Exception e) {
            Message.showError(e.getMessage());
        }
    }
    private void createNewMainWindowDialog(User user) throws Exception{
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

}
