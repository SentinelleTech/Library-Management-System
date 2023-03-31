package com.management.registration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddItem extends JInternalFrame implements ActionListener {
	
	JLabel lblItemID, lblItemName, lblDescription, lblQuantity;
	JTextField txtItemID, txtItemName, txtDescription, txtQuantity;
	JButton btnSubmit;
	GridBagLayout gbl;
	GridBagConstraints c;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;
	JPanel pan;

	public AddItem() {		
		super("Add new Item", true, true, true, true);
		setBounds(10, 10, 400, 400);
		
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		c = new GridBagConstraints();
		
		lblItemID = new JLabel("Item ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblItemID, c);
		
		txtItemID = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 5.5;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtItemID, c);
		
		lblItemName = new JLabel("Item Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblItemName, c);
		
		txtItemName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtItemName, c);
		
		lblDescription = new JLabel("Item Description");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblDescription, c);
		
		txtDescription = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtDescription, c);
		
		lblQuantity = new JLabel("Quantity");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblQuantity, c);
		
		txtQuantity = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtQuantity, c);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		add(btnSubmit, c);
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String id = txtItemID.getText();
		String name = txtItemName.getText();
		String desc = txtDescription.getText();
		String qty = txtQuantity.getText();

		if(ae.getSource() == btnSubmit) {
			
			sb = new StringBuffer();
			
			try {
				
				String urlLink = "http://localhost/LMS/addBook.php";
				
				URL url = new URL(urlLink);
								
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				//Request setup
				connection.setRequestMethod("POST");
				
				writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
					
				String data = URLEncoder.encode("itemId") + "=" + URLEncoder.encode(id) + "&&" + 
				URLEncoder.encode("itemName") + "=" + URLEncoder.encode(name) + "&&" +  
						URLEncoder.encode("description") + "=" + URLEncoder.encode(desc) + "&&" + 
				URLEncoder.encode("quantity") + "=" + URLEncoder.encode(qty);
				
				writer.write(data);				
					
				writer.close();
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line = "";
				
				while((line = br.readLine()) != null) {
					
					result = line;
					
				}
					
				JOptionPane.showMessageDialog(null, result);
				
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				connection.disconnect();
			}
		}
		
	}

}
