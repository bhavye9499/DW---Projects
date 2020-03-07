package DIEMToolApplication;

import java.sql.*;
import java.util.ArrayList;

public class UncertaintyDAO extends DAO {

	private static final String tableName = "Uncertainty";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addUncertainty(Uncertainty uncertainty) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, uncertainty.getUncertaintyId());
			pst.setString(2, uncertainty.getUncertaintyDecisionId());
			pst.setString(3, uncertainty.getUncertaintyName());
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

	public ArrayList<Uncertainty> getUncertaintys(String decisionId) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ArrayList<Uncertainty> uncertainties = new ArrayList<>();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, decisionId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				uncertainties.add(new Uncertainty(rs.getString("uncertainty_id"), rs.getString("decision_id"), rs.getString("uncertainty_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return uncertainties;
	}

	public static String getTableName() {
		return tableName;
	}
}
