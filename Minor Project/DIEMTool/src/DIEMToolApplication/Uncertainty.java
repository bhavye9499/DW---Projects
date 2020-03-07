package DIEMToolApplication;

public class Uncertainty {

	private static final String uncertaintyCode = "UNC";
	private static int uncertaintyCtr = Main.uncertaintyDAO.getNumberOfRows("SELECT * FROM " + UncertaintyDAO.getTableName());
	private String uncertaintyId;
	private String uncertaintyDecisionId;
	private String uncertaintyName;

	public Uncertainty() {}

	public Uncertainty(String name, String decId) {
		uncertaintyCtr++;
		uncertaintyId = uncertaintyCode + uncertaintyCtr;
		uncertaintyDecisionId = decId;
		uncertaintyName = name;
	}

	public Uncertainty(String id, String decId, String name) {
		uncertaintyId = id;
		uncertaintyDecisionId = decId;
		uncertaintyName = name;
	}

	public static String getUncertaintyCode() {
		return uncertaintyCode;
	}

	public static int getUncertaintyCtr() {
		return uncertaintyCtr;
	}

	public String getUncertaintyId() {
		return uncertaintyId;
	}

	public void setUncertaintyId(String id) {
		uncertaintyId = id;
	}

	public String getUncertaintyDecisionId() {
		return uncertaintyDecisionId;
	}

	public void setUncertaintyDecisionId(String id) {
		uncertaintyDecisionId = id;
	}

	public String getUncertaintyName() {
		return uncertaintyName;
	}

	public void setUncertaintyName(String name) {
		uncertaintyName = name;
	}

	public int getIntUncertaintyId() {
		return Integer.parseInt(uncertaintyId.substring(uncertaintyCode.length()));
	}

}
