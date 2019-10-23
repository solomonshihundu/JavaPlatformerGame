package com.iridium.autum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD
{
	public static int LIFE=3;
	public static int HEALTH_INDEX = 100;
	
	public static int PISTOL_AMMO = 100;
	public static int MACH_AMMO = 100;
	
	public int points=0;
	public int level=1;
	
	public void render(Graphics g)
	{
		
		if(LIFE == 1)
		{
			 g.drawImage(Textures.hud_life,10, 10, null);
		}
		else if(LIFE == 2)
		{
			g.drawImage(Textures.hud_life,10, 10, null);
			g.drawImage(Textures.hud_life,30, 10, null);
		}
		else if(LIFE == 3)
		{
			g.drawImage(Textures.hud_life,10, 10, null);
			g.drawImage(Textures.hud_life,30, 10, null);
			g.drawImage(Textures.hud_life,50, 10, null);
		}
		
		
		if(PISTOL_AMMO > 0 && Game.gunMode == Game.GUNMODE.pistol)
		{
		    g.drawImage(Textures.hud_pistol,140, 15, null);
			
			g.setColor(Color.black);
			g.drawRect(175, 22, 110, 16);
			g.setColor(Color.gray);
			for(int xx = 0;xx <HUD.PISTOL_AMMO; xx+=6)
			{
				g.fillRect(180+xx, 25, 5, 10);
			}
		}
		if(MACH_AMMO > 0 && Game.gunMode == Game.GUNMODE.machinegun)
		{
			
			
			g.drawImage(Textures.hud_AK47,140, 15, null);
			
			g.setColor(Color.black);
			g.drawRect(175, 22, 110, 16);
			g.setColor(Color.gray);
			for(int xx = 0;xx <HUD.MACH_AMMO; xx+=6)
			{
				g.fillRect(180+xx, 25, 5, 10);
			}
		}
		g.setColor(new Color(0,19,127,200));
		g.setFont(new Font("Algerian",Font.BOLD+Font.ITALIC,15));
		g.drawString("Level : "+getLevel(), 15, 55);
		g.drawString("Points : "+getPoints(), 105,55);
		
	}
	public void tick()
	{
		if(HEALTH_INDEX <= 0)
		{
			LIFE--;
			HEALTH_INDEX =100;
		}
		
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
