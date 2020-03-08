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

	public ArrayList<DecisionComponent> getUncertaintys(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
		return getComponents(decisionId, selectQuery, "uncertainty");
	}

	public void deleteUncertainty(String id) {
		String deleteQuery1 = "DELETE FROM " + UncertaintyAttributeDAO.getUncertaintyAttributeTableName() + " WHERE uncertainty_id = ?";
		String deleteQuery2 = "DELETE FROM " + tableName + " WHERE uncertainty_id = ?";
		deleteNode(id, deleteQuery1);
		deleteNode(id, deleteQuery2);
	}

	public static String getTableName() {
		return tableName;
	}
}
