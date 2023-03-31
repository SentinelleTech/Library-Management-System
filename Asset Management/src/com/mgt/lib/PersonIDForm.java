package com.mgt.lib;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonIDForm extends JFrame implements KeyListener {
	
	JTextField txtPID, txtBID;
	JPanel panMain, panP, panB;
	CardLayout cl;
	
	private static HttpURLConnection connection;
	BufferedReader br;
	String str;
	StringBuffer sb;

	public PersonIDForm() {
		super("Person ID Form");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		
		panMain = new JPanel();
		cl = new CardLayout();
		panMain.setLayout(cl);
		
		
///////////// panel for Person ID ////////////////////		
		panP = new JPanel();
		panP.setLayout(null);
		
		txtPID = new JTextField();
		txtPID.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtPID.setToolTipText("<html>Please key in Person ID<br>Then press Shift to move to the next window</html>");
		txtPID.setBounds(10, 125, 480, 40);
		txtPID.addKeyListener(this);
		panP.add(txtPID);
		
		panMain.add(panP, "person");
///////////// panel for Person ID ends here ////////////////////		
	
///////////// panel for Book ID ////////////////////		
		panB = new JPanel();
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
	
	public void fetch() {
		
		
		sb = new StringBuffer();
		
		try {
			
			URL url = new URL("http://localhost/Institution%20Library/member.php");
			
			connection = (HttpURLConnection) url.openConnection();
			
			//Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			//System.out.println(status);
			
			if(status > 299){
				
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				
				while((str = br.readLine()) != null){
					sb.append(str);
				}
				br.close();
				
			}else{
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				while((str = br.readLine()) != null){
					sb.append(str);
				}
				br.close();
			}
			parse(sb.toString());
			
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
	
	public String parse(String responseBody){
				
		int typed_id = Integer.parseInt(txtPID.getText());
		
		JSONArray mtu = new JSONArray(responseBody);
		
		for(int i = 0; i < mtu.length(); i++){
			
			JSONObject alb = mtu.getJSONObject(i);
			
			int id = alb.getInt("Borrower_Id");
			
			if(typed_id == id) {
				
				JOptionPane.showMessageDialog(null, "Your ID is good and has been found");
				
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		new PersonIDForm();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent ke) {
	
		
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT) {
			
			fetch();
			
			cl.show(panMain, "book");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}