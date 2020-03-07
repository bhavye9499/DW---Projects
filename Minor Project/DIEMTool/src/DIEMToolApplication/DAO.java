package DIEMToolApplication;

import java.sql.*;

public class DAO {

	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(JDBC.getJDBC_Driver());
    		con = DriverManager.getConnection(JDBC.getDB_URL() + JDBC.getDB_Name(), JDBC.getUser(), JDBC.getPass());
		} catch (Exception e) { e.printStackTrace(); }
		return con;
	}

	public int getNumberOfRows(String query) {
		Connection con = getConnection();
		Statement st = null;
		int count = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) count++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return count;
	}

}
