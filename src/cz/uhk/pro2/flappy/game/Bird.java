package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;


public class Bird implements TickAware{
	//fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 0.5;
	static final int ticksFlyingUp = 4;
	
	//souradnice stredu ptaka
	int viewportX;
	double viewportY;
	private Image image;
	
	//rychlost padani (pozitivni) nebo vzletu (negativni)
	double velocityY = koefDown;
	//kolik ticku jeste zbyva nez ptak zacne padat po nakopnuti
	int ticksToFall = 0;
	
	public Bird(int initialX, int initialY, Image image) {
		viewportX = initialX;
		viewportY = initialY;
		this.image = image;
	}
	
	public void kick(){
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}
	
	public void draw(Graphics g){
		//g.setColor(Color.GREEN);
		//g.fillOval(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		//debug, souradnice ptaka
		//g.setColor(Color.BLACK);
		//g.drawString(viewportX+", "+viewportY, viewportX, (int)viewportY);
		g.drawImage(image, viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, null);
	}	

	public boolean collidesWithRectangle(int x, int y, int w, int h) {
		Ellipse2D.Float birdsBoundary = new Ellipse2D.Float(
				viewportX-Tile.SIZE/2,
				(int)viewportY-Tile.SIZE/2,
				Tile.SIZE,
				Tile.SIZE);
		//overime zda kruznice ma neprazdny prunik s ctvercem
		return birdsBoundary.intersects(x,y,w,h);
	}

	@Override
	public void tick(long ticksSinceStart) {
		if (ticksToFall > 0){
			//ptak jeste leti nahoru, "cekame"
			ticksToFall--;
		} else {
			velocityY = koefDown;
		}
		viewportY = viewportY + velocityY;
	}

}
