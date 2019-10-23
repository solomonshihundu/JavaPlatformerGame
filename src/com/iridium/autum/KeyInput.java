package com.iridium.autum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private boolean keyDown[] = new boolean[4];
	public static boolean directional = true;
	
	
	public KeyInput(Handler handler)
	{
		this.handler=handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			
			if(temp.getID()==ID.Player)
			{
				if(key == KeyEvent.VK_UP && !temp.isJumping())
				{
					temp.setVelY(-14);
					temp.setJumping(true);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_X && (HUD.PISTOL_AMMO > 0 || HUD.MACH_AMMO > 0))
				{
					
					if(Game.gunMode == Game.GUNMODE.pistol)
					{
						AudioPlayer.getSound("koeing").play(1f,(float)Menu.getGameVol()/200);
						HUD.PISTOL_AMMO--;
					}
					else
					{
						AudioPlayer.getSound("machinegun").play(1f,(float)Menu.getGameVol()/200);
						HUD.MACH_AMMO--;
					}
					
					if(directional)
					{
					handler.addObject(new Bullet((int)temp.getX()+32,(int)temp.getY()+23,ID.Bullet,handler));
					}
					else
					{
						handler.addObject(new Bullet((int)temp.getX()-12,(int)temp.getY()+23,ID.Bullet,handler));
					}
					
					
				}
				
				if(key==KeyEvent.VK_DOWN){temp.setVelY(5);keyDown[1] = true;}
				if(key==KeyEvent.VK_LEFT){temp.setVelX(-5); directional = false;keyDown[2] = true;}
				if(key==KeyEvent.VK_RIGHT){temp.setVelX(5);  directional = true;keyDown[3] = true;}
				
			}
		}
		
		if(key == KeyEvent.VK_S)
		{
			if(Game.gunMode == Game.GUNMODE.machinegun)
			{
				Game.gunMode = Game.GUNMODE.pistol;
			}
			else
				Game.gunMode = Game.GUNMODE.machinegun;


		}
		if(key==KeyEvent.VK_SPACE)
		{	

	//		AudioPlayer.getSound("menuSound").play();
			if(Game.gameState == Game.STATE.game)
			{
			if(Game.paused)
			{
				Game.paused=false;
			}
			else Game.paused=true;
		    }
		
		}
		if(key == KeyEvent.VK_ESCAPE)
		{
			Game.gameState = Game.STATE.menu;
		}
		
		
	}
	public void keyReleased(KeyEvent e)
	{
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject temp=handler.object.get(i);
			if(temp.getID()==ID.Player)
			{
				if(key == KeyEvent.VK_UP){keyDown[0] = false;}
				if(key==KeyEvent.VK_DOWN){keyDown[1] = false;}
				if(key==KeyEvent.VK_LEFT){keyDown[2] = false;}
				if(key==KeyEvent.VK_RIGHT){keyDown[3] = false;}
				
				if(!keyDown[3] && !keyDown[2])
				{
					temp.setVelX(0);
				}
			}
		}
		
	}

}
