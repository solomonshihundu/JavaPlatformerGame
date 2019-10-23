package com.iridium.autum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject
{
	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private Handler handler;
	private Textures tex;
	
	private Animation animRight_pistol;
    private Animation animLeft_pistol;
    
    private Animation animRight_machinegun;
    private Animation animLeft_machinegun;
    
    private HUD hud;
 
    
	public Player(float x, float y, ID id,Handler handler,HUD hud)
	{
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		tex = Game.getInstance();
		
		animRight_machinegun = new Animation(3,tex.soldier_image[1],tex.soldier_image[2],tex.soldier_image[3],tex.soldier_image[4]);
		animLeft_machinegun = new Animation(3,tex.soldier_image[6],tex.soldier_image[7],tex.soldier_image[8],tex.soldier_image[9]);
		
		animRight_pistol = new Animation(3,tex.soldier_image[11],tex.soldier_image[12],tex.soldier_image[13],tex.soldier_image[14]);
		animLeft_pistol = new Animation(3,tex.soldier_image[16],tex.soldier_image[17],tex.soldier_image[18],tex.soldier_image[19]);
		

	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		x += velX;
		y += velY;
		
		
		if(HUD.PISTOL_AMMO <= 0)
		{
			Game.gunMode = Game.GUNMODE.machinegun;
		}
		else if(HUD.MACH_AMMO <= 0)
		{
			Game.gunMode = Game.GUNMODE.pistol;
		}
				
		if(jumping || falling)
		{
			velY+=gravity;
			
			if(velY > MAX_SPEED)
			{
				velY = MAX_SPEED;
				
			}
		}
		collision(object);
		
		if(velX > 0)
		{
			if(Game.gunMode == Game.GUNMODE.pistol)
			{
				animRight_pistol.runAnimation();
			}
			else
				animRight_machinegun.runAnimation();
		}
		else if(velX < 0)
		{
			if(Game.gunMode == Game.GUNMODE.pistol)
			{
				animLeft_pistol.runAnimation();
			}
			else
			    animLeft_machinegun.runAnimation();
		}
	}

	
	public void render(Graphics g) 
	{
		if(KeyInput.directional)
		{
			if(jumping)
			{
				if(Game.gunMode == Game.GUNMODE.pistol)
				{
				g.drawImage(tex.player_jumping[0], (int)x, (int)y, null);
					
				}
				else
					g.drawImage(tex.player_jumping[1], (int)x, (int)y, null);
			}
			else if(velX == 0)
		    {
				if(Game.gunMode == Game.GUNMODE.pistol)
				{
	        	g.drawImage(tex.soldier_image[10],(int)x,(int)y,null);
					
		        }
				else
					g.drawImage(tex.soldier_image[0],(int)x,(int)y,null);
		    }
		   else 
		    {
			   if(Game.gunMode == Game.GUNMODE.pistol)
			   {
			    animRight_pistol.drawAnimation(g, (int)x, (int)y);
		
			   }
			   else
				   animRight_machinegun.drawAnimation(g, (int)x, (int)y);
		    }
		}
		else if(!KeyInput.directional)
		{
			if(jumping)
			{
				if(Game.gunMode == Game.GUNMODE.pistol)
				{
				g.drawImage(tex.player_jumping[3], (int)x, (int)y, null);
			
				}
				else
					g.drawImage(tex.player_jumping[2], (int)x, (int)y, null);
			}
			else if(velX == 0)
			{
				if(Game.gunMode == Game.GUNMODE.pistol)
				{
			g.drawImage(tex.soldier_image[15],(int)x,(int)y,null);
			}
			else
				g.drawImage(tex.soldier_image[5],(int)x,(int)y,null);
		}
			else 
			{
				if(Game.gunMode == Game.GUNMODE.pistol)
				{
				animLeft_pistol.drawAnimation(g, (int)x, (int)y);
				}
				else
					animLeft_machinegun.drawAnimation(g, (int)x, (int)y);
			}
		}
	
	}
	public void collision(LinkedList <GameObject> object)
	{
		for(int i = 0;i<handler.object.size() ; i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp.getID() == ID.Block)
			{	
				//top
			if(getBoundsTop().intersects(temp.getBounds()))
			{	    
					y = temp.getY()+32;
				    velY = 0;	    
			}	
				
				//bottom
			if(getBoundsBottom().intersects(temp.getBounds()))
			{
				falling = false;
			    jumping = false;
			    
				y = temp.getY()-height+5;
			    velY = 0;    
			}
			else
			{
				falling = true;
			}
			
			//right
			if(getBoundsRight().intersects(temp.getBounds()))
			{		    
				x = temp.getX()-32;
			}
			
			//left
			if(getBoundsLeft().intersects(temp.getBounds()))
			{
				x = temp.getX()+32;
			  		 
			}
			}
			else if(temp.getID() == ID.Coin)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
				handler.removeObject(temp);
				hud.points+=10;
				}
			}
			else if(temp.getID() == ID.Trophy)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					hud.level++;
				    handler.nextLevel();
	
				}
			}
			else if(temp.getID() == ID.Pistol)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
				handler.removeObject(temp);
				if(HUD.PISTOL_AMMO<99)
				{
				HUD.PISTOL_AMMO+=10;
				}
				}
			}
			else if(temp.getID() == ID.MachineGun)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
				handler.removeObject(temp);
				if(HUD.MACH_AMMO<99)
				{
				HUD.MACH_AMMO+=10;
				}
				
				}
			}
			else if(temp.getID() == ID.Life)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					if(HUD.LIFE<3)
					{
				handler.removeObject(temp);
				HUD.LIFE++;
					}
				}
			}
			else if(temp.getID() == ID.Water)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
				
					HUD.HEALTH_INDEX-=5;
				}
			}
			else if(temp.getID() == ID.EnemyGuard)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
				HUD.HEALTH_INDEX--;
				}
			}
			
		}
		
	}
	
	public Rectangle getBoundsBottom() 
	{	
		return new Rectangle((int) ((int)x+width/2)-8,(int) ((int)y+height/2),(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsTop() 
	{	
		return new Rectangle((int) (((int)x+width/2)-8),(int)y,(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsRight() 
	{	
		return new Rectangle((int) ((int)x+width-8),(int)y+5,(int)width/4,(int)height-10);
	}
	public Rectangle getBoundsLeft() 
	{	
		return new Rectangle((int)x,(int)y+5,8,(int)height-10);
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,30,60);
	}


}
