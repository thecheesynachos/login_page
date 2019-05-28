package io.muic.ooc.webapp;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJava {

	enum TestTableColumns {
		username,
		password,
		name
	}

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://localhost:1150/logindata?user=user&password=12345";

	private Connection connection;
	private Statement statement;

	private static MySQLJava mySQLJava = new MySQLJava();

	public static MySQLJava getInstance() {
		return mySQLJava;
	}

	public MySQLJava() {

		try {
			Class.forName(MYSQL_DRIVER);
			connection = DriverManager.getConnection(MYSQL_URL);
			statement = connection.createStatement();
		} catch(Exception e){
			e.printStackTrace();
		}
	}


	private void close() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean hasUser(String user){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM logindata.users WHERE username=?;");
			ps.setString(1, user);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.isBeforeFirst();
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean loginSuccess(String user, String pwd){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT password FROM logindata.users WHERE username=?;");
			ps.setString(1, user);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.isBeforeFirst()) {
				resultSet.next();
				String hashedPassword = resultSet.getString("password");
				return BCrypt.checkpw(pwd, hashedPassword);
			} else{
				return false;
			}
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public String getName(String user){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT name FROM logindata.users WHERE username=?;");
			ps.setString(1, user);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getString("name");
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean addNewUser(String username, String pwd, String name){
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO logindata.users VALUES (DEFAULT,?,?,?);");
			ps.setString(1, username);
			ps.setString(2, pwd);
			ps.setString(3, name);
			ps.execute();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet getUserData(){
		try{
			return statement.executeQuery("SELECT id, username, name FROM logindata.users;");
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeUser(String username){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM logindata.users WHERE username=?;");
			ps.setString(1, username);
			ps.execute();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
