package DIEMToolApplication;

import DAO.*;
import HomeScreen.HomeScreen;
import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
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
	public static AttributeDAO attributeDAO;

    @Override
    public void start(Stage primaryStage) throws Exception {

//		Creating DAOs
		decisionDAO = new DecisionDAO();
		alternativeDAO = new AlternativeDAO();
		uncertaintyDAO = new UncertaintyDAO();
		objectiveDAO = new ObjectiveDAO();
		actionDAO = new ActionDAO();
		attributeDAO = new AttributeDAO();

//		Setting up MySQLLoginScreen and its scene
//    	MySQLLoginScreen.init("../MySQLLoginScreen/MySQLLoginScreen.fxml", "MySQL Login");
//    	MySQLLoginScreen.setMysqlLoginScreenScene(new Scene(FXMLLoader.load(getClass().getResource(MySQLLoginScreen.getMysqlLoginScreenName()))));
//		String[] credentials = MySQLLoginScreen.getMysqlLoginScreenController().display();

//		Initializing Java DataBase Connectivity
    	JDBC.init("bhavye", "pass@Bhavye99");	// user, pass

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
