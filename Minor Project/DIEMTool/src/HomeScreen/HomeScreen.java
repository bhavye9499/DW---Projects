package HomeScreen;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreen {

	private static String homeScreenName;
	private static Scene homeScreenScene;
	private static Stage homeScreenStage;
	private static HomeScreenController homeScreenController;

	public static void init(String name, Stage stage, String title) {
		setHomeScreenName(name);
		setHomeScreenStage((stage != null) ? stage : new Stage(), title);
	}

	public static String getHomeScreenName() {
		return homeScreenName;
	}

	public static void setHomeScreenName(String name) {
		homeScreenName = name;
	}

	public static Stage getHomeScreenStage() {
		return homeScreenStage;
	}

	public static void setHomeScreenStage(Stage stage, String title) {
		homeScreenStage = stage;
		homeScreenStage.setTitle(title);
	}

	public static Scene getHomeScreenScene() {
		return homeScreenScene;
	}

	public static void setHomeScreenScene(Scene scene) {
		homeScreenScene = scene;
	}
}
