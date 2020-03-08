package Attributes;

public class Attribute {

	private String attributeId;
	private String attributeComponentId;
	private String attributeName;
	private String attributeDataType;

	public Attribute() {}

	public Attribute(String id, String componentId, String name, String dataType) {
		attributeId = id;
		attributeComponentId = componentId;
		attributeName = name;
		attributeDataType = dataType;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String id) {
		attributeId = id;
	}

	public String getAttributeComponentId() {
		return attributeComponentId;
	}

	public void setAttributeComponentId(String id) {
		attributeComponentId = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String name) {
		attributeName = name;
	}

	public String getAttributeDataType() {
		return attributeDataType;
	}

	public void setAttributeDataType(String dataType) {
		attributeDataType = dataType;
	}

	public int getIntAttributeId(int codeLength) {
		return Integer.parseInt(attributeId.substring(codeLength));
	}

}
