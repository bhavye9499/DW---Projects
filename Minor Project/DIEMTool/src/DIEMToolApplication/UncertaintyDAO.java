package DIEMToolApplication;

import java.sql.*;
import java.util.ArrayList;

public class UncertaintyDAO extends DAO {

	private static final String tableName = "Uncertainty";
	private String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
	private String selectQuery = "SELECT * FROM " + tableName + " WHERE decision_id = ?";

	public void addUncertainty(Uncertainty uncertainty) {
		addComponents(uncertainty, insertQuery);
	}

	public ArrayList<DecisionComponent> getUncertaintys(String decisionId) {
		return getComponents(decisionId, selectQuery, "uncertainty");
	}

	public static String getTableName() {
		return tableName;
	}
}
