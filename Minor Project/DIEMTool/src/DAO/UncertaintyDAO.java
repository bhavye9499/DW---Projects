package DAO;

import DecisonComponents.DecisionComponent;
import DecisonComponents.Uncertainty;

import java.util.ArrayList;

public class UncertaintyDAO extends DAO {

	private static final String tableName = "Uncertainty";

	public void addUncertainty(Uncertainty uncertainty) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponent(uncertainty, insertQuery);
	}

	public ArrayList<DecisionComponent> getUncertainties(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = " + quotes(decisionId);
		return getComponents(selectQuery, "uncertainty");
	}

	public void deleteUncertainty(String id) {
		String deleteQuery1 = "DELETE FROM " + UncertaintyAttributeDAO.getTableName() + " WHERE uncertainty_id = " + quotes(id);
		String deleteQuery2 = "DELETE FROM " + tableName + " WHERE uncertainty_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery1);
		updateOrDeleteElement(deleteQuery2);
	}

	public void updateUncertainty(String id, String name) {
		String modifyQuery = "UPDATE " + tableName + " SET uncertainty_name = " + quotes(name) + " WHERE uncertainty_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}
}
