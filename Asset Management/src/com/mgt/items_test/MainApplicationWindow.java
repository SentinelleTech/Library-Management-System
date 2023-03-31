package com.mgt.items_test;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainApplicationWindow extends JFrame implements ActionListener {
	
	JMenuBar bar;
	JMenu mnuMember, mnuItem;
	JMenuItem itAddMember, itAddBook, itBorrowBook, itReturnBook;
	JDesktopPane pane;	
	JPanel pan;

	public MainApplicationWindow() {
		super("Asset Management Software");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		//setContentPane(new ImagePanel());
		
		bar = new JMenuBar();
		
		mnuMember = new JMenu("Member");
		
		itAddMember = new JMenuItem("Add Member");
		itAddMember.addActionListener(this);
		mnuMember.add(itAddMember);
		
		bar.add(mnuMember);
		
		
		mnuItem = new JMenu("Item");
		
		itAddBook = new JMenuItem("Add Book");
		itAddBook.addActionListener(this);
		mnuItem.add(itAddBook);
		
		itBorrowBook = new JMenuItem("Borrow Book");
		itBorrowBook.addActionListener(this);
		mnuItem.add(itBorrowBook);
		
		itReturnBook = new JMenuItem("Return Book");
		itReturnBook.addActionListener(this);
		mnuItem.add(itReturnBook);
		
		bar.add(mnuItem);
		
		setJMenuBar(bar);
		
		pane = new JDesktopPane();
		add(pane);
		
		setVisible(true);
	}

	

	public static void main(String[] args) {
		new MainApplicationWindow();

	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		
		if(src == itAddMember) {
			
			pane.add(new AddStudent());
			
		}else if(src == itBorrowBook) {
			
			pane.add(new Borrow());
			
		}else if(src == itReturnBook) {
			
			pane.add(new Return());
			
		}else if(src == itAddBook){
			
			pane.add(new AddBook());
			
		}
	}

}
