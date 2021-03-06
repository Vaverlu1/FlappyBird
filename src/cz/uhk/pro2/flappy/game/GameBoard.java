package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cz.uhk.pro2.flappy.game.tiles.BonusTile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class GameBoard implements TickAware{
	Tile[][] tiles;
	int shiftX = 30;
	int viewportWidth = 200; //TO DO
	Bird bird;
	boolean gameOver = false;
	
		
	public GameBoard(Tile[][] tiles, BufferedImage imageOfTheBird){
		this.tiles = tiles;
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2, imageOfTheBird);
	}
	
	/*Kresl� cel� hern� sv�t (zdi, bonusy, pt�ka) na pl�tno g.*/
	public void drawAndTestCollisions(Graphics g){
		int minJ = shiftX/Tile.SIZE;	//SPO��T�ME PRVN� J INDEX BUNKY, KTERY MA SMYSL KRESLIT,JE VIDET VE VIEWPORTU, TJ. NA OBRAZOVCE
		int maxJ = minJ + viewportWidth/Tile.SIZE + 2;//+2 proto�e celeciseln� delime jak shiftx tak viewsize,ale chceme zaokrouhlit nahoru
		for(int i=0; i< tiles.length; i++){
			for(int j= minJ; j<= maxJ; j++){
				//chceme aby se sv�t n�jak�m zp�sobem to�il dokola
				//j2 je pohybuje od 0..pocet sloupcu -1
				int j2 = j % tiles[0].length;
				Tile t = tiles[i][j2];
				if (t != null){						//je na souradnicich i j dlazdice?
					int screenX = j*Tile.SIZE - shiftX;
					int screenY = i*Tile.SIZE;
					//nakresl�me dla�dici
					t.draw(g, screenX, screenY);
					//otestujeme moznou kolizi s pt�kem
					if (t instanceof WallTile){
						//dlazdice je typu ze�
						if (bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)){
							//Do�lo ke kolizi ptaka s dlazdici
							System.out.println("Kolize");
							//gameOver = true;
							
						}
					} else if (t instanceof BonusTile){
						if (bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)){
							//Do�lo ke kolizi ptaka s dlazdici
							System.out.println("Sebral Bonus");
							((BonusTile)t).setActive(false, screenX);
						}
					}
				}
			}
		}
		//ptak
		bird.draw(g);
		
	}

	@Override
	public void tick(long ticksSinceStart) {
		if (!gameOver){
			//s kazdym tickem ve hre posuneme hru o 1 px - pocet ticku a pixelu posunu se rovnaji
			shiftX = (int)ticksSinceStart;
			
			//TODO dame vedet jeste ptakovi ze hodiny tickly
			bird.tick(ticksSinceStart);
		}
	}
	
	public int getHeightPix(){
		return tiles.length*Tile.SIZE;
	}
	
	public void kickTheBird(){
		bird.kick();
	}
	
}
