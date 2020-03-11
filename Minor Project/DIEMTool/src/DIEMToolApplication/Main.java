package DIEMToolApplication;

import AlertBox.AlertBox;
import DAO.*;
import HomeScreen.HomeScreen;
import DecisionScreen.DecisionScreen;
import NodesScreen.NodesScreen;
import MySQLLoginScreen.MySQLLoginScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static DecisionDAO decisionDAO;
	public static AlternativeDAO alternativeDAO;
	public static UncertaintyDAO uncertaintyDAO;
	public static ObjectiveDAO objectiveDAO;
	public static ActionDAO actionDAO;
	public static ActionAttributeDAO actionAttributeDAO;
	public static UncertaintyAttributeDAO uncertaintyAttributeDAO;

    @Override
    public void start(Stage primaryStage) throws Exception {

//		Creating DAOs
		decisionDAO = new DecisionDAO();
		alternativeDAO = new AlternativeDAO();
		uncertaintyDAO = new UncertaintyDAO();
		objectiveDAO = new ObjectiveDAO();
		actionDAO = new ActionDAO();
		actionAttributeDAO = new ActionAttributeDAO();
		uncertaintyAttributeDAO = new UncertaintyAttributeDAO();

//    	Setting up AlertBox
		AlertBox.init("../AlertBox/AlertBox.fxml");

//		Setting up MySQLLoginScreen and its scene
    	MySQLLoginScreen.init("../MySQLLoginScreen/MySQLLoginScreen.fxml", "MySQL Login");
    	MySQLLoginScreen.setMysqlLoginScreenScene(new Scene(FXMLLoader.load(getClass().getResource(MySQLLoginScreen.getMysqlLoginScreenName()))));

//    	Initializing Java Database Connectivity
    	Main.initJDBC();

//		Setting up HomeScreen and its scene
    	HomeScreen.init("../HomeScreen/HomeScreen.fxml", primaryStage, "DIEM Tool");
    	HomeScreen.setHomeScreenScene(new Scene(FXMLLoader.load(getClass().getResource(HomeScreen.getHomeScreenName()))));

//		Setting up DecisionScreen
    	DecisionScreen.init("../DecisionScreen/DecisionScreen.fxml");

//		Setting up NodesScreen
    	NodesScreen.init("../NodesScreen/NodesScreen.fxml");

//		Setting HomeScreenScene to HomeScreenStage and showing it
        HomeScreen.getHomeScreenStage().setScene(HomeScreen.getHomeScreenScene());
        HomeScreen.getHomeScreenStage().show();
    }

    private static void initJDBC() {
    	while (true) {
//			Getting credentials
			String[] credentials = MySQLLoginScreen.getMysqlLoginScreenController().display();
//			User pressed Cancel, therefore exiting the application
			if (credentials == null) System.exit(0);
//			Initializing Java DataBase Connectivity
			int status = JDBC.init(credentials[0], credentials[1]);	// user, pass
			if (status == -1) {
				AlertBox.getAlertBoxController().display("Error", "Invalid username or password! Please try again");
			} else {
				break;
			}
		}
	}

    public static void main(String[] args) {
        launch(args);
    }
}
