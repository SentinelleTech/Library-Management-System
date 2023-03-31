package com.mgt.items_test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class AddStudent extends JInternalFrame implements ActionListener {
	
	JLabel lblStudentID, lblFirstName, lblSurname, lblCourse, lblBranch, lblYear, lblSem;
	JTextField txtStudentID, txtFirstName, txtSurname, txtCourse, txtBranch, txtYear, txtSem;
	JButton btnSubmit;
	GridBagLayout gbl;
	GridBagConstraints c;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;

	public AddStudent() {		
		super("Insert student", true, true, true, true);
		setBounds(50, 50, 500, 350);
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		c = new GridBagConstraints();
		
		lblStudentID = new JLabel("Student ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblStudentID, c);
		
		txtStudentID = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 5.5;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtStudentID, c);
		
		lblFirstName = new JLabel("First Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblFirstName, c);
		
		txtFirstName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtFirstName, c);
		
		lblSurname = new JLabel("Surname");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblSurname, c);
		
		txtSurname = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtSurname, c);
		
		lblCourse = new JLabel("Course");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblCourse, c);
		
		txtCourse = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtCourse, c);
		
		lblBranch = new JLabel("College Branch");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblBranch, c);
		
		txtBranch = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtBranch, c);
		
		lblYear = new JLabel("Year");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblYear, c);
		
		txtYear = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtYear, c);
		
		lblSem = new JLabel("Semester");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblSem, c);
		
		txtSem = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtSem, c);
	
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(5, 5, 5, 5);
		add(btnSubmit, c);
		
		setVisible(true);		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String id = txtStudentID.getText();
		String fn = txtFirstName.getText();
		String sn = txtSurname.getText();
		String course = txtCourse.getText();
		String branch = txtBranch.getText();
		String year = txtYear.getText();
		String sem = txtSem.getText();
		
		if(ae.getSource() == btnSubmit) {
					
					sb = new StringBuffer();
					
					try {
						
						String urlLink = "http://192.168.5.158/Institution%20Library/insert_student.php";
						
						URL url = new URL(urlLink);
										
						connection = (HttpURLConnection) url.openConnection();
						connection.setDoInput(true);
						connection.setDoOutput(true);
						//Request setup
						connection.setRequestMethod("POST");
						
						writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
							
						String data = URLEncoder.encode("id") + "=" + URLEncoder.encode(id) + "&&" + 
						URLEncoder.encode("first_name") + "=" + URLEncoder.encode(fn) + "&&" +  
								URLEncoder.encode("surname") + "=" + URLEncoder.encode(sn) + "&&" + 
						URLEncoder.encode("course") + "=" + URLEncoder.encode(course)+ "&&" + 
								URLEncoder.encode("branch") + "=" + URLEncoder.encode(branch)+ "&&" + 
						URLEncoder.encode("year") + "=" + URLEncoder.encode(year)+ "&&" + 
								URLEncoder.encode("sem") + "=" + URLEncoder.encode(sem);
						
						writer.write(data);				
							
						writer.close();
						
						br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						
						String line = "";
						
						while((line = br.readLine()) != null) {
							
							result = line;
							
						}
							
						JOptionPane.showMessageDialog(null, result);
						
						txtStudentID.setText("");
						txtFirstName.setText("");
						txtSurname.setText("");
						txtCourse.setText("");
						txtBranch.setText("");
						txtYear.setText("");
						txtSem.setText("");
						
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
