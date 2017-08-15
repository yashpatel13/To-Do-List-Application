package todo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginapp.LoginController;

public class TodoController implements Initializable{

	private dbConnection dc;
	private ObservableList<TodoData> data;
	@FXML
	private ComboBox<priority> combo1;
	@FXML
	private ComboBox<status> combo2;
	@FXML
	private DatePicker date;
	@FXML
	private TextArea description;
	@FXML
	private TableView<TodoData> todotable;
	@FXML
	private TableColumn<TodoData, String> datecolumn;
	@FXML
	private TableColumn<TodoData, String> descriptioncolumn;
	@FXML
	private TableColumn<TodoData, String> prioritycolumn;
	@FXML
	private TableColumn<TodoData, String> statuscolumn;
	@FXML
	private Button logoutButton;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		this.combo1.setItems(FXCollections.observableArrayList(priority.values()));
		this.combo2.setItems(FXCollections.observableArrayList(status.values()));
		this.dc = new dbConnection();
		
	}
	
	@FXML
	private void deleteTodo(ActionEvent event){
		TodoData a=  todotable.getSelectionModel().getSelectedItem();
		
		try{
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM todo WHERE todo.date = ? AND todo.task = ?");
			
			stmt.setString(1, a.getDate());
			stmt.setString(2, a.getDescription());
			
			System.out.println(a.getDate());
			System.out.println(a.getDescription());
			System.out.println(a.getPriority());
			System.out.println(a.getStatus());
			stmt.execute();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void addTodo(ActionEvent event){
		LoginController u = new LoginController();
		String a = u.getUser();
		String sqlInsert = "INSERT INTO todo(date,task,priority,status,user) VALUES (?,?,?,?,?)";
		try{
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			
			stmt.setString(1, this.date.getEditor().getText());
			stmt.setString(2, this.description.getText());
			stmt.setString(3,this.combo1.getValue().toString());
			stmt.setString(4, this.combo2.getValue().toString());
			stmt.setString(5, a);
			
			stmt.execute();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void loadTodoData(ActionEvent event)throws SQLException{
		try {
			LoginController u = new LoginController();
			String a = u.getUser();
			Connection conn = dbConnection.getConnection();
			this.data = FXCollections.observableArrayList();
			/*
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM todo");*/
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todo WHERE todo.user = ?");
			stmt.setString(1, a);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				this.data.add(new TodoData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.datecolumn.setCellValueFactory(new PropertyValueFactory<TodoData, String>("Date"));
		this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory<TodoData, String>("Description"));
		this.prioritycolumn.setCellValueFactory(new PropertyValueFactory<TodoData, String>("Priority"));
		this.statuscolumn.setCellValueFactory(new PropertyValueFactory<TodoData, String>("Status"));
		//this.usercolumn.setCellValueFactory(new PropertyValueFactory<TodoData, String>("User"));
		this.todotable.setItems(null);
		this.todotable.setItems(this.data);
	}
	
	@FXML
	private void clearTodoData(ActionEvent event)throws SQLException{
		this.date.setValue(null);
		this.description.setText("");
		this.combo1.setValue(null);
		this.combo2.setValue(null);
	}

	@FXML
	private void logoutButton(ActionEvent event)throws SQLException{
		try{
			Stage stage = (Stage)this.logoutButton.getScene().getWindow();
			stage.close();
			Stage logStage = new Stage();
			FXMLLoader logLoader = new FXMLLoader();
			Pane logroot = (Pane)logLoader.load(getClass().getResource("/loginapp/Login.fxml").openStream());
			LoginController logController = (LoginController)logLoader.getController();
			Scene logscene = new Scene(logroot);
			logStage.setScene(logscene);
			logStage.setTitle("Login Dashboard");
			logStage.setResizable(true);
			logStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
}
