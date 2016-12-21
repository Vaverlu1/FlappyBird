package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import cz.uhk.pro2.flappy.game.Tile;

public class BonusTile extends AbstractTile {
	private boolean active = true;
	Tile emptyTile;
	int posTaken;
	
	public BonusTile(BufferedImage image, Tile emptyTile) {
		super(image);
		this.emptyTile = emptyTile;
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		if(!active){
			emptyTile.draw(g, x, y);
		} else {
			super.draw(g, x, y);
		}
		if(!active && x > posTaken){
				super.draw(g, x, y);
				active = false;
		}
	}
	
	public void setActive(boolean active, int x){
		this.active = active;
		posTaken = x;
	}
}
