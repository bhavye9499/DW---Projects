package DAO;

import Attributes.Attribute;
import DecisonComponents.DecisionComponent;

import java.util.ArrayList;

public class ActionAttributeDAO extends DAO {

	private static final String tableName = "ActionAttribute";

	public void addActionAttribute(Attribute attribute) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?)";
		addAttribute(attribute, insertQuery);
	}

	public ArrayList<Attribute> getActionAttributes(String actionId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE action_id = " + quotes(actionId);
		return getAttributes(selectQuery, "action");
	}

	public void deleteActionAttribute(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE action_attribute_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery);
	}

	public void updateActionAttribute(String id, String name, String dataType) {
		String modifyQuery = "UPDATE " + tableName + " SET action_attribute_name = " + quotes(name) + ", action_attribute_datatype = " + quotes(dataType) + " WHERE action_attribute_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
