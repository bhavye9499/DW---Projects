package MysqlLoginScreen;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MysqlLoginScreen {

	private static String mysqlLoginScreenName;
	private static Scene mysqlLoginScreenScene;
	private static Stage mysqlLoginScreenStage;
	private static MysqlLoginScreenController mysqlLoginScreenController;

	public static void init(String name, String title) {
		setMysqlLoginScreenName(name);
		setMysqlLoginScreenStage(new Stage(), title);
		setMysqlLoginScreenController(new MysqlLoginScreenController());
	}

	public static String getMysqlLoginScreenName() {
		return mysqlLoginScreenName;
	}

	public static void setMysqlLoginScreenName(String name) {
		mysqlLoginScreenName = name;
	}

	public static Scene getMysqlLoginScreenScene() {
		return mysqlLoginScreenScene;
	}

	public static void setMysqlLoginScreenScene(Scene scene) {
		mysqlLoginScreenScene = scene;
	}

	public static Stage getMysqlLoginScreenStage() {
		return mysqlLoginScreenStage;
	}

	public static void setMysqlLoginScreenStage(Stage stage, String title) {
		mysqlLoginScreenStage = stage;
		mysqlLoginScreenStage.setTitle(title);
	}

	public static MysqlLoginScreenController getMysqlLoginScreenController() {
		return mysqlLoginScreenController;
	}

	public static void setMysqlLoginScreenController(MysqlLoginScreenController controller) {
		mysqlLoginScreenController = controller;
	}
}
