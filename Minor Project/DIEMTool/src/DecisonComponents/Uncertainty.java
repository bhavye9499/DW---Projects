package DecisonComponents;

import DIEMToolApplication.Main;
import DAO.UncertaintyDAO;

public class Uncertainty extends DecisionComponent {

	private static final String uncertaintyCode = "UNC";
	private static final String componentLabelText = "Uncertainties";
	private static int numRecords = Main.uncertaintyDAO.getListOfRecordIds("SELECT * FROM " + UncertaintyDAO.getTableName(), Uncertainty.getUncertaintyCode().length()).size();
	private static int uncertaintyCtr = (numRecords == 0) ? 0 : Main.uncertaintyDAO.getListOfRecordIds("SELECT * FROM " + UncertaintyDAO.getTableName(), Uncertainty.getUncertaintyCode().length()).get(numRecords - 1);

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

	public static String getComponentLabelText() {
		return componentLabelText;
	}

}
