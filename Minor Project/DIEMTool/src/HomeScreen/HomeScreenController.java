package HomeScreen;

import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
import DIEMToolApplication.Alternative;
import DIEMToolApplication.Decision;
import DIEMToolApplication.DecisionDAO;
import DIEMToolApplication.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

	@FXML private MenuButton addMenuButton, viewButton, deleteButton;
	@FXML private ListView<String> decisionsListView, objectsListView, attributesListView;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		decisionsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		objectsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		attributesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//		Creating and Adding MenuItems for all the  MenuButton
		setupAddMenuButton();
		setupViewMenuButton();
		setupDeleteMenuButton();
//		Loading the decisions from database
		loadDecisions();
	}

	private void loadDecisions() {
		int numDecisions = Main.decisionDAO.getNumberOfRows("SELECT * FROM " + DecisionDAO.getTableName());
		for (int i = 1; i <= numDecisions; i++) {
			Decision decision = Main.decisionDAO.getDecision(i);
			decisionsListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getDecisionName());
		}
	}

	private void setupAddMenuButton() {
//		Creating and Adding a MenuItem - Decision
		MenuItem addDecision = new MenuItem("Decision");
		addDecision.setOnAction(actionEvent -> {
			String decisionName = AddDecisionScreen.getAddDecisionScreenController().display();
			Decision decision = new Decision(decisionName);
			Main.decisionDAO.addDecision(decision);
			decisionsListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getDecisionName());
		});
		addMenuButton.getItems().add(addDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem addAlternative = new MenuItem("Alternative");
		addAlternative.setOnAction(actionEvent -> {
			ObservableList<String> decisions = decisionsListView.getSelectionModel().getSelectedItems();
			Decision decision = new Decision(decisions.get(0).split(" - "));
			String[] alternatives = AddNodesScreen.getAddNodesScreenController().display("Add Alternative", "Decision", decision.getDecisionName(), "Alternative");
			for (int i = 0; i < alternatives.length; i++) {
				Main.alternativeDAO.addAlternative(new Alternative(alternatives[i], decision.getDecisionId()));
			}
			displayAlternatives(decision.getDecisionId());
		});
		addMenuButton.getItems().add(addAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem addUncertainty = new MenuItem("Uncertainty");
		addUncertainty.setOnAction(actionEvent -> {
			ObservableList<String> decisions = decisionsListView.getSelectionModel().getSelectedItems();
			Decision decision = new Decision(decisions.get(0).split(" - "));
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

	private void setupViewMenuButton() {

	}

	private void setupDeleteMenuButton() {

	}

	private void displayAlternatives(String decisionId) {
		ArrayList<Alternative> alternatives1 = Main.alternativeDAO.getAlternatives(decisionId);
		objectsListView.getItems().clear();
		for (int i = 0; i < alternatives1.size(); i++) {
			Alternative alternative = alternatives1.get(i);
			objectsListView.getItems().add(alternative.getIntAlternativeId() + " - " + alternative.getAlternativeName());
		}
	}
}
