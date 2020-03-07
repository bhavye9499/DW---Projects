package DIEMToolApplication;

public class Objective extends DecisionComponent {

	private static final String objectiveCode = "OBJ";
	private static int objectiveCtr = Main.objectiveDAO.getNumberOfRows("SELECT * FROM " + ObjectiveDAO.getTableName());

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
