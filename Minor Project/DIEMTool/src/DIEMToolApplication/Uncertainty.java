package DIEMToolApplication;

public class Uncertainty extends DecisionComponent {

	private static final String uncertaintyCode = "UNC";
	private static int uncertaintyCtr = Main.uncertaintyDAO.getNumberOfRows("SELECT * FROM " + UncertaintyDAO.getTableName());

	public Uncertainty() {}

	public Uncertainty(String name, String decId) {
		super(uncertaintyCode + (++uncertaintyCtr), decId, name);
	}

	public Uncertainty(String id, String decId, String name) {
		super(id, decId, name);
	}

	public static String getUncertaintyCode() {
		return uncertaintyCode;
	}

}
