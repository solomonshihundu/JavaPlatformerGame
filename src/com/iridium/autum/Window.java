package com.iridium.autum;

import java.awt.Dimension;

import javax.swing.JFrame;


public class Window 
{
	public Window(int width,int height,String title,Game game)
	{
		JFrame frame=new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.pack();
		game.start();
	}
}


