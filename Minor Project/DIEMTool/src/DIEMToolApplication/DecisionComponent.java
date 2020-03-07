package DIEMToolApplication;

public class DecisionComponent {

	protected String componentId;
	protected String componentDecisionId;
	protected String componentName;

	public DecisionComponent() {}

	public DecisionComponent(String id, String decId, String name) {
		componentId = id;
		componentDecisionId = decId;
		componentName = name;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String id) {
		componentId = id;
	}

	public String getComponentDecisionId() {
		return componentDecisionId;
	}

	public void setComponentDecisionId(String id) {
		componentDecisionId = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String name) {
		componentName = name;
	}

	public int getIntComponentId(int codeLength) {
		return Integer.parseInt(componentId.substring(codeLength));
	}

}
