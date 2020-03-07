package DIEMToolApplication;

public class Decision {
	private static int decisionCtr = 0;
	private String decisionId;
	private String decisionName;

	public Decision(String decisionName) {
		decisionCtr++;
		decisionId = "D" + decisionCtr;
		this.decisionName = decisionName;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public int getIntDecisionId() {
		return Integer.parseInt(decisionId.substring(1));	// character at index 0 is 'D'
	}

	public String getName() {
		return decisionName;
	}

	public void setName(String decisionName) {
		this.decisionName = decisionName;
	}
}
