package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Water extends GameObject
{
	
	private Textures tex;
	

	public Water(float x, float y, ID id) {
		super(x, y, id);
		this.tex = Game.getInstance();
	
	}	
	public void tick(LinkedList<GameObject> object) 
	{
		
	}

	public void render(Graphics g)
	{
		g.drawImage(tex.water[0],(int) x, (int)y, null);
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32, 32);
	}

}
