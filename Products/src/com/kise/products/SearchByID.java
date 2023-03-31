package com.kise.products;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MenuKeyEvent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SearchByID extends JFrame implements KeyListener{
	
	JLabel lblPName, lblPDescription, lblPQuantity;
	JTextField txtPName, txtPQuantity, txtID;
	JTextArea txtPDescription;
	JPanel panMain, panID, panDetails;
	CardLayout cl;
	GridBagLayout gbl;
	GridBagConstraints c;
	Connection con;
	Pool p;
	Statement st;
	ResultSet rs;


	public SearchByID() {
		super("Search");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		p = new Pool("root", "", "Products_DB");
		
		con = p.createConnection();
		
		
		
		panID = new JPanel();
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		
		panID.setLayout(gbl);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Consolas", Font.BOLD, 30));
		txtID.addKeyListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10.0;
		c.insets = new Insets(5, 5, 5, 5);
		panID.add(txtID, c);
		
		
		panDetails = new JPanel();		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		panDetails.setLayout(gbl);
		
		lblPName = new JLabel("Name");
		lblPName.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(lblPName, c);
		
		txtPName = new JTextField();
		txtPName.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 5.4;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(txtPName, c);
		
		lblPDescription = new JLabel("Description");
		lblPDescription.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(lblPDescription, c);
		
		txtPDescription = new JTextArea();
		txtPDescription.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(txtPDescription, c);
		
		lblPQuantity = new JLabel("Quantity");
		lblPQuantity.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(lblPQuantity, c);
		
		txtPQuantity = new JTextField();
		txtPQuantity.addKeyListener(this);
		txtPQuantity.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		panDetails.add(txtPQuantity, c);
		
		panMain = new JPanel();
		cl = new CardLayout();
		panMain.setLayout(cl);
		
		panMain.add("Identifier", panID);
		panMain.add("More", panDetails);
		
		
		add(panMain);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new SearchByID();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
			cl.show(panMain, "More");
			
			JSONParser jsonP = new JSONParser();
			try {			
				
				st = con.createStatement();
				
				rs = st.executeQuery("select * from products where product_id = '"+txtID.getText()+"' ");
				
				JSONObject obj = new JSONObject();
				
				while (rs.next()) {
					
		            int total_rows = rs.getMetaData().getColumnCount();
		            
		            for (int i = 0; i < total_rows; i++) {		            	
		                
		                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getString(i + 1));
		                
		                String name = (String) obj.get("product_name");
		                String desc = (String) obj.get("product_description");
		                String qty = (String) obj.get("product_quantity");
		                
				         txtPName.setText(name);
				         txtPDescription.setText(desc);
				         txtPQuantity.setText(qty);
		                
		            }
		        }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
			cl.show(panMain, "Identifier");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
