package DIEMToolApplication;

import java.util.ArrayList;

public class AlternativeDAO extends DAO {

	private static final String tableName = "Alternative";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addAlternative(Alternative alternatives) {
		addComponents(alternatives, insertQuery);
	}

	public ArrayList<DecisionComponent> getAlternatives(String decisionId) {
		return getComponents(decisionId, selectQuery, "alternative");
	}

	public static String getTableName() {
		return tableName;
	}

}
