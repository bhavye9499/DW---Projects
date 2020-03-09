package DAO;

import DecisonComponents.Alternative;
import DecisonComponents.DecisionComponent;

import java.util.ArrayList;

public class AlternativeDAO extends DAO {

	private static final String tableName = "Alternative";

	public void addAlternative(Alternative alternatives) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponent(alternatives, insertQuery);
	}

	public ArrayList<DecisionComponent> getAlternatives(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = " + quotes(decisionId);
		return getComponents(selectQuery, "alternative");
	}

	public void deleteAlternative(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE alternative_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery);
	}

	public void updateAlternative(String id, String name) {
		String modifyQuery = "UPDATE " + tableName + " SET alternative_name = " + quotes(name) + " WHERE alternative_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
