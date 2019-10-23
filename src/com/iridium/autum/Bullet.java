package com.iridium.autum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject
{
	private Handler handler;

	public Bullet(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	if(KeyInput.directional)
	{
		velX=10;
	}
	else{
		velX=10*-1;
	}
		
	}	
	public void tick(LinkedList<GameObject> object) 
	{
		
		x+=velX;
		
		
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			if(temp.getID() == ID.Block)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					handler.removeObject(this);
				}
			}
		}	
	}

	
	public void render(Graphics g)
	{
		g.setColor(new Color(249,225,0));
		g.fillRect((int)x, (int)y, 8, 2);
	}
	public Rectangle getBounds()
	{
		
		return new Rectangle((int)x,(int)y,5, 5);
	}

}
