package DAO;

import DAO.DAO;
import DIEMToolApplication.Main;
import DecisonComponentsAndNodes.Decision;
import DecisonComponentsAndNodes.DecisionComponent;

import java.sql.*;
import java.util.ArrayList;

public class DecisionDAO extends DAO {

	private static final String tableName = "Decision";

	public void addDecision(Decision decision) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?)";
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
			String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
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

	public void deleteDecision(String decId) {
//		Deleting all alternatives
		ArrayList<DecisionComponent> components = Main.alternativeDAO.getAlternatives(decId);
		for (DecisionComponent component : components) {
			Main.alternativeDAO.deleteAlternative(component.getComponentId());
		}
//		Deleting all uncertainties
		components = Main.uncertaintyDAO.getUncertaintys(decId);
		for (DecisionComponent component : components) {
			Main.uncertaintyDAO.deleteUncertainty(component.getComponentId());
		}
//		Deleting all actions
		components = Main.actionDAO.getActions(decId);
		for (DecisionComponent component : components) {
			Main.actionDAO.deleteAction(component.getComponentId());
		}
//		Deleting all objectives
		components = Main.objectiveDAO.getObjectives(decId);
		for (DecisionComponent component : components) {
			Main.objectiveDAO.deleteObjective(component.getComponentId());
		}
//		Finally deleting the decision
		String deleteQuery = "DELETE FROM " + tableName + " WHERE decision_id = ?";
		deleteComponents(decId, deleteQuery);
	}

	public static String getTableName() {
		return tableName;
	}
}
