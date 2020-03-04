package DIEMToolApplication;

public class Decision {
	private static int decisionCtr = 0;
	private String decisionId;
	private String name;

	public Decision(String name) {
		decisionCtr++;
		decisionId = "D" + decisionCtr;
		this.name = name;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public int getIntDecisionId() {
		return Integer.parseInt(decisionId.substring(1));	// character at index 0 is 'D'
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
