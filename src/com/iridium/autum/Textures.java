package com.iridium.autum;

import java.awt.image.BufferedImage;

public class Textures 
{
	
	private SpriteSheet soldier_sprite;
	private SpriteSheet object_sprite;
	private SpriteSheet object_sprite_2;
	private SpriteSheet enemy_sprite;
	private SpriteSheet groundSprite;
	private SpriteSheet game_sprite;
	
	private BufferedImage soldier_sprite_image = null;
	private BufferedImage object_sprite_image = null;
	private BufferedImage object_sprite_image2 = null;
	private BufferedImage enemy_sprite_image = null;
	private BufferedImage ground_sprite_image = null;
	private BufferedImage game_sprite_image = null;
	
	public BufferedImage [] level_images = new BufferedImage[15];
	public BufferedImage [] soldier_image = new BufferedImage[20];
	public BufferedImage [] player_jumping = new BufferedImage[4];
	public BufferedImage [] coin_image =new BufferedImage[6];
	public BufferedImage [] life_image = new BufferedImage[4];
	public BufferedImage [] trophy_image = new BufferedImage[4];
	public BufferedImage [] machine_gun_image =new BufferedImage[3];
	public BufferedImage [] pistol_gun_image =new BufferedImage[3];
	public BufferedImage [] water = new BufferedImage[2];
	public BufferedImage [] enemy = new BufferedImage[4];
	public BufferedImage [] ground_image = new BufferedImage[64];
	public BufferedImage [] game_image = new BufferedImage[20];
	
	public BufferedImage block_image = null;
	public BufferedImage grass_image = null;
	public BufferedImage grass_image_left = null;
	public BufferedImage grass_image_right = null;
	public BufferedImage player_dead = null;
	public BufferedImage clouds = null;
	
	public static BufferedImage hud_AK47 = null;
	public static BufferedImage hud_pistol = null;
	public static BufferedImage hud_life = null;
	
	public BufferedImage intro = null;
	public BufferedImage sub_menu = null;
	public BufferedImage end = null;
	
	
	public Textures()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		level_images[0] = loader.loadImage("/level.png");
		level_images[1] = loader.loadImage("/level2.png");
		level_images[2] = loader.loadImage("/level3.png");
		level_images[3] = loader.loadImage("/level4.png");
		level_images[4] = loader.loadImage("/level5.png");
		level_images[5] = loader.loadImage("/level6.png");
		level_images[6] = loader.loadImage("/level7.png");
		level_images[7] = loader.loadImage("/level8.png");
		level_images[8] = loader.loadImage("/level9.png");
		level_images[9] = loader.loadImage("/level10.png");
		level_images[10] = loader.loadImage("/level11.png");
		level_images[11] = loader.loadImage("/level12.png");
		level_images[12] = loader.loadImage("/level13.png");
		
		intro = loader.loadImage("/elite_intro.png");
		sub_menu = loader.loadImage("/sub_menu.png");
		end = loader.loadImage("/elite_end.png");
		
		soldier_sprite_image = loader.loadImage("/elite_solder.png");
		object_sprite_image = loader.loadImage("/object_sprite_sheet.png");
		object_sprite_image2 = loader.loadImage("/object_ssheet.png");
		player_dead = loader.loadImage("/player_dead.png");
		clouds = loader.loadImage("/clouds.png");
		enemy_sprite_image = loader.loadImage("/enemy_sprite_sheet.png");
		ground_sprite_image = loader.loadImage("/groundTextures.png");
		game_sprite_image = loader.loadImage("/game_textures.png");

		soldier_sprite = new SpriteSheet(soldier_sprite_image);
		object_sprite = new SpriteSheet(object_sprite_image);
		object_sprite_2 = new SpriteSheet(object_sprite_image2);
		enemy_sprite = new SpriteSheet(enemy_sprite_image);
		groundSprite = new SpriteSheet(ground_sprite_image);
		
		game_sprite = new SpriteSheet(game_sprite_image);

