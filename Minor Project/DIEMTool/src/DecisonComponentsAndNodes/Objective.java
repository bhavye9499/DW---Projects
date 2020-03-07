package DecisonComponentsAndNodes;

import DIEMToolApplication.Main;
import DAO.ObjectiveDAO;

public class Objective extends DecisionComponent {

	private static final String objectiveCode = "OBJ";
	private static int objectiveCtr = Main.objectiveDAO.getListOfRecordIds("SELECT * FROM " + ObjectiveDAO.getTableName(), Objective.getObjectiveCode().length()).size();

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

}
