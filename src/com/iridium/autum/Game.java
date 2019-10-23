package com.iridium.autum;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 5562914334401036325L;
	
	private Thread thread;
	private boolean running = false;
	public static int WIDTH,HEIGHT;
	
	private Handler handler;
	private Camera cam;
	private static Textures tex;
	private HUD hud;
	
	private Menu menu;
	public static boolean paused = false;
	
	public static int guntype = 2;
	
	private Graphics g;
	
	public enum STATE 
	{
		game,menu,end,options,help,volume,credits
	}
	public enum GUNMODE
	{
		pistol,machinegun
	}
	public static STATE gameState = STATE.menu;
	public static GUNMODE gunMode = GUNMODE.machinegun;
	
	private void init()
	{
		AudioPlayer.load();
		WIDTH = getWidth();
		HEIGHT = getHeight();
		cam = new Camera(0,0);
		hud = new HUD();
		tex = new Textures();
		handler = new Handler(tex,hud,cam,this);
		menu= new Menu(handler,hud,tex);
		
		addKeyListener(new KeyInput(handler));
		addMouseListener(menu);
		
		float music_vol = Menu.getMusicVol();
		// Game.clamp((float)Menu.getMusicVol()/200, 0, 200);
		
		AudioPlayer.getMusic("music").loop(1f,music_vol);
			
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop()
	{
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void tick()
	{
		System.out.println((float)Menu.MUSIC_VOL/200);
		
		if(HUD.LIFE <= 0)
		{
		     gameState = STATE.end;
		}
		
		if( gameState == STATE.volume)
		{
			menu.tick();
		}
		
		if(gameState == STATE.game)
		{
		    if(!Game.paused)
		    {
		        handler.tick();
		        hud.tick();
	          	for(int i = 0;i<handler.object.size();i++)
		        {
			        if(handler.object.get(i).getID() == ID.Player)
			          {
				        cam.tick(handler.object.get(i));
			          }
	         	}
		   }
		}
		
		else if(gameState == STATE.end)
		{
			menu.tick();
		}
		
	}
	public void render()
	{
		BufferStrategy bs= this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
	    g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
	//	g.setColor(new Color(25,191,224));
	//	g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		
		EventQueue.invokeLater(new Runnable(){
			public void run()
			{
				g.drawImage(new BufferedImageLoader().loadImage("/forest.png"),0,0,null);
			}
		});
		 
		
		if(Game.gameState == STATE.game)
		{
			
		
		g2d.translate(cam.getX(), cam.getY());
//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		handler.render(g);
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////
		g2d.translate( -cam.getX(), -cam.getY());
		
		hud.render(g);
		}
		else if(gameState == STATE.menu || gameState == STATE.options 
				|| gameState == STATE.end || gameState == STATE.help || gameState == STATE.volume|| gameState == STATE.credits)
		{
			menu.render(g);
		
		}
	
		g.dispose();
		bs.show();
		
	}
	public void run()
	{
		
		init();
		handler.nextLevel();
		this.requestFocus();
		
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / 65;
		final double timeF = 1000000000 / 65;
		double deltaU = 0, deltaF = 0;
		long timer = System.currentTimeMillis();
		
		    while (running) {
		
		        long currentTime = System.nanoTime();
		        deltaU += (currentTime - initialTime) / timeU;
		        deltaF += (currentTime - initialTime) / timeF;
		        initialTime = currentTime;
		
		        if (deltaU >= 1) {
		            tick();
		            
		            deltaU--;
		        }
		
		        if (deltaF >= 1) {
		            render();
		            
		            deltaF--;
		        }
		
		        if (System.currentTimeMillis() - timer > 1000) {
		            timer += 1000;
		        }
		    }
		    
		    stop();
}
		 
	public static float clamp(float var,float min,float max)
	{
		if(var<min)
		{
			return min;
		}
		else if(var>max)
		{
			return max;
		}
		else
			return var;
		
	}
	
	public static Textures getInstance()
	{
		return tex;
	}
	
	public static void main(String args[])
	{
		new Window(800,600,"EliteSoldier",new Game());
	}

}
