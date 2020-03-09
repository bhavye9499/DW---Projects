package DAO;

import DecisonComponents.Action;
import DecisonComponents.DecisionComponent;

import java.util.ArrayList;

public class ActionDAO extends DAO {

	private static final String tableName = "Action";

	public void addAction(Action action) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponent(action, insertQuery);
	}

	public ArrayList<DecisionComponent> getActions(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = " + quotes(decisionId);
		return getComponents(selectQuery, "action");
	}

	public void deleteAction(String id) {
		String deleteQuery1 = "DELETE FROM " + ActionAttributeDAO.getTableName() + " WHERE action_id = " + quotes(id);
		String deleteQuery2 = "DELETE FROM " + tableName + " WHERE action_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery1);
		updateOrDeleteElement(deleteQuery2);
	}

	public void updateAction(String id, String name) {
		String modifyQuery = "UPDATE " + tableName + " SET action_name = " + quotes(name) + " WHERE action_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
