package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class EnemyGuard extends GameObject
{
	private float width = 32, height = 64;
	private Textures tex;
	
	private Handler handler;
	public static boolean facingRight = false; 
	private Random r = new Random();
	private int shoot ;
	private int healthIndex = 30;
	
	public EnemyGuard(float x, float y, ID id,Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
		this.tex = Game.getInstance();
		

	}	
	public void tick(LinkedList<GameObject> object) 
	{
		 
		 shoot = r.nextInt(10);
		x += velX;
		collision();
		
		
		
	}

	
	public void render(Graphics g)
	{
	
		for(int i=0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{
			
				if(tempObject.getX() > this.getX())
				{
						g.drawImage(tex.enemy[0], (int)x, (int)y , null);
						facingRight = true; 
				}
				else if(tempObject.getX() < this.getX())
				{
					g.drawImage(tex.enemy[3], (int)x, (int)y , null);
					facingRight = false; 
				}
				
			}			
		}
		
		
	}
	
	public void  collision()
	{
		for(int i = 0;i<handler.object.size() ; i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp.getID() == ID.Block)
			{
				if(getBoundsAll().intersects(temp.getBounds()))
				{
					velY = 0;
				}
		        if(getBounds().intersects(temp.getBounds())) 
		       {
		         	falling = false;
		            jumping = false;
		    
			        y = temp.getY()-height+5;
		      
		        }
		    }
			if(temp.getID() == ID.Player)
			{
				if(getBoundsWide().intersects(temp.getBounds()))
				{
					if(temp.getX() > this.getX())
					{
						if(shoot == 0)
						{
							AudioPlayer.getSound("machinegun").play(1f,(float)Menu.getGameVol()/200);
				        	handler.addObject(new EnemyBullet((int)this.getX()+32,(int)this.getY()+23,ID.EnemyBullet,handler));
					
						}
					}
					else if(temp.getX() < this.getX())
					{
						if(shoot == 0)
						{
							AudioPlayer.getSound("machinegun").play(1f,(float)Menu.getGameVol()/200);
					    	handler.addObject(new EnemyBullet((int)this.getX()-12,(int)this.getY()+23,ID.EnemyBullet,handler));
						}
					}
				}
			}
			if(temp.getID() == ID.Bullet)
			{
				if(getBoundsAll().intersects(temp.getBounds()))
				{
					healthIndex -- ;
					
					if(Game.gunMode == Game.GUNMODE.pistol)
					{
					   if(healthIndex <= 0)
					   {
					        handler.removeObject(this);
					   }
					}
					else if(Game.gunMode == Game.GUNMODE.machinegun)
					{
						if(healthIndex <= 10)
						{
						handler.removeObject(this);
						}
					}
				}
			}
	    }
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) ((int)x+width/2)-8,(int) ((int)y+height/2),(int)width/2,(int)height/2);
	}
	
	public Rectangle getBoundsAll()
	{
		return new Rectangle((int)x,(int)y,30,60);
	}
	public Rectangle getBoundsWide()
	{
		return new Rectangle((int)x-577,(int)y,1300,60);
	}
	
}
