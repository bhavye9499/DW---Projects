package AlertBox;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	private static String alertBoxName;
	private static Scene alertBoxScene;
	private static Stage alertBoxStage;
	private static AlertBoxController alertBoxController;

	public static void init(String name) {
		setAlertBoxName(name);
		setAlertBoxStage(new Stage());
		setAlertBoxController(new AlertBoxController());
	}

	public static String getAlertBoxName() {
		return alertBoxName;
	}

	public static void setAlertBoxName(String name) {
		alertBoxName = name;
	}

	public static Scene getAlertBoxScene() {
		return alertBoxScene;
	}

	public static void setAlertBoxScene(Scene scene) {
		alertBoxScene = scene;
	}

	public static Stage getAlertBoxStage() {
		return alertBoxStage;
	}

	private static void setAlertBoxStage(Stage stage) {
		alertBoxStage = stage;
		alertBoxStage.initModality(Modality.APPLICATION_MODAL);
	}

	public static AlertBoxController getAlertBoxController() {
		return alertBoxController;
	}

	public static void setAlertBoxController(AlertBoxController controller) {
		alertBoxController = controller;
	}

}
