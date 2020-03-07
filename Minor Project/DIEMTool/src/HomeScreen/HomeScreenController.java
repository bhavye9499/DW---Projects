package HomeScreen;

import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
import DIEMToolApplication.Decision;
import DIEMToolApplication.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

	@FXML private MenuButton addMenuButton, viewButton, deleteButton;
	@FXML private ListView<String> decisionsListView, objectsListView, attributesListView;
	private ObservableList<MenuItem> addMenuItems;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		decisionsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		objectsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		attributesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		//		Creating and Adding a MenuItem - Decision
		MenuItem addDecision = new MenuItem("Decision");
		addDecision.setOnAction(actionEvent -> {
			String decisionName = AddDecisionScreen.getAddDecisionScreenController().display();
			Decision decision = new Decision(decisionName);
			decisionsListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getName());
		});
		addMenuButton.getItems().add(addDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem addAlternative = new MenuItem("Alternative");
		addAlternative.setOnAction(actionEvent -> {
			ObservableList<String> decisions = decisionsListView.getSelectionModel().getSelectedItems();
			String[] alternatives = AddNodesScreen.getAddNodesScreenController().display("Add Alternative", "Decision", decisions.get(0).split(" - ")[1], "Alternative");
		});
		addMenuButton.getItems().add(addAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem addUncertainty = new MenuItem("Uncertainty");
		addUncertainty.setOnAction(actionEvent -> {
			ObservableList<String> decisions = decisionsListView.getSelectionModel().getSelectedItems();
			String[] uncertainties = AddNodesScreen.getAddNodesScreenController().display("Add Uncertainty", "Decision", decisions.get(0), "Uncertainty");
		});
		addMenuButton.getItems().add(addUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem addAction = new MenuItem("Action");
		addMenuButton.getItems().add(addAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem addObjective = new MenuItem("Objective");
		addMenuButton.getItems().add(addObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem addAttribute = new MenuItem("Attribute");
		addMenuButton.getItems().add(addAttribute);

	}
}
