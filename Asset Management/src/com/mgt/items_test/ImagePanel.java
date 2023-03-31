package com.mgt.items_test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	BufferedImage myImage;

	public ImagePanel() {
	
		try {
			
			myImage = ImageIO.read(new File("images/Library-Management.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void paint(Graphics g) {
		
		g.drawImage(myImage, 0, 0, this);
		
		//super.paint(g);
	}
	
	

	

}
