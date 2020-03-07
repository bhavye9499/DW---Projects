package DecisonComponentsAndNodes;

import DAO.ActionDAO;
import DIEMToolApplication.Main;

public class Action extends DecisionComponent {

	private static final String actionCode = "ACT";
	private static int actionCtr = Main.actionDAO.getListOfRecordIds("SELECT * FROM " + ActionDAO.getTableName(), Action.getActionCode().length()).size();

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

}
