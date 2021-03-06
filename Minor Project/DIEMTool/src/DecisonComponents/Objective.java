package DecisonComponents;

import DIEMToolApplication.Main;
import DAO.ObjectiveDAO;

public class Objective extends DecisionComponent {

	private static final String objectiveCode = "OBJ";
	private static final String componentLabelText = "Objectives";
	private static int numRecords = Main.objectiveDAO.getListOfRecordIds("SELECT * FROM " + ObjectiveDAO.getTableName(), Objective.getObjectiveCode().length()).size();
	private static int objectiveCtr = (numRecords == 0) ? 0 : Main.objectiveDAO.getListOfRecordIds("SELECT * FROM " + ObjectiveDAO.getTableName(), Objective.getObjectiveCode().length()).get(numRecords - 1);

	public Objective() {}

	public Objective(String name, String decId) {
		super(objectiveCode + (++objectiveCtr), decId, name);
	}

	public Objective(String id, String decId, String name) {
		super(id, decId, name);
	}

	public static String getObjectiveCode() {
		return objectiveCode;
	}

	public static String getComponentLabelText() {
		return componentLabelText;
	}

}
