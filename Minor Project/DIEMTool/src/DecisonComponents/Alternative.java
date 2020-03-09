package DecisonComponents;

import DAO.AlternativeDAO;
import DIEMToolApplication.Main;

public class Alternative extends DecisionComponent {

	private static final String alternativeCode = "ALT";
	private static int numRecords = Main.alternativeDAO.getListOfRecordIds("SELECT * FROM " + AlternativeDAO.getTableName(), Alternative.getAlternativeCode().length()).size();
	private static int alternativeCtr = Main.alternativeDAO.getListOfRecordIds("SELECT * FROM " + AlternativeDAO.getTableName(), Alternative.getAlternativeCode().length()).get(numRecords - 1);

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
