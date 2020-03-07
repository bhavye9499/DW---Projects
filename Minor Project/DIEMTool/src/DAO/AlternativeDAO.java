package DAO;

import DAO.DAO;
import DecisonComponentsAndNodes.Alternative;
import DecisonComponentsAndNodes.DecisionComponent;

import java.util.ArrayList;

public class AlternativeDAO extends DAO {

	private static final String tableName = "Alternative";

	public void addAlternative(Alternative alternatives) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponents(alternatives, insertQuery);
	}

	public ArrayList<DecisionComponent> getAlternatives(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
		return getComponents(decisionId, selectQuery, "alternative");
	}

	public void deleteAlternative(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE alternative_id = ?";
		deleteComponents(id, deleteQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
