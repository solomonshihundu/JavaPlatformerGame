package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Life extends GameObject
{
	
	private Textures tex;
	private Animation anim;

	public Life(float x, float y, ID id) {
		super(x, y, id);
		this.tex = Game.getInstance();
		anim = new Animation(10,tex.life_image[0],tex.life_image[1],tex.life_image[2],tex.life_image[3],tex.life_image[2],
				tex.life_image[1]);
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
