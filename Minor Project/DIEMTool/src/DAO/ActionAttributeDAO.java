package DAO;

import Attributes.Attribute;
import DecisonComponents.DecisionComponent;

import java.util.ArrayList;

public class ActionAttributeDAO extends DAO {

	private static final String actionAttributeTableName = "ActionAttribute";

	public void addActionAttribute(Attribute attribute) {
		String insertQuery = "INSERT INTO " + actionAttributeTableName + " VALUES (?, ?, ?, ?)";
		addAttribute(attribute, insertQuery);
	}

	public ArrayList<Attribute> getActionAttributes(String actionId) {
		String selectQuery = "SELECT * FROM " + actionAttributeTableName + " WHERE action_id = ?";
		return getAttributes(actionId, selectQuery, "action");
	}

	public void deleteActionAttribute(String id) {
		String deleteQuery = "DELETE FROM " + actionAttributeTableName + " WHERE action_attribute_id = ?";
		deleteNode(id, deleteQuery);
	}

	public static String getActionAttributeTableName() {
		return actionAttributeTableName;
	}
}
