package DecisonComponents;

import DAO.ActionDAO;
import DIEMToolApplication.Main;

public class Action extends DecisionComponent {

	private static final String actionCode = "ACT";
	private static final String componentLabelText = "Actions";
	private static int numRecords = Main.actionDAO.getListOfRecordIds("SELECT * FROM " + ActionDAO.getTableName(), Action.getActionCode().length()).size();
	private static int actionCtr = (numRecords == 0) ? 0 : Main.actionDAO.getListOfRecordIds("SELECT * FROM " + ActionDAO.getTableName(), Action.getActionCode().length()).get(numRecords - 1);

	public Action() {}

	public Action(String name, String decId) {
		super(actionCode + (++actionCtr), decId, name);
	}

	public Action(String id, String decId, String name) {
		super(id, decId, name);
	}

	public static String getActionCode() {
		return actionCode;
	}

	public static String getComponentLabelText() {
		return componentLabelText;
	}

}
