package DIEMToolApplication;

import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
import HomeScreen.HomeScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//		Setting up HomeScreen and its scene
    	HomeScreen.init("../HomeScreen/HomeScreen.fxml", primaryStage, "DIEM Tool");
    	HomeScreen.setHomeScreenScene(new Scene(FXMLLoader.load(getClass().getResource(HomeScreen.getHomeScreenName()))));

//		Setting up AddDecisionScreen
    	AddDecisionScreen.init("../AddDecisionScreen/AddDecisionScreen.fxml", "Add Decision");

//		Setting up AddNodesScreen
    	AddNodesScreen.init("../AddNodesScreen/AddNodesScreen.fxml");

//		Setting HomeScreenScene to HomeScreenStage and showing it
        HomeScreen.getHomeScreenStage().setScene(HomeScreen.getHomeScreenScene());
        HomeScreen.getHomeScreenStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
