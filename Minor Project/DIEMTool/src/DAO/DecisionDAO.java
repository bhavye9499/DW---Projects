package DAO;

import DIEMToolApplication.Main;
import DecisonComponents.Decision;
import DecisonComponents.DecisionComponent;

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

	public Decision getDecision(int id) {
		Connection con = getConnection();
		Statement st = null;
		Decision decision = new Decision();
		try {
			String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = " + quotes(Decision.getDecisionCode() + id);
			st = con.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			rs.next();
			decision.setDecisionId(Decision.getDecisionCode() + id);
			decision.setDecisionName(rs.getString("decision_name"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return decision;
	}

	public void deleteDecision(String id) {
//		Deleting all alternatives
		ArrayList<DecisionComponent> components = Main.alternativeDAO.getAlternatives(id);
		for (DecisionComponent component : components) {
			Main.alternativeDAO.deleteAlternative(component.getComponentId());
		}
//		Deleting all uncertainties
		components = Main.uncertaintyDAO.getUncertainties(id);
		for (DecisionComponent component : components) {
			Main.uncertaintyDAO.deleteUncertainty(component.getComponentId());
		}
//		Deleting all actions
		components = Main.actionDAO.getActions(id);
		for (DecisionComponent component : components) {
			Main.actionDAO.deleteAction(component.getComponentId());
		}
//		Deleting all objectives
		components = Main.objectiveDAO.getObjectives(id);
		for (DecisionComponent component : components) {
			Main.objectiveDAO.deleteObjective(component.getComponentId());
		}
//		Finally deleting the decision
		String deleteQuery = "DELETE FROM " + tableName + " WHERE decision_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery);
	}

	public void updateDecision(String id, String name) {
		String modifyQuery = "UPDATE " + tableName + " SET decision_name = " + quotes(name) + " WHERE decision_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}
}
