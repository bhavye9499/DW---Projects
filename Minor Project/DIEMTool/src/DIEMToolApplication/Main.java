package DIEMToolApplication;

import AddDecisionScreen.AddDecisionScreenController;
import AddNodesScreen.AddNodesScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	public static String homeScreenName, addDecisionScreenName, addNodesScreenName;
	public static Parent homeScreenParent, addDecisionScreenParent, addNodesScreenParent;
	public static Scene homeScreenScene, addDecisionScreenScene, addNodesScreenScene;
	public static AddDecisionScreenController addDecisionScreenController;
	public static AddNodesScreenController addNodesScreenController;
	public static Stage homeScreenStage, addDecisionScreenStage, addNodesScreenStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	homeScreenName = "../HomeScreen/HomeScreen.fxml";
    	homeScreenStage = primaryStage;
    	homeScreenStage.setTitle("DIEM Toll");
        homeScreenParent = FXMLLoader.load(getClass().getResource(homeScreenName));
        homeScreenScene = new Scene(homeScreenParent);

    	addDecisionScreenName = "../AddDecisionScreen/AddDecisionScreen.fxml";
    	addDecisionScreenStage = new Stage();
		addDecisionScreenStage.initModality(Modality.APPLICATION_MODAL);
		addDecisionScreenStage.setTitle("Add Decision");
        addDecisionScreenController = new AddDecisionScreenController();

        addNodesScreenName = "../AddNodesScreen/AddNodesScreen.fxml";
    	addNodesScreenStage = new Stage();
		addNodesScreenStage.initModality(Modality.APPLICATION_MODAL);
        addNodesScreenController = new AddNodesScreenController();

        homeScreenStage.setScene(homeScreenScene);
        homeScreenStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
