package DIEMToolApplication;

public class Decision {
	private static final String decisionCode = "DEC";
	private static int decisionCtr = Main.decisionDAO.getNumberOfDecisions();
	private String decisionId;
	private String decisionName;

	public Decision(String decisionName) {
		decisionCtr++;
		decisionId = decisionCode + decisionCtr;
		this.decisionName = decisionName;
	}

	public Decision() {}

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
