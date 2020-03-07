package DIEMToolApplication;

import java.sql.*;
import java.util.ArrayList;

public class ObjectiveDAO extends DAO{

	private static final String tableName = "Objective";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addObjective(Objective objective) {
		addComponents(objective, insertQuery);
	}

	public ArrayList<DecisionComponent> getObjectives(String decisionId) {
		return getComponents(decisionId, selectQuery, "objective");
	}

	public static String getTableName() {
		return tableName;
	}

}
