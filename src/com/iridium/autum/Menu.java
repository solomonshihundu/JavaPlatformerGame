package com.iridium.autum;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
	private HUD hud;
	private Handler handler;
	private Textures tex;

	public static float MUSIC_VOL = 0.5f;
	public static int GAME_VOL = 20;
	
	public Menu(Handler handler,HUD hud,Textures tex)
	{
		this.hud = hud;
		this.handler = handler;
		this.tex = tex;		
	}
	public void mousePressed(MouseEvent e)
	{

		int mx=e.getX();
		int my=e.getY();
		
		if(Game.gameState==Game.STATE.menu)
		{
			
		//play
		if(mouseOver(mx,my,40,155,200,50))
		{
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);	
			Game.gameState= Game.STATE.game;
		}
		
		//options
		if(mouseOver(mx,my,90,220,200,50))
		{
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			Game.gameState= Game.STATE.options;

			
		}
		
		//quit
		if(mouseOver(mx,my,140,285,200,50))
		{
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			handler.reset();
			System.exit(0);
			
		}
		}
		else if(Game.gameState==Game.STATE.options)
		{
			//help
			if(mouseOver(mx,my,280,150,200,30))
			{
				AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
				Game.gameState = Game.STATE.help;
				
			}
			//volume
			if(mouseOver(mx,my,280,250,200,30))
			{
				AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
				Game.gameState = Game.STATE.volume;
			}
			//credits
			if(mouseOver(mx,my,280,350,200,30))
			{
				AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
				Game.gameState = Game.STATE.credits;
			}
			
	     	//back from help
		   if(mouseOver(mx,my,305,490,150,40))
		    {
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			Game.gameState=Game.STATE.menu;
			
		    }
		}
		else if(Game.gameState==Game.STATE.end)
		{
			
		//try again
		if(mouseOver(mx,my,320,500,155,40))
		{
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			handler.reset();
			Game.gameState=Game.STATE.game;
			handler.loadLevel(tex.level_images[0]);
			handler.reset();
		}
		}
		
		//back from help
		else if(Game.gameState == Game.STATE.help)
		{
			if(mouseOver(mx,my,305,490,150,40))
		    {
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			Game.gameState=Game.STATE.options;
			
		    }
		}
		
		
		else if(Game.gameState == Game.STATE.volume)
		{
			//back from volume
			if(mouseOver(mx,my,305,490,150,40))
		    {
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			Game.gameState=Game.STATE.options;
			
		    }
			
			//music volume minus
			if(mouseOver(mx,my,250, 80, 20, 20))
		    {
				MUSIC_VOL -= 0.1f;
				setMusicVol(MUSIC_VOL);
		    }
			
			//music volume add
			if(mouseOver(mx,my,650, 80, 20, 20))
		    {
				
				MUSIC_VOL += 0.1f;
				setMusicVol(MUSIC_VOL);
		    }
			
			//Game vol minus
			if(mouseOver(mx,my,250, 180, 20, 20))
		    {
				GAME_VOL-=10;
				setGameVol(GAME_VOL);
		    }
			//GAME_VOL volume add
			if(mouseOver(mx,my,650, 180, 20, 20))
		    {
				GAME_VOL+=10;
				setGameVol(GAME_VOL);
		    }
			
		}
		
		//back from credits
		else if(Game.gameState == Game.STATE.credits)
		{
			if(mouseOver(mx,my,305,490,150,40))
		    {
			AudioPlayer.getSound("menuSound").play(1f,(float)getGameVol()/200);
			Game.gameState=Game.STATE.options;
			
		    }
		}
		

	}
	public void tick()
	{
		if(Game.gameState == Game.STATE.end)
		{
			handler.reset();
		}
		
	}
	public void render(Graphics g)
	{
		if(Game.gameState == Game.STATE.menu)
		{
			
		g.drawImage(tex.intro, 0, 0, null);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Algerian",Font.BOLD,40));
		g.drawString("ELITE ",20,75);
		g.drawString(" SOLDIER",90,120);
		
		g.setFont(new Font("Algerian",Font.BOLD,35));
		g.drawRect(40,155,200,50);
		g.drawString("PLAY",85, 189);
		
		g.drawRect(90,220,200,50);
		g.drawString("OPTIONS",120, 255);
		
		g.drawRect(140,285,200,50);
		g.drawString("QUIT",195, 320);
		
		}
		else if(Game.gameState==Game.STATE.options)
		{
			g.drawImage(tex.sub_menu, 0, 0, null);
			
			g.setColor(Color.red);
			g.setFont(new Font("Algerian",Font.BOLD,40));
			g.drawString("OPTIONS",310,75);
			g.setFont(new Font("TimesNewRoman",Font.ITALIC,20));
			
			g.drawRect(280,150,200,30);
			g.drawString("help", 360, 170);
			
			g.drawRect(280,250,200,30);
			g.drawString("volume", 345, 270);
			
			g.drawRect(280,350,200,30);
			g.drawString("credits", 345, 370);

			g.setFont(new Font("Algerian",Font.PLAIN,30));
			g.drawRect(300,490,150,40);
			g.drawString("BACK",340, 520);
		}
		else if(Game.gameState==Game.STATE.end)
		{
			g.drawImage(tex.end, 0, 0, null);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Algerian",Font.BOLD,40));
			g.drawString("GAME OVER",280,75);
			g.setFont(new Font("TimesNewRoman",Font.ITALIC,20));
			g.drawString("YOU LOST !!!",330,175);
			g.drawString("Score : "+hud.getPoints(),335,220);
			
			g.drawRect(320,500,155,40);
			g.drawString("Try Again", 350, 527);
		
		}
		else if(Game.gameState == Game.STATE.help)
		{
			g.drawImage(tex.sub_menu, 0, 0, null);
			g.setColor(Color.BLACK);
			g.drawString("-> Use arrow keys to move player",80,120);
			
			g.setFont(new Font("Algerian",Font.PLAIN,30));
			g.drawRect(300,490,150,40);
			g.drawString("BACK",340, 520);
			
		}
		else if(Game.gameState == Game.STATE.volume)
		{
			g.drawImage(tex.sub_menu, 0, 0, null);
			g.setColor(Color.red);
			g.setFont(new Font("Algerian",Font.PLAIN,30));
			
			g.drawString("Music ", 100, 100);	
			g.drawString("- ", 255, 100);
			g.drawRect(250, 80, 20, 20);
			g.drawString("+ ", 652, 100);
			g.drawRect(650, 80, 20, 20);
			
			g.drawString("Game ", 100, 200);
			g.drawString("- ", 255, 200);
			g.drawRect(250, 180, 20, 20);
			g.drawString("+ ", 652, 200);
			g.drawRect(650, 180, 20, 20);
			
			g.fillRect(300, 80,(int) Game.clamp((MUSIC_VOL*2)*100, 0, 200), 20);
			g.fillRect(300, 180, (int) Game.clamp(GAME_VOL*2,0,200),20);
			
	//		System.out.println(Game.clamp(MUSIC_VOL*2, 0, 200));
	//		System.out.println(Game.clamp(GAME_VOL*2,0,200));
			
			g.drawRect(300,490,150,40);
			g.drawString("BACK",340, 520);
		}
		else if(Game.gameState == Game.STATE.credits)
		{
			g.drawImage(tex.sub_menu, 0, 0, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Algerian",Font.PLAIN,30));
			g.drawRect(300,490,150,40);
			g.drawString("BACK",340, 520);
		}
		
	}
	public boolean mouseOver(int mx,int my,int x,int y,int width,int height)
	{
		if(mx>x&&mx<x+width)
		{
			if(my>y&&my<y+height)
			{
				return true;
			}
			else return false;
		}
		else return false;
		
	}
	public static float getMusicVol() 
	{
		return MUSIC_VOL;
	}
	public static void setMusicVol(float mUSIC_VOL) {
		MUSIC_VOL = mUSIC_VOL;
	}
	public static int getGameVol() {
		return GAME_VOL;
	}
	public static void setGameVol(int gAME_VOL) {
		GAME_VOL = gAME_VOL;
	}
	

}
