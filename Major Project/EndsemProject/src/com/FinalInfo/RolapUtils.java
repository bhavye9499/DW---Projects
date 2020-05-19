package com.FinalInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RolapUtils {

	public static String projectID;
	public static ArrayList<Fact> facts = new ArrayList<>();
	public static ArrayList<Dimension> dimensions = new ArrayList<>();

	private static BufferedWriter openFile(String filePath) {
        File out = new File(filePath);
        try {
        	return new BufferedWriter(new FileWriter(out, true));
		} catch (IOException e) {
        	e.printStackTrace();
		}
        return null;
	}

	private static void closeFile(BufferedWriter bw) {
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Fact createFact(String name, HashMap<String,String> attributes) {
		return new Fact(name, attributes);
	}

	public static Dimension createDimension(String name) {
		return new Dimension(name);
	}

	public static Dimension createSubDimension(String name) {
		return new Dimension(name);
	}

	public static boolean checkDimensionExistence(Dimension D) {
		for (Dimension d : dimensions) {
			if (d.getName().equals(D.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkSubDimensionExistence(Dimension SD) {
		return checkDimensionExistence(SD);
	}

	private static void writeFact(BufferedWriter bw, Fact F) throws IOException {
		bw.append("\t");
		bw.append(F.getName());
		bw.append(" (\n");
		int sz = F.getAttributes().size();
		int idx = 0;
		String lastAttribute = null;
		for (String attribute : F.getAttributes().keySet()) {
			if (idx == sz - 1) {
				lastAttribute = attribute;
				break;
			}
			bw.append("\t\t".concat(attribute).concat(",\n"));
			idx++;
		}
		assert lastAttribute != null;
		bw.append("\t\t".concat(lastAttribute).concat("\n"));
		bw.append("\t)\n\n");
	}

	private static void writeDimension(BufferedWriter bw, Dimension D) throws IOException {

	}

	private static void writeSubDimension(BufferedWriter bw, Dimension D) throws IOException {

	}

	public static void writeRolapSchemaToFile() {
		String filePath = "/home/bhavye/Desktop/DW/Projects/Major Project/EndsemProject/rolap/" + projectID + "_rolap.txt";
		BufferedWriter bw = openFile(filePath);
		assert (bw != null);
		try {
			for (Fact f : facts) {
				bw.append("Fact:\n");
				writeFact(bw, f);
			}
		} catch (IOException e) {

		}
		closeFile(bw);
	}
}

class Fact {
	private String name;
	private HashMap<String, String> attributes;
	private ArrayList<Dimension> linkedDimensions;

	public Fact(String name, HashMap<String, String> attributes) {
		this.name = name;
		this.attributes = attributes;
		this.linkedDimensions = new ArrayList<>();
	}

	public void linkDimension(Dimension D) {
		this.linkedDimensions.add(D);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Dimension> getLinkedDimensions() {
		return linkedDimensions;
	}

	public void setLinkedDimensions(ArrayList<Dimension> linkedDimensions) {
		this.linkedDimensions = linkedDimensions;
	}
}

class Dimension {
	private String name;
	private HashMap<String, String> attributes;
	private ArrayList<Dimension> subDimensions;

	public Dimension(String name) {
		this.name = name;
		this.attributes = new HashMap<>();
		this.subDimensions = new ArrayList<>();
	}

	public void linkSubDimension(Dimension SD) {
		this.subDimensions.add(SD);
	}

	public void addAttribute(String name, String dataType) {
		attributes.put(name, dataType);
	}

	public void addAttribute(HashMap<String, String> attribute) {
		for (String attributeName : attribute.keySet()) {	/* only 1 time iteration */
			this.attributes.put(attributeName, attribute.get(attributeName));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Dimension> getSubDimensions() {
		return subDimensions;
	}

	public void setSubDimensions(ArrayList<Dimension> subDimensions) {
		this.subDimensions = subDimensions;
	}
}