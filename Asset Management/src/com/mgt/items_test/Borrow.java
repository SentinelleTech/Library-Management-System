package com.mgt.items_test;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Robot;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Borrow extends JInternalFrame implements KeyListener  {
	
	JTextField txtPID, txtBID;
	JPanel panMain, panP, panB;
	CardLayout cl;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str, result;
	StringBuffer sb;
	
	public Borrow(){	
		super("Borrow Book", true, true, true, true);
		setBounds(200, 100, 500, 300);
		setResizable(false);
		
		panMain = new JPanel();
		cl = new CardLayout();
		panMain.setLayout(cl);
		
		
///////////// panel for Person ID ////////////////////		
		panP = new JPanel();
		panP.setBackground(java.awt.Color.RED);
		panP.setLayout(null);
		
		txtPID = new JTextField(12);
		txtPID.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtPID.setToolTipText("<html>Please key in Person ID<br>Then press Shift to move to the next window</html>");
		txtPID.setBounds(10, 125, 480, 40);
		txtPID.addKeyListener(this);
		panP.add(txtPID);
		
		panMain.add(panP, "person");
///////////// panel for Person ID ends here ////////////////////		
	
///////////// panel for Book ID ////////////////////		
		panB = new JPanel();
		panB.setBackground(java.awt.Color.BLUE);
		panB.setLayout(null);
		
		txtBID = new JTextField();
		txtBID.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtBID.setToolTipText("<html>Please key in Book ID<br>Then press Enter to borrow a book</html>");
		txtBID.setBounds(10, 125, 480, 40);
		txtBID.addKeyListener(this);
		panB.add(txtBID);
		
		panMain.add(panB, "book");
///////////// panel for Book ID ends here ////////////////////		
		
		add(panMain);
		
		setVisible(true);
	}
	
	public void getTextFieldData() {
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {}
	
	@Override
	public void keyTyped(KeyEvent ke) {}


	@Override
	public void keyReleased(KeyEvent ke) {
		
		String id = txtPID.getText();
		String bkid = txtBID.getText();
		String urlLink = "http://192.168.5.158/Institution%20Library/borrow_book.php";
		
		if(id.length() == 7 && id.charAt(4) == '-') {			
			
			
			cl.show(panMain, "book");
	
			
		}else {
			

			
			try {			
				
				
				URL url = new URL(urlLink);
								
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				//Request setup
				connection.setRequestMethod("POST");
				
				writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
					
				String data = URLEncoder.encode("st_id") + "=" + URLEncoder.encode(id) + "&&" +
							  URLEncoder.encode("bk_id") + "=" + URLEncoder.encode(bkid);
				
				writer.write(data);				
					
				writer.close();
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line = br.readLine();			
					
				while((line = br.readLine()) != null) {
					
					result = line;
					
				}
					
					
				JOptionPane.showMessageDialog(null, result);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
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
