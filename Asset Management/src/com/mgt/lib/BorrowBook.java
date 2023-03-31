package com.mgt.lib;

import java.awt.CardLayout;
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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class BorrowBook extends JFrame implements KeyListener  {
	
	JTextField txtPID, txtBID;
	JPanel panMain, panP, panB;
	CardLayout cl;
	private static HttpURLConnection connection;
	BufferedWriter writer;
	BufferedReader br;
	String str;
	StringBuffer sb;
	int result;

	public BorrowBook(){	
		super("Borrow Book");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		
		panMain = new JPanel();
		cl = new CardLayout();
		panMain.setLayout(cl);
		
		
///////////// panel for Person ID ////////////////////		
		panP = new JPanel();
		panP.setBackground(java.awt.Color.RED);
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


	public static void main(String[] args) {
		new BorrowBook();

	}


	@Override
	public void keyPressed(KeyEvent arg0) {}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}


	@Override
	public void keyReleased(KeyEvent ke) {
		
		String id = txtPID.getText();
		String bkid = txtBID.getText();
		String urlLink = "http://localhost/Institution%20Library/member.php";
		String urlLink2 = "http://localhost/Institution%20Library/books.php";
		
		if(ke.getSource() == txtPID) {
			
			if(id.length() == 7 && id.charAt(4) == '-') {
			
				if(id != null) {
				
					int r = checkID("st_id", id, urlLink);
					
					if(r == Integer.parseInt(id)) {
					
						cl.show(panMain, "book");
					
					}
				
				}
			
			}
			
		}else {
			
			if(bkid != null) {
				
				int r = checkID2("bk_id", bkid, urlLink2);
				
				if(r == Integer.parseInt(bkid)) {
				
					//cl.show(panMain, "book");
					
					JOptionPane.showMessageDialog(null, "FOUND");
				
				}
			
			}
			
		}
		
	}
	
	
	public int checkID(String phpID, String UI_ID, String urlLink) {
	
		int i = 0;
		
		try {			
			
			
			URL url = new URL(urlLink);
							
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//Request setup
			connection.setRequestMethod("POST");
			
			writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				
			String data = URLEncoder.encode(phpID) + "=" + URLEncoder.encode(UI_ID) ;
			
			writer.write(data);				
				
			writer.close();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line = br.readLine();			
				
				if( (i = traverse(line)) == Integer.parseInt(UI_ID)) {						

					result = i;
					
					JOptionPane.showMessageDialog(null, i);
					
				}
				
				
			//JOptionPane.showMessageDialog(null, result);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.disconnect();
		}
		
		return result;
	}

	
	
	public int checkID2(String phpID, String UI_ID, String urlLink) {
		
		int i = 0;
		
		try {			
			
			
			URL url = new URL(urlLink);
							
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//Request setup
			connection.setRequestMethod("POST");
			
			writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				
			String data = URLEncoder.encode(phpID) + "=" + URLEncoder.encode(UI_ID) ;
			
			writer.write(data);				
				
			writer.close();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line = br.readLine();			
				
				if( (i = traverse2(line)) == Integer.parseInt(UI_ID)) {						

					result = i;
					
					JOptionPane.showMessageDialog(null, i);
					
				}
				
				
			//JOptionPane.showMessageDialog(null, result);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.disconnect();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int traverse(String responseBody){
		
		int studentID = 0;
		
		JSONArray p = new JSONArray(responseBody);
		
		for(int i = 0; i < p.length(); i++){
			
			JSONObject alb = p.getJSONObject(i);
			
			studentID = alb.getInt("student_id");
		}
		
		return studentID;
	}
	
	
	public static int traverse2(String responseBody){
		
		int bookID = 0;
		
		JSONArray p = new JSONArray(responseBody);
		
		for(int i = 0; i < p.length(); i++){
			
			JSONObject alb = p.getJSONObject(i);
			
			bookID = alb.getInt("book_id");
		}
		
		return bookID;
	}

	

}
