package DIEMToolApplication;

import java.sql.*;

public class DecisionDAO extends DAO {

	private static final String tableName = "Decision";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

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

	public static String getTableName() {
		return tableName;
	}
}
