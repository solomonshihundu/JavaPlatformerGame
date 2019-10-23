package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Pistol extends GameObject
{
	
	private Textures tex;
	private Animation anim;
	private int ammo;

	public Pistol(float x, float y, ID id) {
		super(x, y, id);
		this.tex = Game.getInstance();
		anim = new Animation(10,tex.pistol_gun_image[0],tex.pistol_gun_image[1],tex.pistol_gun_image[2],tex.pistol_gun_image[1],tex.pistol_gun_image[0]);
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
	public int getAmmo() {
		return ammo;
	}
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

}
