package DAO;

import DecisonComponents.DecisionComponent;
import DecisonComponents.Objective;

import java.util.ArrayList;

public class ObjectiveDAO extends DAO {

	private static final String tableName = "Objective";

	public void addObjective(Objective objective) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
		addComponent(objective, insertQuery);
	}

	public ArrayList<DecisionComponent> getObjectives(String decisionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";
		return getComponents(decisionId, selectQuery, "objective");
	}

	public void deleteObjective(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE objective_id = ?";
		deleteNode(id, deleteQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
