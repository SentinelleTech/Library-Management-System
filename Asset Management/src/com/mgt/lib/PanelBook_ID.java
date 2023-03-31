package com.mgt.lib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.io.BufferedReader;
import java.net.HttpURLConnection;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBook_ID extends JPanel implements KeyListener {
	
	JTextField txtBID;
	private static HttpURLConnection connection;
	BufferedReader br;
	String str;
	StringBuffer sb;

	public PanelBook_ID() {
		
		setLayout(null);
		
		txtBID = new JTextField();
		txtBID.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtBID.setToolTipText("<html>Please key in Book ID<br>Then press Enter to borrow a book</html>");
		txtBID.setBounds(10, 125, 480, 40);
		txtBID.addKeyListener(this);
		add(txtBID);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
