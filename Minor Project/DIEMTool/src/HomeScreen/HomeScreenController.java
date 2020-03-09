package HomeScreen;

import DecisionScreen.DecisionScreen;
import NodesScreen.NodesScreen;
import Attributes.ActionAttribute;
import Attributes.Attribute;
import Attributes.UncertaintyAttribute;
import DAO.DecisionDAO;
import DIEMToolApplication.*;
import DecisonComponents.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

	@FXML private MenuButton addMenuButton, viewMenuButton, deleteMenuButton, updateMenuButton;
	@FXML private ListView<String> decisionListView, componentListView, attributeListView;
	@FXML private Label componentLabel, attributeLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		decisionListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		componentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		attributeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//		Creating and Adding MenuItems for all the MenuButtons
		setupAddMenuButton();
		setupViewMenuButton();
		setupDeleteMenuButton();
		setupUpdateMenuButton();

//		Loading the decisions from database
		displayDecisions();
	}

	private void setupAddMenuButton() {
//		Creating and Adding a MenuItem - Decision
		MenuItem addDecision = new MenuItem("Decision");
		addDecision.setOnAction(actionEvent -> {
			String decisionName = DecisionScreen.getDecisionScreenController().display(null);
			if (decisionName != null) {
				Decision decision = new Decision(decisionName);
				Main.decisionDAO.addDecision(decision);
			}
			displayDecisions();
		});
		addMenuButton.getItems().add(addDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem addAlternative = new MenuItem("Alternative");
		addAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				String[] alternatives = NodesScreen.getNodesScreenController().display("Add Alternative", "Decision", decision.getDecisionName(), "Alternative", null);
				for (String alternative : alternatives) {
					Main.alternativeDAO.addAlternative(new Alternative(alternative, decision.getDecisionId()));
				}
				displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
			}
		});
		addMenuButton.getItems().add(addAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem addUncertainty = new MenuItem("Uncertainty");
		addUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				String[] uncertainties = NodesScreen.getNodesScreenController().display("Add Uncertainty", "Decision", decision.getDecisionName(), "Uncertainty", null);
				for (String uncertainty : uncertainties) {
					Main.uncertaintyDAO.addUncertainty(new Uncertainty(uncertainty, decision.getDecisionId()));
				}
				displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
			}
		});
		addMenuButton.getItems().add(addUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem addAction = new MenuItem("Action");
		addAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				String[] actions = NodesScreen.getNodesScreenController().display("Add Action", "Decision", decision.getDecisionName(), "Action", null);
				for (String action : actions) {
					Main.actionDAO.addAction(new Action(action, decision.getDecisionId()));
				}
				displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
			}
		});
		addMenuButton.getItems().add(addAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem addObjective = new MenuItem("Objective");
		addObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				String[] objectives = NodesScreen.getNodesScreenController().display("Add Objective", "Decision", decision.getDecisionName(), "Objective", null);
				for (String objective : objectives) {
					Main.objectiveDAO.addObjective(new Objective(objective, decision.getDecisionId()));
				}
				displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
			}
		});
		addMenuButton.getItems().add(addObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem addAttribute = new MenuItem("Attribute");
		addAttribute.setOnAction(actionEvent -> {
			ArrayList<String[]> components = getSelectedComponents();
			if (components.size() != 0) {
				String componentId = null;
				int attributeCodeLength = -1;
				if (componentLabel.getText().equals("Actions")) {
					componentId = Action.getActionCode() + components.get(0)[0];
					String[] attributes = NodesScreen.getNodesScreenController().display("Add Attribute", "Action", components.get(0)[1], "Attribute", null);
					for (String attribute : attributes) {
						String[] splittedAttribute = attribute.split(",");
						Main.actionAttributeDAO.addActionAttribute(new ActionAttribute(splittedAttribute[0], componentId, splittedAttribute[1]));
					}
					attributeCodeLength = ActionAttribute.getActionAttributeCode().length();
				} else if (componentLabel.getText().equals("Uncertainties")) {
					componentId = Uncertainty.getUncertaintyCode() + components.get(0)[0];
					String[] attributes = NodesScreen.getNodesScreenController().display("Add Attribute", "Uncertainty", components.get(0)[1], "Attribute", null);
					for (String attribute : attributes) {
						String[] splittedAttribute = attribute.split(",");
						Main.uncertaintyAttributeDAO.addUncertaintyAttribute(new UncertaintyAttribute(splittedAttribute[0], componentId, splittedAttribute[1]));
					}
					attributeCodeLength = UncertaintyAttribute.getUncertaintyAttributeCode().length();
				}
				displayAttributes(componentId, attributeCodeLength);
			}
		});
		addMenuButton.getItems().add(addAttribute);
	}

	private void setupViewMenuButton() {
//		Creating and Adding a MenuItem - Alternative
		MenuItem viewAlternative = new MenuItem("Alternative");
		viewAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
			}
		});
		viewMenuButton.getItems().add(viewAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem viewUncertainty = new MenuItem("Uncertainty");
		viewUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
			}
		});
		viewMenuButton.getItems().add(viewUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem viewAction = new MenuItem("Action");
		viewAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
			}
		});
		viewMenuButton.getItems().add(viewAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem viewObjective = new MenuItem("Objective");
		viewObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
			}
		});
		viewMenuButton.getItems().add(viewObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem viewAttribute = new MenuItem("Attribute");
		viewAttribute.setOnAction(actionEvent -> {
			ArrayList<String[]> components = getSelectedComponents();
			String componentId = null;
			int attributeCodeLength = -1;
			if (components.size() == 1) {
				if (componentLabel.getText().equals("Actions")) {
					componentId = Action.getActionCode() + components.get(0)[0];
					attributeCodeLength = ActionAttribute.getActionAttributeCode().length();
					displayAttributes(componentId, attributeCodeLength);
				} else if (componentLabel.getText().equals("Uncertainties")) {
					componentId = Uncertainty.getUncertaintyCode() + components.get(0)[0];
					attributeCodeLength = UncertaintyAttribute.getUncertaintyAttributeCode().length();
					displayAttributes(componentId, attributeCodeLength);
				}
			}
		});
		viewMenuButton.getItems().add(viewAttribute);
	}

	private void setupDeleteMenuButton() {
//		Creating and Adding a MenuItem - Decision
		MenuItem deleteDecision = new MenuItem("Decision");
		deleteDecision.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				Main.decisionDAO.deleteDecision(decision.getDecisionId());
			}
			displayDecisions();
			componentListView.getItems().clear();
			attributeListView.getItems().clear();
		});
		deleteMenuButton.getItems().add(deleteDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem deleteAlternative = new MenuItem("Alternative");
		deleteAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			ArrayList<String[]> alternatives = getSelectedComponents();
			for (String[] alternative : alternatives) {
				Main.alternativeDAO.deleteAlternative(Alternative.getAlternativeCode() + alternative[0]);
			}
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
			}
		});
		deleteMenuButton.getItems().add(deleteAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem deleteUncertainty = new MenuItem("Uncertainty");
		deleteUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			ArrayList<String[]> uncertainties = getSelectedComponents();
			for (String[] uncertainty : uncertainties) {
				Main.uncertaintyDAO.deleteUncertainty(Uncertainty.getUncertaintyCode() + uncertainty[0]);
			}
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
			}
		});
		deleteMenuButton.getItems().add(deleteUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem deleteAction = new MenuItem("Action");
		deleteAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			ArrayList<String[]> actions = getSelectedComponents();
			for (String[] action : actions) {
				Main.actionDAO.deleteAction(Action.getActionCode() + action[0]);
			}
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
			}
		});
		deleteMenuButton.getItems().add(deleteAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem deleteObjective = new MenuItem("Objective");
		deleteObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			ArrayList<String[]> objectives = getSelectedComponents();
			for (String[] objective : objectives) {
				Main.objectiveDAO.deleteObjective(Objective.getObjectiveCode() + objective[0]);
			}
			if (decision != null) {
				displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
			}
		});
		deleteMenuButton.getItems().add(deleteObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem deleteAttribute = new MenuItem("Attribute");
		deleteAttribute.setOnAction(actionEvent -> {
			ArrayList<String[]> component = getSelectedComponents();
			ArrayList<String[]> attributes = getSelectedAttributes();
			String componentId = null;
			int attributeCodeLength = -1;
			if (componentLabel.getText().equals("Actions") && component.size() == 1) {
				attributeCodeLength = ActionAttribute.getActionAttributeCode().length();
				componentId = Action.getActionCode() + component.get(0)[0];
				for (String[] attribute : attributes) {
					Main.actionAttributeDAO.deleteActionAttribute(ActionAttribute.getActionAttributeCode() + attribute[0]);
				}
				displayAttributes(componentId, attributeCodeLength);
			} else if (componentLabel.getText().equals("Uncertainties") && component.size() == 1) {
				attributeCodeLength = UncertaintyAttribute.getUncertaintyAttributeCode().length();
				componentId = Uncertainty.getUncertaintyCode() + component.get(0)[0];
				for (String[] attribute : attributes) {
					Main.uncertaintyAttributeDAO.deleteUncertaintyAttribute(UncertaintyAttribute.getUncertaintyAttributeCode() + attribute[0]);
				}
				displayAttributes(componentId, attributeCodeLength);
			}
		});
		deleteMenuButton.getItems().add(deleteAttribute);
	}

	private void setupUpdateMenuButton() {
//		Creating and Adding a MenuItem - Decision
		MenuItem updateDecision = new MenuItem("Decision");
		updateDecision.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				String decisionName = DecisionScreen.getDecisionScreenController().display(decision.getDecisionName());
				if (decisionName != null) {
					Main.decisionDAO.updateDecision(decision.getDecisionId(), decisionName);
					displayDecisions();
				}
			}
		});
		updateMenuButton.getItems().add(updateDecision);

