package DIEMToolApplication;

public class Decision {

	private static final String decisionCode = "DEC";
	private static int decisionCtr = Main.decisionDAO.getNumberOfRows("SELECT * FROM " + DecisionDAO.getTableName());
	private String decisionId;
	private String decisionName;

	public Decision() {}

	public Decision(String name) {
		decisionCtr++;
		decisionId = decisionCode + decisionCtr;
		decisionName = name;
	}

	public Decision(String[] arr) {
		decisionId = decisionCode + arr[0];
		decisionName = arr[1];
	}

	public static String getDecisionCode() {
		return decisionCode;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(String id) {
		decisionId = id;
	}

	public String getDecisionName() {
		return decisionName;
	}

	public void setDecisionName(String name) {
		decisionName = name;
	}

	public int getIntDecisionId() {
		return Integer.parseInt(decisionId.substring(decisionCode.length()));
	}
}
