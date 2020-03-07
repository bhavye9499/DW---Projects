package AddNodesScreen;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddNodesScreen {

	private static String addNodesScreenName;
	private static Scene addNodesScreenScene;
	private static Stage addNodesScreenStage;
	private static AddNodesScreenController addNodesScreenController;

	public static void init(String name) {
		setAddNodesScreenName(name);
		setAddNodesScreenStage(new Stage());
		setAddNodesScreenController(new AddNodesScreenController());
	}

	public static String getAddNodesScreenName() {
		return addNodesScreenName;
	}

	public static void setAddNodesScreenName(String name) {
		addNodesScreenName = name;
	}

	public static Scene getAddNodesScreenScene() {
		return addNodesScreenScene;
	}

	public static void setAddNodesScreenScene(Scene scene) {
		addNodesScreenScene = scene;
	}

	public static Stage getAddNodesScreenStage() {
		return addNodesScreenStage;
	}

	private static void setAddNodesScreenStage(Stage stage) {
		addNodesScreenStage = stage;
		addNodesScreenStage.initModality(Modality.APPLICATION_MODAL);
	}

	public static AddNodesScreenController getAddNodesScreenController() {
		return addNodesScreenController;
	}

	public static void setAddNodesScreenController(AddNodesScreenController controller) {
		addNodesScreenController = controller;
	}
}
