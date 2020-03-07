package DIEMToolApplication;

import MySQLLoginScreen.MySQLLoginScreen;
import HomeScreen.HomeScreen;
import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static DecisionDAO decisionDAO;

    @Override
    public void start(Stage primaryStage) throws Exception {

//		Creating DAOs
		decisionDAO = new DecisionDAO();

//		Setting up MySQLLoginScreen and its scene
    	MySQLLoginScreen.init("../MySQLLoginScreen/MySQLLoginScreen.fxml", "MySQL Login");
    	MySQLLoginScreen.setMysqlLoginScreenScene(new Scene(FXMLLoader.load(getClass().getResource(MySQLLoginScreen.getMysqlLoginScreenName()))));
		String[] credentials = MySQLLoginScreen.getMysqlLoginScreenController().display();

//		Initializing Java DataBase Connectivity
    	JDBC.init(credentials[0], credentials[1]);	// user, pass

//		Setting up MySQLLoginScreen and its scene
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
