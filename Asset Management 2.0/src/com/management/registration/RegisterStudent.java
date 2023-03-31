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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class RegisterStudent extends JInternalFrame implements ActionListener {
	
	JLabel lblUsername, lblPassword, lblConfirmPassword, lblInstitute, lblIcon;
	JTextField txtUsername, txtPassword, txtConfirmPassword, txtInstitute;
	JButton btnRegister;
	GridBagLayout gbl;
	GridBagConstraints c;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;
	JPanel pan;

	public RegisterStudent() {
		super("Register New Student", true, true, true, true);
		setBounds(10, 10, 400, 450);
		
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		c = new GridBagConstraints();
		
		lblIcon = new JLabel(new ImageIcon("images/studRegistration.png"));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		add(lblIcon, c);
		
		lblUsername = new JLabel("Username");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblUsername, c);
		
		txtUsername = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 5.5;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtUsername, c);
		
		lblPassword = new JLabel("Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPassword, c);
		
		txtPassword = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPassword, c);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblConfirmPassword, c);
		
		txtConfirmPassword = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtConfirmPassword, c);
		
		lblInstitute = new JLabel("Institute");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblInstitute, c);
		
		txtInstitute = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtInstitute, c);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		add(btnRegister, c);
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String uname = txtUsername.getText();
		String p = txtPassword.getText();
		String cp = txtConfirmPassword.getText();
		String in = txtInstitute.getText();
		
		if(ae.getSource() == btnRegister) {
			
			sb = new StringBuffer();
			
			try {
				
				String urlLink = "http://localhost/LMS/studRegistration.php";
				
				URL url = new URL(urlLink);
								
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				//Request setup
				connection.setRequestMethod("POST");
				
				writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
					
				String data = URLEncoder.encode("username") + "=" + URLEncoder.encode(uname) + "&&" + 
				URLEncoder.encode("password") + "=" + URLEncoder.encode(p) + "&&" +  
						URLEncoder.encode("confirmPassword") + "=" + URLEncoder.encode(cp) + "&&" + 
				URLEncoder.encode("institute") + "=" + URLEncoder.encode(in);
				
				writer.write(data);				
					
				writer.close();
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line = "";
				
				while((line = br.readLine()) != null) {
					
					result = line;
					
				}
					
				JOptionPane.showMessageDialog(null, result);
				
				txtUsername.setText("");
				txtPassword.setText("");
				txtConfirmPassword.setText("");
				txtInstitute.setText("");
				
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
