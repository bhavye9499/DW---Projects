package Attributes;

import DAO.ActionAttributeDAO;
import DIEMToolApplication.Main;

public class ActionAttribute extends Attribute{

	private static final String actionAttributeCode = "ACTATR";
	private static int actionAttributeCtr = Main.actionAttributeDAO.getListOfRecordIds("SELECT * FROM " + ActionAttributeDAO.getActionAttributeTableName(), ActionAttribute.getActionAttributeCode().length()).size();

	public ActionAttribute() {}

	public ActionAttribute(String name, String actionId, String dataType) {
		super(actionAttributeCode + (++actionAttributeCtr), actionId, name, dataType);
	}

	public ActionAttribute(String id, String actionId, String name, String dataType) {
		super(id, actionId, name, dataType);
	}

	public static String getActionAttributeCode() {
		return actionAttributeCode;
	}

}
