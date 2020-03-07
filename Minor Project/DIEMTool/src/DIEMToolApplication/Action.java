package DIEMToolApplication;

public class Action extends DecisionComponent {

	private static final String actionCode = "ACT";
	private static int actionCtr = Main.actionDAO.getNumberOfRows("SELECT * FROM " + ActionDAO.getTableName());

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
