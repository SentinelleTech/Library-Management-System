package com.management.registration;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class AdminLogin extends JPanel implements ActionListener {
	
	JLabel lblUsername, lblPassword;
	JTextField txtUsername, txtPassword;
	JButton btnLogin;
	Border b;
	GridBagLayout gbl;
	GridBagConstraints c;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;

	public AdminLogin() {
		
		b = BorderFactory.createTitledBorder(b, "LOGIN", TitledBorder.CENTER, TitledBorder.TOP, new Font("Monotype Corsiva", Font.BOLD, 20), Color.BLUE);
		setBorder(b);
		
		gbl = new GridBagLayout();
		setLayout(gbl);
		c = new GridBagConstraints();
		
		lblUsername = new JLabel("Username");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblUsername, c);
		
		txtUsername = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 5.5;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtUsername, c);
		
		lblPassword = new JLabel("Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPassword, c);
		
		txtPassword = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPassword, c);
		
		btnLogin = new JButton("Submit");
		btnLogin.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(btnLogin, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String uname = txtUsername.getText();
		String p = txtPassword.getText();
		
		if(ae.getSource() == btnLogin) {
			
			sb = new StringBuffer();
			
			try {
				
				String urlLink = "http://localhost/LMS/adminLogin.php";
				
				URL url = new URL(urlLink);
								
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				//Request setup
				connection.setRequestMethod("POST");
				
				writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
					
				String data = URLEncoder.encode("username") + "=" + URLEncoder.encode(uname) + "&&" + 
				URLEncoder.encode("password") + "=" + URLEncoder.encode(p);
				
				writer.write(data);				
					
				writer.close();
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line = "";
				
				while((line = br.readLine()) != null) {
					
					result = line;
					
				}
					
				JOptionPane.showMessageDialog(null, result);
				
				new MainAdminWindow();
				
//				txtUsername.setText("");
//				txtPassword.setText("");
				
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
