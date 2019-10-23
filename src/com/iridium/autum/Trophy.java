package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Trophy extends GameObject
{
	
	private Textures tex;
	private Animation anim;

	public Trophy(float x, float y, ID id) {
		super(x, y, id);
		this.tex = Game.getInstance();
		anim = new Animation(3,tex.trophy_image[0],tex.trophy_image[1],tex.trophy_image[2],tex.trophy_image[3],tex.trophy_image[2],tex.trophy_image[1],tex.trophy_image[0],tex.trophy_image[1],tex.trophy_image[2]);
	}	
	public void tick(LinkedList<GameObject> object) 
	{
		anim.runAnimation();
	}

	
	public void render(Graphics g)
	{
		
		anim.drawAnimation(g, (int)x, (int)y);
		
	}

	
	public Rectangle getBounds()
	{
		
		return new Rectangle((int)x,(int)y,32, 32);
	}

}