//		Creating and Adding a MenuItem - Alternative
		MenuItem updateAlternative = new MenuItem("Alternative");
		updateAlternative.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				ArrayList<String[]> alternative = getSelectedComponents();
				if (alternative.size() == 1) {
					String[] alternativeNames = NodesScreen.getNodesScreenController().display("Update Alternative", "Decision", decision.getDecisionName(), "Alternative", alternative.get(0)[1]);
					if (alternativeNames.length != 0) {
						Main.alternativeDAO.updateAlternative(Alternative.getAlternativeCode() + alternative.get(0)[0], alternativeNames[0]);
						displayComponents(decision.getDecisionId(), "alternative", Alternative.getAlternativeCode().length());
					}
				}
			}
		});
		updateMenuButton.getItems().add(updateAlternative);

//		Creating and Adding a MenuItem - Uncertainty
		MenuItem updateUncertainty = new MenuItem("Uncertainty");
		updateUncertainty.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				ArrayList<String[]> uncertainty = getSelectedComponents();
				if (uncertainty.size() == 1) {
					String[] uncertaintyNames = NodesScreen.getNodesScreenController().display("Update Uncertainty", "Decision", decision.getDecisionName(), "Uncertainty", uncertainty.get(0)[1]);
					if (uncertaintyNames.length != 0) {
						Main.uncertaintyDAO.updateUncertainty(Uncertainty.getUncertaintyCode() + uncertainty.get(0)[0], uncertaintyNames[0]);
						displayComponents(decision.getDecisionId(), "uncertainty", Uncertainty.getUncertaintyCode().length());
					}
				}
			}
		});
		updateMenuButton.getItems().add(updateUncertainty);

