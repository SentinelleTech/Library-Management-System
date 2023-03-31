package com.mgt.lib;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class PanelPerson_ID extends JPanel implements KeyListener {
	
	JTextField txtPID;
	
	private static HttpURLConnection connection;
	BufferedReader br;
	String str;
	StringBuffer sb;

	public PanelPerson_ID() {
		
		setLayout(null);
		
		txtPID = new JTextField();
		txtPID.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtPID.setToolTipText("<html>Please key in Person ID<br>Then press Shift to move to the next window</html>");
		txtPID.setBounds(10, 125, 480, 40);
		txtPID.addKeyListener(this);
		add(txtPID);
		
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	
		
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT) {
			
			fetch();
			
			//cl.show(panMain, "book");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
