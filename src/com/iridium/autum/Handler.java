package com.iridium.autum;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler
{
	public LinkedList<GameObject>object=new LinkedList<GameObject>();
	private Textures tex;
	private HUD hud;
	private GameObject temp;
	private Camera cam;
	private BufferedImage clouds;
	private BufferedImage currentLevel = null;
	private Game game;

	public Handler(Textures tex,HUD hud,Camera cam,Game game)
	{
		this.tex = tex;
		this.hud = hud;
		this.cam = cam;
		this.clouds = tex.clouds;
		this.game = game;
		currentLevel = tex.level_images[hud.getLevel()];
		
	}
	public void tick()
	{
		for(int i=0;i<object.size();i++)
		{
		    temp=object.get(i);
			temp.tick(object);
			
		}
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObj =object.get(i);
			if(tempObj.getID() == ID.Player)
			{
				if(HUD.HEALTH_INDEX<= 0)
				{
					removeObject(tempObj);
					reloadPlayer(currentLevel);
				}
			}
		}
		
		
	}
	public void render(Graphics g)
	{
		for(int i=0;i<object.size();i++)
		{
			temp=object.get(i);
			temp.render(g);
			
		}	
		for(int xx = 0;xx< clouds.getWidth()*5;xx+=clouds.getWidth())
		{
			
			g.drawImage(clouds,xx, 50, game);
			
		}
	}
	
	public void addObject(GameObject myObject)
	{
		this.object.add(myObject);
	}
	
	public void removeObject(GameObject myObject)
	{
		this.object.remove(myObject);
	}
	
	public void nextLevel()
	{	
		
		clearLevel();
		loadLevel(tex.getLevel_image(hud.getLevel()-1));
		
	}
	private void clearLevel()
	{
		cam.setX(0);
		object.clear();
	}
	public void reset()
	{
		hud.setLevel(0);
		hud.setPoints(0);
		HUD.HEALTH_INDEX = 100;
		HUD.LIFE = 3;
		HUD.MACH_AMMO = 100;
		HUD.PISTOL_AMMO = 100;
		
	}
	public void loadLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx= 0;xx<w;xx++)
		{
			for(int yy=0;yy<h;yy++)
			{
				int pixel = image.getRGB(xx, yy);
				
				int red = (pixel>>16) & 0xff;
				int green =(pixel>>8) & 0xff;
				int blue = (pixel)& 0xff;
				
				if(red == 255 && green == 255 && blue == 255)
				{
					addObject(new Block(xx*32,yy*32,ID.Block,0));
				}
				if(red == 0 && blue == 255 && green == 0  )
				{
					addObject(new Player(xx*32,yy*32+30,ID.Player,this,hud));
				}
				if(red == 0 && blue == 0 && green == 255)
				{
					addObject(new Block(xx*32,yy*32,ID.Block,1));
				}
				if(red == 150 && blue == 0 && green == 150)
				{
					addObject(new Block(xx*32,yy*32,ID.Block,2));
				}
				if(red == 0 && blue == 150 && green == 150)
				{
					addObject(new Block(xx*32,yy*32,ID.Block,3));
				}
				if(red == 255 && blue == 0 && green == 255)
				{
					addObject(new Coin(xx*32,yy*32,ID.Coin));
				}
				if(red == 255 && blue == 0 && green == 0)
				{
					addObject(new Life(xx*32,yy*32,ID.Life));
				}
				if(red == 160 && blue == 160 && green == 160)
				{
					addObject(new Pistol((int)xx*32,yy*32,ID.Pistol));
				}
				if(red == 127 && blue == 0 && green == 51)
				{
					addObject(new Trophy((int)xx*32,yy*32,ID.Trophy));
				}
				if(red == 0 && blue == 144 && green == 255)
				{
					addObject(new MachineGun((int)xx*32,yy*32,ID.MachineGun));
				}
				if(red == 0 && blue == 255 && green == 255)
				{
					addObject(new Water((int)xx*32,yy*32,ID.Water));
				}
				if(red == 48 && blue == 48 && green == 48)
				{
					addObject(new EnemyGuard((int)xx*32,yy*32,ID.EnemyGuard,this));
				}
				if(red == 0 && blue == 0 && green == 150)
				{
					addObject(new Tree(xx*32,yy*32-30,ID.Tree));
				}
			
			}
			
		}
	}
	public void reloadPlayer(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx= 0;xx<w;xx++)
		{
			for(int yy=0;yy<h;yy++)
			{
				int pixel = image.getRGB(xx, yy);
				
				int red = (pixel>>16) & 0xff;
				int green =(pixel>>8) & 0xff;
				int blue = (pixel)& 0xff;
				
				if(red == 0 && blue == 255 && green == 0  )
				{
					addObject(new Player(xx*32,yy*32,ID.Player,this,hud));
				}
			}
		}
	}
	

}