//		Creating and Adding a MenuItem - Action
		MenuItem updateAction = new MenuItem("Action");
		updateAction.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				ArrayList<String[]> action = getSelectedComponents();
				if (action.size() == 1) {
					String[] actionNames = NodesScreen.getNodesScreenController().display("Update Action", "Decision", decision.getDecisionName(), "Action", action.get(0)[1]);
					if (actionNames.length != 0) {
						Main.actionDAO.updateAction(Action.getActionCode() + action.get(0)[0], actionNames[0]);
						displayComponents(decision.getDecisionId(), "action", Action.getActionCode().length());
					}
				}
			}
		});
		updateMenuButton.getItems().add(updateAction);

//		Creating and Adding a MenuItem - Objective
		MenuItem updateObjective = new MenuItem("Objective");
		updateObjective.setOnAction(actionEvent -> {
			Decision decision = getSelectedDecision();
			if (decision != null) {
				ArrayList<String[]> objective = getSelectedComponents();
				if (objective.size() == 1) {
					String[] objectiveNames = NodesScreen.getNodesScreenController().display("Update Objective", "Decision", decision.getDecisionName(), "Objective", objective.get(0)[1]);
					if (objectiveNames.length != 0) {
						Main.objectiveDAO.updateObjective(Objective.getObjectiveCode() + objective.get(0)[0], objectiveNames[0]);
						displayComponents(decision.getDecisionId(), "objective", Objective.getObjectiveCode().length());
					}
				}
			}
		});
		updateMenuButton.getItems().add(updateObjective);

