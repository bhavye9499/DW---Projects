package DAO;

import Attributes.Attribute;
import java.util.ArrayList;

public class UncertaintyAttributeDAO extends DAO {

	private static final String uncertaintyAttributeTableName = "UncertaintyAttribute";

	public void addUncertaintyAttribute(Attribute attribute) {
		String insertQuery = "INSERT INTO " + uncertaintyAttributeTableName + " VALUES (?, ?, ?, ?)";
		addAttribute(attribute, insertQuery);
	}

	public ArrayList<Attribute> getUncertaintyAttributes(String uncertaintyId) {
		String selectQuery = "SELECT * FROM " + uncertaintyAttributeTableName + " WHERE uncertainty_id = ?";
		return getAttributes(uncertaintyId, selectQuery, "uncertainty");
	}

	public void deleteUncertaintyAttribute(String id) {
		String deleteQuery = "DELETE FROM " + uncertaintyAttributeTableName + " WHERE uncertainty_attribute_id = ?";
		deleteNode(id, deleteQuery);
	}

	public static String getUncertaintyAttributeTableName() {
		return uncertaintyAttributeTableName;
	}


}
