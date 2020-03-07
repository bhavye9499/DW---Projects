package DIEMToolApplication;

import java.sql.*;
import java.util.ArrayList;

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

	public void addComponents(DecisionComponent component, String insertQuery) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, component.getComponentId());
			pst.setString(2, component.getComponentDecisionId());
			pst.setString(3, component.getComponentName());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	public ArrayList<DecisionComponent> getComponents(String decisionId, String selectQuery, String componentName) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ArrayList<DecisionComponent> components = new ArrayList<>();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, decisionId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				components.add(new DecisionComponent(rs.getString(componentName + "_id"), rs.getString("decision_id"), rs.getString(componentName + "_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return components;
	}

	public void deleteComponents(String decisionId, String deleteQuery, String componentName) {

	}
}
