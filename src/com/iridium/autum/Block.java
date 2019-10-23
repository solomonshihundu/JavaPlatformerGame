package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Block extends GameObject
{
	
	private Textures tex;
	private int type = 0;

	public Block(float x, float y, ID id,int type) {
		super(x, y, id);
		this.tex = Game.getInstance();
		this.type = type;
	}	
	public void tick(LinkedList<GameObject> object) 
	{
		
	}

	public void render(Graphics g)
	{
		if(type == 0)
		{
		g.drawImage(tex.block_image,(int) x, (int)y, null);
		}
		else if(type == 1)
		{
			g.drawImage(tex.grass_image,(int) x, (int)y, null);
		}
		else if(type == 2)
		{
			g.drawImage(tex.grass_image_left,(int) x, (int)y, null);
		}
		else if(type == 3)
		{
			g.drawImage(tex.grass_image_right,(int) x, (int)y, null);
		}
	}
	
	public Rectangle getBounds()
	{
		
		return new Rectangle((int)x,(int)y,32, 32);
	}

}