		getTextures();
		
	}
	public void getTextures()
	{
		//player walking rightwards with AK47
		soldier_image[0] = soldier_sprite.grabImage(5, 1, 32,64,false);	
		soldier_image[1] = soldier_sprite.grabImage(1, 1, 32,64,false);	
		soldier_image[2] = soldier_sprite.grabImage(2, 1, 32,64,false);	
		soldier_image[3] = soldier_sprite.grabImage(3, 1, 32,64,false);	
		soldier_image[4] = soldier_sprite.grabImage(4, 1, 32,64,false);	
		
		//player walking leftwards
		soldier_image[5] = soldier_sprite.grabImage(12, 1, 32,64,false);	
		soldier_image[6] = soldier_sprite.grabImage(16, 1, 32,64,false);	
		soldier_image[7] = soldier_sprite.grabImage(15, 1, 32,64,false);
		soldier_image[8] = soldier_sprite.grabImage(14, 1, 32,64,false);
		soldier_image[9] = soldier_sprite.grabImage(13, 1, 32,64,false);
		
		//player walking rightwards with pistol
		soldier_image[10] = soldier_sprite.grabImage(5, 2, 32,64,false);	
		soldier_image[11] = soldier_sprite.grabImage(1, 2, 32,64,false);	
		soldier_image[12] = soldier_sprite.grabImage(2, 2, 32,64,false);
		soldier_image[13] = soldier_sprite.grabImage(3, 2, 32,64,false);
		soldier_image[14] = soldier_sprite.grabImage(4, 2, 32,64,false);
				
		//player walking leftwards
		soldier_image[15] = soldier_sprite.grabImage(12, 2, 32,64,false);
		soldier_image[16] = soldier_sprite.grabImage(16, 2, 32,64,false);	
		soldier_image[17] = soldier_sprite.grabImage(15, 2, 32,64,false);	
		soldier_image[18] = soldier_sprite.grabImage(14, 2, 32,64,false);
		soldier_image[19] = soldier_sprite.grabImage(13, 2, 32,64,false);
		
		
		//with pistol
		
		player_jumping[0] = soldier_sprite.grabImage(1,3, 32,70,false);//right
		player_jumping[3] = soldier_sprite.grabImage(16,3, 32,70,false);//left
		
		//with machine gun
		player_jumping[1] = soldier_sprite.grabImage(2,3, 32,70,false);//right
		player_jumping[2] = soldier_sprite.grabImage(15,3, 32,70,false);//left
		
		//blocks
		block_image = object_sprite.grabImage(3, 4, 32, 32);
		grass_image = object_sprite.grabImage(4, 2, 32, 32);
		grass_image_left = object_sprite_2.grabImage(1, 4, 32, 32);
		grass_image_right = object_sprite_2.grabImage(2, 4, 32, 32);
		
		//coins
		coin_image[0] = object_sprite.grabImage(1, 1,32, 32);
		coin_image[1] = object_sprite.grabImage(2, 1,32, 32);
		coin_image[2] = object_sprite.grabImage(3, 1,32, 32);
		coin_image[3] = object_sprite.grabImage(4, 1,32, 32);
		coin_image[4] = object_sprite.grabImage(1, 2,32, 32);
		coin_image[5] = object_sprite.grabImage(2, 2,32, 32);
		
		//life
		life_image[0] = object_sprite.grabImage(1, 3, 32, 32);
		life_image[1] = object_sprite.grabImage(2, 3, 31, 31);
		life_image[2] = object_sprite.grabImage(1, 4, 32, 32);
		life_image[3] = object_sprite.grabImage(2, 4, 30, 30);
		hud_life = object_sprite.grabImage(2, 4, 30, 30);
		
		//ammo
		machine_gun_image[0] = object_sprite_2.grabImage(1, 2, 32, 32);
		machine_gun_image[1] = object_sprite_2.grabImage(3, 2, 32, 32);
		machine_gun_image[2] = object_sprite_2.grabImage(1, 3, 32, 32);
		
		pistol_gun_image[0] = object_sprite_2.grabImage(2, 2, 32, 32);
		pistol_gun_image[1] = object_sprite_2.grabImage(4, 2, 32, 32);
		pistol_gun_image[2] = object_sprite_2.grabImage(1, 3, 32, 32);
		
		hud_AK47 = object_sprite_2.grabImage(1, 2, 32, 32);
		hud_pistol = object_sprite_2.grabImage(2, 2, 32, 32);
		
		//trophy
		trophy_image[0] = object_sprite_2.grabImage(1, 1, 32, 32);
		trophy_image[1] = object_sprite_2.grabImage(2, 1, 32, 32);
		trophy_image[2] = object_sprite_2.grabImage(3, 1, 32, 32);
		trophy_image[3] = object_sprite_2.grabImage(4, 1, 32, 32);
		
		//water
		
		water[0] = object_sprite_2.grabImage(2, 3, 32, 32);
		water[1] = object_sprite_2.grabImage(3, 3, 32, 32);
		
		//enemy
		
		enemy[0] = enemy_sprite.grabImage(1, 1, 32, 64,false);
		enemy[1] = enemy_sprite.grabImage(2, 1, 32, 64,false);
		enemy[2] = enemy_sprite.grabImage(7, 1, 32, 64,false);
		enemy[3] = enemy_sprite.grabImage(8, 1, 32, 64,false);
		
		//ground textures
		ground_image[0] = groundSprite.grabImage(1, 1, 32, 32);
		ground_image[1] = groundSprite.grabImage(2, 1, 32, 32);
		ground_image[2] = groundSprite.grabImage(3, 1, 32, 32);
		ground_image[3] = groundSprite.grabImage(4, 1, 32, 32);
		ground_image[4] = groundSprite.grabImage(5, 1, 32, 32);
		ground_image[5] = groundSprite.grabImage(6, 1, 32, 32);
		ground_image[6] = groundSprite.grabImage(7, 1, 32, 32);
		ground_image[7] = groundSprite.grabImage(8, 1, 32, 32);
		ground_image[8] = groundSprite.grabImage(1, 2, 32, 32);
		ground_image[9] = groundSprite.grabImage(2, 2, 32, 32);
		ground_image[10] = groundSprite.grabImage(3, 2, 32, 32);
		
		/// general game textures
		
		game_image[0] = game_sprite.grabImage(1, 1, 32, 64, false);
	

	}
	public  BufferedImage getLevel_image(int i) {
		return level_images[i];
	}
	
	
}
