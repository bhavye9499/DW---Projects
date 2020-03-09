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
		String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = " + quotes(decisionId);
		return getComponents(selectQuery, "objective");
	}

	public void deleteObjective(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE objective_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery);
	}

	public void updateObjective(String id, String name) {
		String modifyQuery = "UPDATE " + tableName + " SET objective_name = " + quotes(name) + " WHERE objective_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
