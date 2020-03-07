package DAO;

import DAO.DAO;
import DecisonComponentsAndNodes.Action;
import DecisonComponentsAndNodes.DecisionComponent;

import java.util.ArrayList;

public class ActionDAO extends DAO {

	private static final String tableName = "Action";

	public void addAction(Action action) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponents(action, insertQuery);
	}

	public ArrayList<DecisionComponent> getActions(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
		return getComponents(decisionId, selectQuery, "action");
	}

	public void deleteAction(String id) {
		String deleteQuery1 = "DELETE FROM " + AttributeDAO.getActionAttributeTableName() + " WHERE action_id = ?";
		String deleteQuery2 = "DELETE FROM " + tableName + " WHERE action_id = ?";
		deleteComponents(id, deleteQuery1);
		deleteComponents(id, deleteQuery2);
	}

	public static String getTableName() {
		return tableName;
	}

}