//		Creating and Adding a MenuItem - Attribute
		MenuItem updateAttribute = new MenuItem("Attribute");
		updateAttribute.setOnAction(actionEvent -> {
			ArrayList<String[]> component = getSelectedComponents();
			if (component.size() != 0) {
				String componentId = null;
				int attributeCodeLength = -1;
				ArrayList<String[]> attribute = getSelectedAttributes();
				if (attribute.size() != 0) {
					String attributeInfo = attribute.get(0)[1] + "," + attribute.get(0)[2];
					if (componentLabel.getText().equals("Actions")) {
						componentId = Action.getActionCode() + component.get(0)[0];
						String[] attributesInfo = NodesScreen.getNodesScreenController().display("Add Attribute", "Action", component.get(0)[1], "Attribute", attributeInfo);
						if (attributesInfo.length != 0) {
							String[] splittedAttribute = attributesInfo[0].split(",");
							Main.actionAttributeDAO.updateActionAttribute(ActionAttribute.getActionAttributeCode() + attribute.get(0)[0], splittedAttribute[0], splittedAttribute[1]);
						}
						attributeCodeLength = ActionAttribute.getActionAttributeCode().length();
					} else if (componentLabel.getText().equals("Uncertainties")) {
						componentId = Uncertainty.getUncertaintyCode() + component.get(0)[0];
						String[] attributesInfo = NodesScreen.getNodesScreenController().display("Add Attribute", "Uncertainty", component.get(0)[1], "Attribute", attributeInfo);
						if (attributesInfo.length != 0) {
							String[] splittedAttribute = attributesInfo[0].split(",");
							Main.uncertaintyAttributeDAO.updateUncertaintyAttribute(UncertaintyAttribute.getUncertaintyAttributeCode() + attribute.get(0)[0], splittedAttribute[0], splittedAttribute[1]);
						}
						attributeCodeLength = UncertaintyAttribute.getUncertaintyAttributeCode().length();
					}
					displayAttributes(componentId, attributeCodeLength);
				}
			}
		});
		updateMenuButton.getItems().add(updateAttribute);
	}

	private void displayDecisions() {
		decisionListView.getItems().clear();
		ArrayList<Integer> decisionIds = Main.decisionDAO.getListOfRecordIds("SELECT * FROM " + DecisionDAO.getTableName(), Decision.getDecisionCode().length());
		for (int i : decisionIds) {
			Decision decision = Main.decisionDAO.getDecision(i);
			decisionListView.getItems().add(decision.getIntDecisionId() + " - " + decision.getDecisionName());
		}
	}

	private void displayComponents(String decisionId, String componentName, int componentCodeLength) {
		attributeLabel.setText("-");
		attributeListView.getItems().clear();
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
			components = Main.uncertaintyDAO.getUncertainties(decisionId);
		} else if (componentName.equals("objective")) {
			componentLabel.setText("Objectives");
			components = Main.objectiveDAO.getObjectives(decisionId);
		}
		for (int i = 0; i < components.size(); i++) {
			componentListView.getItems().add(components.get(i).getIntComponentId(componentCodeLength) + " - " + components.get(i).getComponentName());
		}
	}

	private void displayAttributes(String componentId, int attributeCodeLength) {
		attributeListView.getItems().clear();
		attributeLabel.setText("Attributes");
		ArrayList<Attribute> attributes = null;
		if (componentLabel.getText().equals("Actions")) {
			attributes = Main.actionAttributeDAO.getActionAttributes(componentId);
		} else if (componentLabel.getText().equals("Uncertainties")) {
			attributes = Main.uncertaintyAttributeDAO.getUncertaintyAttributes(componentId);
		}
		for (int i = 0; i < attributes.size(); i++) {
			attributeListView.getItems().add(attributes.get(i).getIntAttributeId(attributeCodeLength) + " - " + attributes.get(i).getAttributeName() + " (" + attributes.get(i).getAttributeDataType() + ")");
		}
	}

	private Decision getSelectedDecision() {
		ObservableList<String> decisions = decisionListView.getSelectionModel().getSelectedItems();
		if (decisions.size() == 0) { return null; }
		return new Decision(decisions.get(0).split(" - "));
	}

	private ArrayList<String[]> getSelectedComponents() {
		ObservableList<String> components = componentListView.getSelectionModel().getSelectedItems();
		ArrayList<String[]> splittedComponents = new ArrayList<>();
		for (String component : components) {
			splittedComponents.add(component.split(" - "));
		}
		return splittedComponents;
	}

	private ArrayList<String[]> getSelectedAttributes() {
		ObservableList<String> attributes = attributeListView.getSelectionModel().getSelectedItems();
		ArrayList<String[]> splittedAttributes = new ArrayList<>();
		for (String attribute : attributes) {
			String[] splittedAttribute = attribute.split(" - ");
			String[] temp = splittedAttribute[1].split(" ");
			splittedAttributes.add(new String[]{splittedAttribute[0], temp[0], temp[1].substring(1, temp[1].length() - 1)});
		}
		return splittedAttributes;
	}

}
