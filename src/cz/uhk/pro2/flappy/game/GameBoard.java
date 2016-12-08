package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

public class GameBoard implements TickAware{
	Tile[][] tiles;
	int shiftX = 30;
	int viewportWidth = 200; //TO DO
	Bird bird;
	
	public GameBoard() {
		tiles = new Tile[20][20]; //TO DO
		//tiles[2][1] = new WallTile();
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2);
	}
	
	public GameBoard(Tile[][] tiles){
		this.tiles = tiles;
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2);
	}
	
	/*Kreslí celý herní svìt (zdi, bonusy, ptáka) na plátno g.*/
	public void draw(Graphics g){
		int minJ = shiftX/Tile.SIZE;	//SPOÈÍTÁME PRVNÍ J INDEX BUNKY, KTERY MA SMYSL KRESLIT,JE VIDET VE VIEWPORTU, TJ. NA OBRAZOVCE
		int maxJ = minJ + viewportWidth/Tile.SIZE + 2;//+2 protože celeciselnì delime jak shiftx tak viewsize,ale chceme zaokrouhlit nahoru
		for(int i=0; i< tiles.length; i++){
			for(int j= minJ; j<= maxJ; j++){
				//chceme aby se svìt nìjakým zpùsobem toèil dokola
				//j2 je pohybuje od 0..pocet sloupcu -1
				int j2 = j % tiles[0].length;
				Tile t = tiles[i][j2];
				if (t != null){						//je na souradnicich i j dlazdice?
					int screenX = j*Tile.SIZE - shiftX;
					int screenY = i*Tile.SIZE;
					t.draw(g, screenX, screenY);
				}
			}
		}
		//ptak
		bird.draw(g);
		
	}

	@Override
	public void tick(long ticksSinceStart) {
		//s kazdym tickem ve hre posuneme hru o 1 px - pocet ticku a pixelu posunu se rovnaji
		shiftX = (int)ticksSinceStart;
		
		//TODO dame vedet jeste ptakovi ze hodiny tickly
		bird.tick(ticksSinceStart);
	}
	
	public int getHeightPix(){
		return tiles.length*Tile.SIZE;
	}
	
	public void kickTheBird(){
		bird.kick();
	}
	
}
