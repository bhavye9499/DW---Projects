package DIEMToolApplication;

public class Alternative {

	private static final String alternativeCode = "ALT";
	private static int alternativeCtr = Main.alternativeDAO.getNumberOfRows("SELECT * FROM " + AlternativeDAO.getTableName());
	private String alternativeId;
	private String alternativeDecisionId;
	private String alternativeName;

	public Alternative() {}

	public Alternative(String name, String decId) {
		alternativeCtr++;
		alternativeId = alternativeCode + alternativeCtr;
		alternativeDecisionId = decId;
		alternativeName = name;
	}

	public Alternative(String id, String decId, String name) {
		alternativeId = id;
		alternativeDecisionId = decId;
		alternativeName = name;
	}

	public static String getAlternativeCode() {
		return alternativeCode;
	}

	public static int getAlternativeCtr() {
		return alternativeCtr;
	}

	public String getAlternativeId() {
		return alternativeId;
	}

	public void setAlternativeId(String id) {
		alternativeId = id;
	}

	public String getAlternativeDecisionId() {
		return alternativeDecisionId;
	}

	public void setAlternativeDecisionId(String id) {
		alternativeDecisionId = id;
	}

	public String getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(String name) {
		alternativeName = name;
	}

	public int getIntAlternativeId() {
		return Integer.parseInt(alternativeId.substring(alternativeCode.length()));
	}

}
