package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject
{
	protected float x;
	protected float y;
	
	protected float velX = 0;
	protected float velY = 0;
	
	protected ID id;
	
	protected boolean jumping = false;
	protected boolean falling = true;
	
	protected int ammo = 0;
	
	
	public GameObject(float x,float y,ID id)
	{
		this.x=x;
		this.y=y;
		this.id=id;
	}
	public abstract void tick(LinkedList<GameObject>object);
	public abstract void render(Graphics g);
	public float getX() 
	{
		return x;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	public float getY() 
	{
		return y;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	public float getVelX() 
	{
		return velX;
	}
	public void setVelX(float velX) 
	{
		this.velX = velX;
	}
	public float getVelY()
	{
		return velY;
	}
	public void setVelY(float velY) 
	{
		this.velY = velY;
	}
	public ID getID()
	{
		return id;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public int getAmmo() {
		return ammo;
	}
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	public abstract Rectangle getBounds();
	
}