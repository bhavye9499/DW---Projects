package DAO;

import DAO.DAO;
import DecisonComponentsAndNodes.DecisionComponent;
import DecisonComponentsAndNodes.Uncertainty;

import java.util.ArrayList;

public class UncertaintyDAO extends DAO {

	private static final String tableName = "Uncertainty";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addUncertainty(Uncertainty uncertainty) {
		addComponents(uncertainty, insertQuery);
	}

	public ArrayList<DecisionComponent> getUncertaintys(String decisionId) {
		return getComponents(decisionId, selectQuery, "uncertainty");
	}

	public void deleteUncertainty(String id) {
		String deleteQuery1 = "DELETE FROM " + AttributeDAO.getUncertaintyAttributeTableName() + " WHERE uncertainty_id = ?";
		String deleteQuery2 = "DELETE FROM " + tableName + " WHERE uncertainty_id = ?";
		deleteComponents(id, deleteQuery1);
		deleteComponents(id, deleteQuery2);
	}

	public static String getTableName() {
		return tableName;
	}
}
