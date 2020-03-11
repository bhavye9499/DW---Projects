package Attributes;

import DAO.ActionAttributeDAO;
import DIEMToolApplication.Main;

import java.util.ArrayList;

public class ActionAttribute extends Attribute{

	private static final String actionAttributeCode = "ACTATR";
	private static int numRecords = Main.actionAttributeDAO.getListOfRecordIds("SELECT * FROM " + ActionAttributeDAO.getTableName(), ActionAttribute.getActionAttributeCode().length()).size();
	private static int actionAttributeCtr = (numRecords == 0) ? 0 : Main.actionAttributeDAO.getListOfRecordIds("SELECT * FROM " + ActionAttributeDAO.getTableName(), ActionAttribute.getActionAttributeCode().length()).get(numRecords - 1) + 1;

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
