package DIEMToolApplication;

import java.sql.*;

public class JDBC {

//	JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "DIEMTool";

//	Database credentials
	private static String User;
	private static String Pass;

	public static void init(String user, String pass) {
//		Setting the database credentials
		JDBC.setUser(user);
		JDBC.setPass(pass);
		JDBC jdbc = new JDBC();
		jdbc.setupDatabase();
		jdbc.setupTables();
	}

//	Creating Database if it doesn't exist already
	public void setupDatabase() {
		Connection con = null;
		Statement st = null;
    	try {
    		Class.forName(JDBC_DRIVER);
    		con = DriverManager.getConnection(DB_URL, User, Pass);
    		st = con.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

//	Creating required tables if they don't exist already
	public void setupTables() {
		Connection con = null;
		Statement st = null;
		try {
    		Class.forName(JDBC_DRIVER);
    		con = DriverManager.getConnection(DB_URL + DB_NAME, User, Pass);
    		st = con.createStatement();
    		String sql;

//    		Creating Decision Table
			sql = "CREATE TABLE IF NOT EXISTS Decision ("
					+ "decision_id varchar(10) NOT NULL,"
					+ "decision_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (decision_id))";
			st.executeUpdate(sql);

//			Creating Alternative Table
			sql = "CREATE TABLE IF NOT EXISTS Alternative ("
					+ "alternative_id varchar(10) NOT NULL,"
					+ "decision_id varchar(10) NOT NULL,"
					+ "alternative_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (alternative_id),"
					+ "FOREIGN KEY f_decision_id(decision_id) "
					+ "REFERENCES Decision(decision_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

//			Creating Action Table
			sql = "CREATE TABLE IF NOT EXISTS Action ("
					+ "action_id varchar(10) NOT NULL,"
					+ "decision_id varchar(10) NOT NULL,"
					+ "action_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (action_id),"
					+ "FOREIGN KEY f_decision_id(decision_id) "
					+ "REFERENCES Decision(decision_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

//			Creating ActionAttribute Table
			sql = "CREATE TABLE IF NOT EXISTS ActionAttribute ("
					+ "action_attribute_id varchar(10) NOT NULL,"
					+ "action_id varchar(10) NOT NULL,"
					+ "action_attribute_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (action_attribute_id),"
					+ "FOREIGN KEY f_action_id(action_id) "
					+ "REFERENCES Action(action_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

//			Creating Uncertainty Table
			sql = "CREATE TABLE IF NOT EXISTS Uncertainty ("
					+ "uncertainty_id varchar(10) NOT NULL,"
					+ "decision_id varchar(10) NOT NULL,"
					+ "uncertainty_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (uncertainty_id),"
					+ "FOREIGN KEY f_decision_id(decision_id) "
					+ "REFERENCES Decision(decision_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

//			Creating UncertaintyAttribute Table
			sql = "CREATE TABLE IF NOT EXISTS UncertaintyAttribute ("
					+ "uncertainty_attribute_id varchar(10) NOT NULL,"
					+ "uncertainty_id varchar(10) NOT NULL,"
					+ "uncertainty_attribute_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (uncertainty_attribute_id),"
					+ "FOREIGN KEY f_uncertainty_id(uncertainty_id) "
					+ "REFERENCES Uncertainty(uncertainty_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

//			Creating Objective Table
			sql = "CREATE TABLE IF NOT EXISTS Objective ("
					+ "objective_id varchar(10) NOT NULL,"
					+ "decision_id varchar(10) NOT NULL,"
					+ "objective_name varchar(50) NOT NULL,"
					+ "PRIMARY KEY (objective_id),"
					+ "FOREIGN KEY f_decision_id(decision_id) "
					+ "REFERENCES Decision(decision_id) "
					+ "ON UPDATE CASCADE "
					+ "ON DELETE RESTRICT)";
			st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	public static String getJDBC_Driver() {
		return JDBC_DRIVER;
	}

	public static String getDB_URL() {
		return DB_URL;
	}

	public static String getDB_Name() {
		return DB_NAME;
	}

	public static String getUser() {
		return User;
	}

	public static void setUser(String user) {
		User = user;
	}

	public static String getPass() {
		return Pass;
	}

	public static void setPass(String pass) {
		Pass = pass;
	}
}
