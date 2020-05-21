package com.FinalInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RolapUtils {

	public static ArrayList<Fact> facts = new ArrayList<>();
	public static ArrayList<Dimension> dimensions = new ArrayList<>();

	private static BufferedWriter openFile(String fileName) {
        File out = new File("rolap", fileName);
        try {
        	return new BufferedWriter(new FileWriter(out, false));
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

	public static int checkDimensionExistence(Dimension D) {
		for (int i = 0; i < dimensions.size(); i++) {
			if (dimensions.get(i).getName().equals(D.getName())) {
				return i;
			}
		}
		return -1;
	}

	private static void writeSubDimension(BufferedWriter bw, Dimension SD) throws IOException {
		bw.append("\t\t".concat(SD.getName()).concat(" (\n"));
		int sz = SD.getAttributes().size();
		int idx = 0;
		String lastAttribute = null;
		for (String attribute : SD.getAttributes().keySet()) {
			if (idx == sz - 1) {
				lastAttribute = attribute;
				break;
			}
			bw.append("\t\t\t".concat(attribute).concat(",\n"));
			idx++;
		}
		assert lastAttribute != null;
		bw.append("\t\t\t".concat(lastAttribute).concat("\n"));
		bw.append("\t\t)\n");
	}

	private static void writeDimension(BufferedWriter bw, Dimension D) throws IOException {
		bw.append("\t".concat(D.getName()).concat(" (\n"));
		int sz = D.getAttributes().size();
		int idx = 0;
		String lastAttribute = null;
		for (String attribute : D.getAttributes().keySet()) {
			if (idx == sz - 1) {
				lastAttribute = attribute;
				break;
			}
			bw.append("\t\t".concat(attribute).concat(",\n"));
			idx++;
		}
		assert lastAttribute != null;
		bw.append("\t\t".concat(lastAttribute).concat("\n"));
		bw.append("\t)\n");

		if (D.getSubDimensions().size() > 0) {
			bw.append("\tSubDimension(s):\n");
			for (Dimension sd : D.getSubDimensions()) {
				writeSubDimension(bw, sd);
			}
		}
		bw.append("\n");
	}

	private static void writeFact(BufferedWriter bw, Fact F) throws IOException {
		bw.append("\t".concat(F.getName()).concat(" (\n"));
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

	public static void writeRolapSchemaToFile(String projectID) {
		BufferedWriter bw = openFile(projectID + "_rolap.txt");
		assert (bw != null);
		try {
			bw.write("Project: " + projectID + "\n");
			for (Fact f : facts) {
				bw.append("\nFact:\n");
				writeFact(bw, f);
				bw.append("Dimension(s):\n");
				for (Dimension d : f.getLinkedDimensions()) {
					writeDimension(bw, d);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeFile(bw);

		// System.out.println("Facts: ");
		// for (Fact f : facts) {
		// 	System.out.println(f.getName());
		// }
		// System.out.println();
		// System.out.println("Dimensions: ");
		// for (Dimension d : dimensions) {
		// 	System.out.println(d.getName());
		// 	System.out.println("\tSub-Dim: ");
		// 	for (Dimension sd : d.getSubDimensions()) {
		// 		System.out.println("\t" + sd.getName());
		// 	}
		// }
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

	public int checkSubDimensionExistence(Dimension SD) {
		for (int i = 0; i < this.subDimensions.size(); i++) {
			if (this.subDimensions.get(i).getName().equals(SD.getName())) {
				return i;
			}
		}
		return -1;
	}

	public void linkSubDimension(Dimension SD) {
		this.subDimensions.add(SD);
	}

	public void addAttribute(String name, String dataType) {
		this.attributes.put(name, dataType);
	}

	public void addAttribute(HashMap<String, String> attribute) {
		for (String attributeName : attribute.keySet()) {	/* only 1 time iteration in this tool */
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