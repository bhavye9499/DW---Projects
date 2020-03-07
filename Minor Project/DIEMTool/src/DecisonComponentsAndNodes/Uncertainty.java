package DecisonComponentsAndNodes;

import DIEMToolApplication.Main;
import DAO.UncertaintyDAO;

public class Uncertainty extends DecisionComponent {

	private static final String uncertaintyCode = "UNC";
	private static int uncertaintyCtr = Main.uncertaintyDAO.getListOfRecordIds("SELECT * FROM " + UncertaintyDAO.getTableName(), Uncertainty.getUncertaintyCode().length()).size();

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
