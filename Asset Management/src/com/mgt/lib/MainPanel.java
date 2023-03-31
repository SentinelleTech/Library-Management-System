package com.mgt.lib;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	public MainPanel() {
		
		add(new PanelBook_ID(), "book");
		add(new PanelPerson_ID(), "person");
		
	}

}
