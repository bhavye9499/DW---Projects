package DIEMToolApplication;

public class Alternative extends DecisionComponent {

	private static final String alternativeCode = "ALT";
	private static int alternativeCtr = Main.alternativeDAO.getNumberOfRows("SELECT * FROM " + AlternativeDAO.getTableName());

	public Alternative() {}

	public Alternative(String name, String decId) {
		super(alternativeCode + (++alternativeCtr), decId, name);
	}

	public Alternative(String id, String decId, String name) {
		super(id, decId, name);
	}

	public static String getAlternativeCode() {
		return alternativeCode;
	}

}
