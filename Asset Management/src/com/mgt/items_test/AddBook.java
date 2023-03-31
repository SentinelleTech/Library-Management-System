package com.mgt.items_test;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddBook extends JInternalFrame implements ActionListener {
	
	JLabel lblBookID, lblBookName, lblEdition, lblPublisher, lblStatus;
	JTextField txtBookID, txtBookName, txtEdition, txtPublisher, txtStatus;
	JButton btnSubmit;
	GridBagLayout gbl;
	GridBagConstraints c;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;

	public AddBook() {
		super("Add new book", true, true, true, true);
		setBounds(50, 50, 500, 350);
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		c = new GridBagConstraints();
		
		lblBookID = new JLabel("Book ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblBookID, c);
		
		txtBookID = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 5.5;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtBookID, c);
		
		lblBookName = new JLabel("Book Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblBookName, c);
		
		txtBookName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtBookName, c);
		
		lblEdition = new JLabel("Edition");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblEdition, c);
		
		txtEdition = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtEdition, c);
		
		lblPublisher = new JLabel("Publisher");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPublisher, c);
		
		txtPublisher = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPublisher, c);
		
		lblStatus = new JLabel("Book Status");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblStatus, c);
		
		txtStatus = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtStatus, c);		
		
	
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		add(btnSubmit, c);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String id = txtBookID.getText();
		String bn = txtBookName.getText();
		String ed = txtEdition.getText();
		String pub = txtPublisher.getText();
		String stat = txtStatus.getText();
		
		if(ae.getSource() == btnSubmit) {
					
					sb = new StringBuffer();
					
					try {
						
						String urlLink = "http://192.168.5.158/Institution%20Library/insert_book.php";
						
						URL url = new URL(urlLink);
										
						connection = (HttpURLConnection) url.openConnection();
						connection.setDoInput(true);
						connection.setDoOutput(true);
						//Request setup
						connection.setRequestMethod("POST");
						
						writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
							
						String data = URLEncoder.encode("bkid") + "=" + URLEncoder.encode(id) + "&&" + 
						URLEncoder.encode("bkname") + "=" + URLEncoder.encode(bn) + "&&" +  
								URLEncoder.encode("ed") + "=" + URLEncoder.encode(ed) + "&&" + 
						URLEncoder.encode("pub") + "=" + URLEncoder.encode(pub)+ "&&" + 
								URLEncoder.encode("s") + "=" + URLEncoder.encode(stat);
						
						writer.write(data);				
							
						writer.close();
						
						br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						
						String line = "";
						
						while((line = br.readLine()) != null) {
							
							result = line;
							
						}
							
						JOptionPane.showMessageDialog(null, result);
						
						txtBookID.setText("");
						txtBookName.setText("");
						txtEdition.setText("");
						txtPublisher.setText("");
						txtStatus.setText("");
						
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
