package com.kise.products;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailsPanel extends JPanel {

	GridBagLayout gbl;
	GridBagConstraints c;
	JLabel lblPName, lblPDescription, lblPQuantity;
	JTextField txtPName, txtPQuantity;
	JTextArea txtPDescription;

	public DetailsPanel() {
		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gbl);
		
		lblPName = new JLabel("Name");
		lblPName.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPName, c);
		
		txtPName = new JTextField();
		txtPName.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 5.4;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPName, c);
		
		lblPDescription = new JLabel("Description");
		lblPDescription.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPDescription, c);
		
		txtPDescription = new JTextArea();
		txtPDescription.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPDescription, c);
		
		lblPQuantity = new JLabel("Quantity");
		lblPQuantity.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(lblPQuantity, c);
		
		txtPQuantity = new JTextField();
		txtPQuantity.setFont(new Font("Consolas", Font.BOLD, 30));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		add(txtPQuantity, c);
		
	}

}
