package DecisionScreen;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DecisionScreen {

	private static String decisionScreenName;
	private static Scene decisionScreenScene;
	private static Stage decisionScreenStage;
	private static DecisionScreenController decisionScreenController;

	public static void init(String name) {
		setDecisionScreenName(name);
		setDecisionScreenStage(new Stage());
		setDecisionScreenController(new DecisionScreenController());
	}

	public static String getDecisionScreenName() {
		return decisionScreenName;
	}

	public static void setDecisionScreenName(String name) {
		decisionScreenName = name;
	}

	public static Scene getDecisionScreenScene() {
		return decisionScreenScene;
	}

	public static void setDecisionScreenScene(Scene scene) {
		decisionScreenScene = scene;
	}

	public static Stage getDecisionScreenStage() {
		return decisionScreenStage;
	}

	private static void setDecisionScreenStage(Stage stage) {
		decisionScreenStage = stage;
		decisionScreenStage.initModality(Modality.APPLICATION_MODAL);
	}

	public static DecisionScreenController getDecisionScreenController() {
		return decisionScreenController;
	}

	public static void setDecisionScreenController(DecisionScreenController controller) {
		decisionScreenController = controller;
	}
}
