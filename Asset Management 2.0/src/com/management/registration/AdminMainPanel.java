package com.management.registration;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class AdminMainPanel extends JPanel  implements MouseListener {
	
	JLabel lblLogin, lblRegister;
	JPanel panR, panL, panMain, pan, pann;
	Border bA, bS;
	ImageIcon icLogin, icRegister;
	CardLayout cl;

	public AdminMainPanel() {
		
		cl = new CardLayout();
		panMain = new JPanel();
		
		
		pan = new JPanel();
		
		panL = new JPanel();
		bA = BorderFactory.createTitledBorder(bA, "LOGIN", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 20), Color.BLUE);
		panL.setBorder(bA);
		icLogin = new ImageIcon("images/adminLogin.png");
		lblLogin = new JLabel(icLogin);		
		panL.addMouseListener(this);
		panL.add(lblLogin);
		
		pan.add(panL);
		
		panR = new JPanel();
		bS = BorderFactory.createTitledBorder(bA, "REGISTER", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 20), Color.BLUE);
		panR.setBorder(bS);
		icRegister = new ImageIcon("images/admin.png");
		lblRegister = new JLabel(icRegister);
		panR.addMouseListener(this);
		panR.add(lblRegister);
		
		pan.add(panR);
		
		panMain.add(pan, BorderLayout.NORTH);
		
		pann = new JPanel();
		pann.setLayout(cl);
		pann.add(new AdminLogin(), "login");
		pann.add(new AdminRegister(), "register");
		
		panMain.add(pann, BorderLayout.SOUTH);
		
		
		add(panMain);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == panL) {
			
			cl.show(pann, "login");
			
		}else {
			
			cl.show(pann, "register");
			
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
