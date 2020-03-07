package DIEMToolApplication;

import java.sql.*;

public class DecisionDAO {

	private String insertQuery = "INSERT INTO Decision VALUES (?, ?)";
	private String selectQuery = "SELECT decision_name FROM Decision WHERE decision_id = ?";

	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(JDBC.getJDBC_Driver());
    		con = DriverManager.getConnection(JDBC.getDB_URL() + JDBC.getDB_Name(), JDBC.getUser(), JDBC.getPass());
		} catch (Exception e) { e.printStackTrace(); }
		return con;
	}

	public void addDecision(Decision decision) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, decision.getDecisionId());
			pst.setString(2, decision.getDecisionName());
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

	public Decision getDecision(int decisionId) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		Decision decision = new Decision();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, Decision.getDecisionCode() + decisionId);
			ResultSet rs = pst.executeQuery();
			rs.next();
			decision.setDecisionId(Decision.getDecisionCode() + decisionId);
			decision.setDecisionName(rs.getString("decision_name"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return decision;
	}

	public int getNumberOfDecisions() {
		Connection con = getConnection();
		Statement st = null;
		int count = 0;
		try {
			st = con.prepareStatement(insertQuery);
			ResultSet rs = st.executeQuery("SELECT * FROM Decision");
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
