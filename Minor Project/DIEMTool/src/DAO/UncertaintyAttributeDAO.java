package DAO;

import Attributes.Attribute;
import java.util.ArrayList;

public class UncertaintyAttributeDAO extends DAO {

	private static final String tableName = "UncertaintyAttribute";

	public void addUncertaintyAttribute(Attribute attribute) {
		String insertQuery = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?)";
		addAttribute(attribute, insertQuery);
	}

	public ArrayList<Attribute> getUncertaintyAttributes(String uncertaintyId) {
		String selectQuery = "SELECT * FROM " + tableName + " WHERE uncertainty_id = " + quotes(uncertaintyId);
		return getAttributes(selectQuery, "uncertainty");
	}

	public void deleteUncertaintyAttribute(String id) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE uncertainty_attribute_id = " + quotes(id);
		updateOrDeleteElement(deleteQuery);
	}

	public void updateUncertaintyAttribute(String id, String name, String dataType) {
		String modifyQuery = "UPDATE " + tableName + " SET uncertainty_attribute_name = " + quotes(name) + ", uncertainty_attribute_datatype = " + quotes(dataType) + " WHERE uncertainty_attribute_id = " + quotes(id);
		updateOrDeleteElement(modifyQuery);
	}

	public static String getTableName() {
		return tableName;
	}

}
