package DIEMToolApplication;

import java.sql.*;
import java.util.ArrayList;

public class AlternativeDAO extends DAO {

	private static final String tableName = "Alternative";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addAlternative(Alternative alternative) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, alternative.getAlternativeId());
			pst.setString(2, alternative.getAlternativeDecisionId());
			pst.setString(3, alternative.getAlternativeName());
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

	public ArrayList<Alternative> getAlternatives(String decisionId) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ArrayList<Alternative> alternatives = new ArrayList<>();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, decisionId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				alternatives.add(new Alternative(rs.getString("alternative_id"), rs.getString("decision_id"), rs.getString("alternative_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return alternatives;
	}

	public static String getTableName() {
		return tableName;
	}
}
