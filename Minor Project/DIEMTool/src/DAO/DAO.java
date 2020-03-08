package DAO;

import DIEMToolApplication.JDBC;
import Attributes.Attribute;
import DecisonComponents.DecisionComponent;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(JDBC.getJDBC_Driver());
    		con = DriverManager.getConnection(JDBC.getDB_URL() + JDBC.getDB_Name(), JDBC.getUser(), JDBC.getPass());
		} catch (Exception e) { e.printStackTrace(); }
		return con;
	}

	public ArrayList<Integer> getListOfRecordIds(String query, int codeLength) {
		Connection con = getConnection();
		Statement st = null;
		ArrayList<Integer> ids = new ArrayList<>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) ids.add(Integer.parseInt(rs.getString(1).substring(codeLength)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return ids;
	}

	public void addComponent(DecisionComponent component, String insertQuery) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, component.getComponentId());
			pst.setString(2, component.getComponentDecisionId());
			pst.setString(3, component.getComponentName());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	public void addAttribute(Attribute attribute, String insertQuery) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, attribute.getAttributeId());
			pst.setString(2, attribute.getAttributeComponentId());
			pst.setString(3, attribute.getAttributeName());
			pst.setString(4, attribute.getAttributeDataType());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	public ArrayList<DecisionComponent> getComponents(String decisionId, String selectQuery, String componentName) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ArrayList<DecisionComponent> components = new ArrayList<>();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, decisionId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				components.add(new DecisionComponent(rs.getString(componentName + "_id"), rs.getString("decision_id"), rs.getString(componentName + "_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return components;
	}

	public ArrayList<Attribute> getAttributes(String componentId, String selectQuery, String componentName) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ArrayList<Attribute> attributes = new ArrayList<>();
		try {
			pst = con.prepareStatement(selectQuery);
			pst.setString(1, componentId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				attributes.add(new Attribute(rs.getString(componentName + "_attribute_id"), rs.getString(componentName + "_id"), rs.getString(componentName + "_attribute_name"), rs.getString(componentName + "_attribute_datatype")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return attributes;
	}

	public void deleteNode(String nodeId, String deleteQuery) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(deleteQuery);
			pst.setString(1, nodeId);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

}
