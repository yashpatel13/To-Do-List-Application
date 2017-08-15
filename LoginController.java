package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import todo.TodoController;

public class LoginController implements Initializable {
	
	LoginModel loginModel = new LoginModel();
	@FXML
	private Label dbstatus;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button loginbutton;
	@FXML
	private Label loginstatus;
	
	static String userLog;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		if(this.loginModel.isDataConnected()){
			this.dbstatus.setText("Connected");
		}else{
			this.dbstatus.setText("Not Connected");
		}
		
	}
	
	public void login(ActionEvent event){
		try {
			if(this.loginModel.isLogin(this.username.getText(), this.password.getText())){
				Stage stage = (Stage)this.loginbutton.getScene().getWindow();
				stage.close();
				todoPage();
				
			}else{
				this.loginstatus.setText("Wrong Creditials");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void todoPage(){
		try{
			Stage todoStage = new Stage();
			FXMLLoader todoLoader = new FXMLLoader();
			Pane todoroot = (Pane)todoLoader.load(getClass().getResource("/todo/TODO.fxml").openStream());
			TodoController todoController = (TodoController)todoLoader.getController();
			userLog = this.username.getText();
			getUser();
			Scene todoscene = new Scene(todoroot);
			todoStage.setScene(todoscene);
			todoStage.setTitle("Todo List");
			todoStage.setResizable(true);
			todoStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public String getUser(){
		return userLog;
	}
}
