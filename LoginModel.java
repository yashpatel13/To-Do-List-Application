package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
	
	Connection connection;
	
	public LoginModel(){
		try{
			this.connection = dbConnection.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(this.connection == null){
			System.exit(1);
		}
	}
	
	public boolean isDataConnected(){
		return this.connection != null;
	}
	
	public boolean isLogin(String user, String pass)throws Exception{
		
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM login where username = ? and password = ?";
		try{
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, user);
			pr.setString(2, pass);
			rs = pr.executeQuery();
			
			if(rs.next()){
				return true;
			}
			return false;
		}catch(SQLException e){
			return false;
		}
		finally{
			{
				pr.close();
				rs.close();
			}
		}
	}
}
