package com.iridium.autum;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation 
{
	private int index = 0;
	private int count = 0;
	private int frame = 0;
	private int speed = 0;
	private BufferedImage [] images = null;
	private BufferedImage currentImage = null;
	
	public Animation(int speed,BufferedImage...args)
	{
		this.speed = speed;
		images = new BufferedImage[args.length];
		
		for(int i= 0 ; i < args.length ; i++)
		{
			images[i] = args[i];
		}
		frame= args.length;
		
	}
	public void runAnimation()
	{
		index ++;
		if(index > speed)
		{
			nextFrame();
			index = 0;
		}
	}
	private void nextFrame()
	{
		for(int i = 0;i < frame ;i++)
		{
			if(count == i)
			{
			currentImage = images[i];
			}
		}
		
		count ++;
		
		if(count > frame)
		{
			count = 0;
		}
	}
	public void drawAnimation(Graphics g,int x,int y)
	{
		g.drawImage(currentImage, x, y, null);
	}
	public void drawAnimation(Graphics g,int x,int y,int scalex,int scaley)
	{
		g.drawImage(currentImage, x, y,scalex,scaley, null);
	}

}
