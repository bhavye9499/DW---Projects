package HomeScreen;

import AddDecisionScreen.AddDecisionScreen;
import AddNodesScreen.AddNodesScreen;
import DIEMToolApplication.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

	@FXML private MenuButton addMenuButton, viewMenuButton, deleteMenuButton;
	@FXML private ListView<String> decisionListView, componentListView, attributeListView;
	@FXML private Label componentLabel, attributeLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		decisionListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		componentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		attributeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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
			decisionListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getDecisionName());
		}
	}

	private void setupAddMenuButton() {
//		Creating and Adding a MenuItem - Decision
		MenuItem addDecision = new MenuItem("Decision");
		addDecision.setOnAction(actionEvent -> {
			String decisionName = AddDecisionScreen.getAddDecisionScreenController().display();
			if (decisionName != null) {
				Decision decision = new Decision(decisionName);
				Main.decisionDAO.addDecision(decision);
				decisionListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getDecisionName());
			}
		});
		addMenuButton.getItems().add(addDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem addAlternative = new MenuItem("Alternative");
		addAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			String[] alternatives = AddNodesScreen.getAddNodesScreenController().display("Add Alternative", "Decision", decision.getDecisionName(), "Alternative");
			for (String alternative : alternatives) {
				Main.alternativeDAO.addAlternative(new Alternative(alternative, decision.getDecisionId()));
			}
			displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
		});
		addMenuButton.getItems().add(addAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem addUncertainty = new MenuItem("Uncertainty");
		addUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			String[] uncertainties = AddNodesScreen.getAddNodesScreenController().display("Add Uncertainty", "Decision", decision.getDecisionName(), "Uncertainty");
			for (String uncertainty : uncertainties) {
				Main.uncertaintyDAO.addUncertainty(new Uncertainty(uncertainty, decision.getDecisionId()));
			}
			displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
		});
		addMenuButton.getItems().add(addUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem addAction = new MenuItem("Action");
		addAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			String[] actions = AddNodesScreen.getAddNodesScreenController().display("Add Action", "Decision", decision.getDecisionName(), "Action");
			for (String action : actions) {
				Main.actionDAO.addAction(new Action(action, decision.getDecisionId()));
			}
			displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
		});
		addMenuButton.getItems().add(addAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem addObjective = new MenuItem("Objective");
		addObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			String[] objectives = AddNodesScreen.getAddNodesScreenController().display("Add Objective", "Decision", decision.getDecisionName(), "Objective");
			for (String objective : objectives) {
				Main.objectiveDAO.addObjective(new Objective(objective, decision.getDecisionId()));
			}
			displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
		});
		addMenuButton.getItems().add(addObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem addAttribute = new MenuItem("Attribute");
		addMenuButton.getItems().add(addAttribute);
	}

	private void setupViewMenuButton() {
//		Creating and Adding a MenuItem - Alternative
		MenuItem viewAlternative = new MenuItem("Alternative");
		viewAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
		});
		viewMenuButton.getItems().add(viewAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem viewUncertainty = new MenuItem("Uncertainty");
		viewUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
		});
		viewMenuButton.getItems().add(viewUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem viewAction = new MenuItem("Action");
		viewAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
		});
		viewMenuButton.getItems().add(viewAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem viewObjective = new MenuItem("Objective");
		viewObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
		});
		viewMenuButton.getItems().add(viewObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem viewAttribute = new MenuItem("Attribute");
		viewMenuButton.getItems().add(viewAttribute);
	}

	private void setupDeleteMenuButton() {

	}

	private void displayComponents(String decisionId, String componentName, int componentCodeLenth) {
		componentListView.getItems().clear();
		ArrayList<DecisionComponent> components = null;
		if (componentName.equals("alternative")) {
			componentLabel.setText("Alternatives");
			components = Main.alternativeDAO.getAlternatives(decisionId);
		} else if (componentName.equals("action")) {
			componentLabel.setText("Actions");
			components = Main.actionDAO.getActions(decisionId);
		} else if (componentName.equals("uncertainty")) {
			componentLabel.setText("Uncertainties");
			components = Main.uncertaintyDAO.getUncertaintys(decisionId);
		} else if (componentName.equals("objective")) {
			componentLabel.setText("Objectives");
			components = Main.objectiveDAO.getObjectives(decisionId);
		}
		for (int i = 0; i < components.size(); i++) {
			componentListView.getItems().add(components.get(i).getIntComponentId(componentCodeLenth) + " - " + components.get(i).getComponentName());
		}
	}

	private Decision getSelectedDecision() {
		ObservableList<String> decisions = decisionListView.getSelectionModel().getSelectedItems();
		return new Decision(decisions.get(0).split(" - "));
	}

}
