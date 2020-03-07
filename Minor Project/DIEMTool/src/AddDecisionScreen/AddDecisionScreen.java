package AddDecisionScreen;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddDecisionScreen {

	private static String addDecisionScreenName;
	private static Scene addDecisionScreenScene;
	private static Stage addDecisionScreenStage;
	private static AddDecisionScreenController addDecisionScreenController;

	public static void init(String name, String title) {
		setAddDecisionScreenName(name);
		setAddDecisionScreenStage(new Stage(), title);
		setAddDecisionScreenController(new AddDecisionScreenController());
	}

	public static String getAddDecisionScreenName() {
		return addDecisionScreenName;
	}

	public static void setAddDecisionScreenName(String name) {
		addDecisionScreenName = name;
	}

	public static Scene getAddDecisionScreenScene() {
		return addDecisionScreenScene;
	}

	public static void setAddDecisionScreenScene(Scene scene) {
		addDecisionScreenScene = scene;
	}

	public static Stage getAddDecisionScreenStage() {
		return addDecisionScreenStage;
	}

	private static void setAddDecisionScreenStage(Stage stage, String title) {
		addDecisionScreenStage = stage;
		addDecisionScreenStage.initModality(Modality.APPLICATION_MODAL);
		addDecisionScreenStage.setTitle(title);
	}

	public static AddDecisionScreenController getAddDecisionScreenController() {
		return addDecisionScreenController;
	}

	public static void setAddDecisionScreenController(AddDecisionScreenController controller) {
		addDecisionScreenController = controller;
	}
}
