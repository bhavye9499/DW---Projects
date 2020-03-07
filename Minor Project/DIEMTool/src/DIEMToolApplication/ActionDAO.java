package DIEMToolApplication;

import java.util.ArrayList;

public class ActionDAO extends DAO {

	private static final String tableName = "Action";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addAction(Action action) {
		addComponents(action, insertQuery);
	}

	public ArrayList<DecisionComponent> getActions(String decisionId) {
		return getComponents(decisionId, selectQuery, "action");
	}

	public static String getTableName() {
		return tableName;
	}

}
