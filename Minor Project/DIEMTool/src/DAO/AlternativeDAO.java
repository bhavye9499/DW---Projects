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
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
		return getComponents(decisionId, selectQuery, "alternative");
	}

	public void deleteAlternative(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE alternative_id = ?";
		deleteNode(id, deleteQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
