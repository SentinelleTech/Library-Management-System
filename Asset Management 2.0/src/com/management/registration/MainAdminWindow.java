package com.management.registration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainAdminWindow extends JFrame implements ActionListener {
	
	JPanel panLeft, panCenter;
	Box b;
	JButton btnIssue, btnReturn;
	JButton btnOne, btnTwo, btnThree, btnAddItem, btnCus, btnEmp, btnMgt, btnProducts;
	JLabel lbl, lbl2;
	ImageIcon ic;
	JDesktopPane pane;

	public MainAdminWindow() {
		super("ADMIN DASHBOARD");
		setSize(800, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		panLeft = new JPanel();
		panLeft.setBackground(Color.gray);
		
		b = Box.createVerticalBox();
		
		
		
		lbl = new JLabel("WELCOME ADMIN");
		lbl.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		lbl.setForeground(Color.BLUE);
		lbl.setMaximumSize(new Dimension(200, 60));
		b.add(lbl);
		b.add(Box.createVerticalStrut(30));
		
		ic = new ImageIcon("images/admin.png");
		lbl2 = new JLabel(ic);
		b.add(lbl2);
		b.add(Box.createVerticalStrut(30));
		
		btnAddItem = new JButton("ADD ITEM");
		btnAddItem.addActionListener(this);
		btnAddItem.setMaximumSize(new Dimension(200, 60));
		b.add(btnAddItem);
		b.add(Box.createVerticalStrut(30));
		
		btnCus = new JButton("ISSUE ITEM");
		btnCus.setMaximumSize(new Dimension(200, 60));
		b.add(btnCus);
		b.add(Box.createVerticalStrut(30));

		btnEmp = new JButton("RETURN ITEM");
		btnEmp.setMaximumSize(new Dimension(200, 60));
		b.add(btnEmp);
		b.add(Box.createVerticalStrut(30));

		btnMgt = new JButton("ADD MEMBER");
		btnMgt.addActionListener(this);
		btnMgt.setMaximumSize(new Dimension(200, 60));
		b.add(btnMgt);
		b.add(Box.createVerticalStrut(30));

		btnProducts = new JButton("VIEW");
		btnProducts.setMaximumSize(new Dimension(200, 60));
		b.add(btnProducts);
		
		panLeft.add(b);
		
		add(panLeft, BorderLayout.WEST);
		
		
//		panCenter = new JPanel();
//		panCenter.setBackground(Color.WHITE);
//		
//		add(panCenter, BorderLayout.CENTER);
		
		pane = new JDesktopPane();
		
		add(pane);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainAdminWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnMgt) {
			
			pane.add(new RegisterStudent());
			
		}else if(e.getSource() == btnAddItem) {
			
			pane.add(new AddItem());
			
		}
		
		
	}

}
