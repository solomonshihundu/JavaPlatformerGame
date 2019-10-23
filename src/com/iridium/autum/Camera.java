package com.iridium.autum;


public class Camera 
{
	private float x = 0;
	private float y = 0;
	
	
	public Camera(float x,float y)
	{
		this.x = x;
		this.y = y;
		
	}
	public void tick(GameObject player)
	{
		x = -player.getX()+100;
	//	y = -player.getY()+350;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
