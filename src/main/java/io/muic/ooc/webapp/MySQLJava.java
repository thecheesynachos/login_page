package io.muic.ooc.webapp;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class MySQLJava {

	public static final String DATABASE_HOSTNAME = Optional.ofNullable(System.getenv("DATABASE_HOSTNAME")).orElse("localhost");
	public static final String DATABASE_PORT = Optional.ofNullable(System.getenv("DATABASE_PORT")).orElse("3306");

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = String.format("jdbc:mysql://%s:%s/logindata?useSSL=false&characterEncoding=UTF-8&user=root&password=12345", DATABASE_HOSTNAME, DATABASE_PORT);

	private Connection connection;
	private Statement statement;

	private static MySQLJava mySQLJava = new MySQLJava();

	public static MySQLJava getInstance() {
		return mySQLJava;
	}

	public MySQLJava() {

		System.err.println(String.format("My Database URL: %s", MYSQL_URL));

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

	public String getName(int id){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT name FROM logindata.users WHERE id=?;");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getString("name");
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String getUsername(int id){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT username FROM logindata.users WHERE id=?;");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getString("username");
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public int getId(String username){
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id FROM logindata.users WHERE username=?;");
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getInt("id");
		} catch(Exception e){
			e.printStackTrace();
			return 0;
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

	public boolean removeUser(int id){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM logindata.users WHERE id=?;");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean editUser(int id, String username, String password, String name){
		try {
			PreparedStatement ps = connection.prepareStatement
					("UPDATE logindata.users SET username=?, password=?, name=? WHERE id=?;");
			ps.setString(1, username);
			ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
			ps.setString(3, name);
			ps.setInt(4, id);
			ps.execute();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
