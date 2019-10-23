package com.iridium.autum;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class AudioPlayer 
{
	
	public static Map<String,Sound> soundMap=new HashMap<String,Sound>();
	public static Map<String,Music> musicMap=new HashMap<String,Music>();
	
	public static void load()
	{
		try {
			soundMap.put("menuSound",new Sound("res/buttonsound.ogg"));
			soundMap.put("machinegun",new Sound("res/machinegun.ogg"));
			soundMap.put("koeing",new Sound("res/mike_koeing.ogg"));
			musicMap.put("music",new Music("res/Palpitations.ogg"));
			
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public static Sound getSound(String key)
	{
		return soundMap.get(key);
	}
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
	}

}
