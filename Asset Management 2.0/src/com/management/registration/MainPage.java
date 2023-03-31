package com.management.registration;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MainPage extends JFrame implements MouseListener{
	
	JPanel panMain, panAdmin, panStudent, panOne;
	ImageIcon icAdmin, icStudent;
	JLabel lblA, lblS;
	Border bA, bS;
	CardLayout cl;

	public MainPage() {		
		super("Main Registration Page");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		cl = new CardLayout();
		panMain = new JPanel();
		panMain.setLayout(cl);
		
		panOne  = new JPanel();
		
		panAdmin = new JPanel();
		bA = BorderFactory.createTitledBorder(bA, "ADMIN", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 20), Color.BLUE);
		panAdmin.setBorder(bA);
		icAdmin = new ImageIcon("images/adminMain.png");
		lblA = new JLabel(icAdmin);
		panAdmin.addMouseListener(this);
		panAdmin.add(lblA);
		panOne.add(panAdmin, BorderLayout.WEST);
		
		
		panStudent = new JPanel();
		bS = BorderFactory.createTitledBorder(bS, "STUDENT", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 20), Color.BLUE);
		panStudent.setBorder(bS);
		icStudent = new ImageIcon("images/studentMain.png");
		lblS = new JLabel(icStudent);
		panStudent.addMouseListener(this);
		panStudent.add(lblS);
		panOne.add(panStudent, BorderLayout.EAST);
		
		panMain.add(panOne);
		panMain.add(new AdminMainPanel(), "main");
		
		
		add(panMain);
		
		pack();
		
		setVisible(true);
	}

	

	public static void main(String[] args) {
		new MainPage();

	}



	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource() == panAdmin) {
			
			cl.show(panMain, "main");
			
		}
		
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {}



	@Override
	public void mouseExited(MouseEvent arg0) {}



	@Override
	public void mousePressed(MouseEvent arg0) {}



	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
