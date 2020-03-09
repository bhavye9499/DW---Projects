package Attributes;

import DAO.UncertaintyAttributeDAO;
import DIEMToolApplication.Main;

public class UncertaintyAttribute extends Attribute {

	private static final String uncertaintyAttributeCode = "UNCATR";
	private static int numRecords = Main.uncertaintyAttributeDAO.getListOfRecordIds("SELECT * FROM " + UncertaintyAttributeDAO.getTableName(), UncertaintyAttribute.getUncertaintyAttributeCode().length()).size();
	private static int uncertaintyAttributeCtr = Main.uncertaintyAttributeDAO.getListOfRecordIds("SELECT * FROM " + UncertaintyAttributeDAO.getTableName(), UncertaintyAttribute.getUncertaintyAttributeCode().length()).get(numRecords - 1) + 1;

	public UncertaintyAttribute() {}

	public UncertaintyAttribute(String name, String uncertaintyId, String dataType) {
		super(uncertaintyAttributeCode + (++uncertaintyAttributeCtr), uncertaintyId, name, dataType);
	}

	public UncertaintyAttribute(String id, String uncertaintyId, String name, String dataType) {
		super(id, uncertaintyId, name, dataType);
	}

	public static String getUncertaintyAttributeCode() {
		return uncertaintyAttributeCode;
	}

}
