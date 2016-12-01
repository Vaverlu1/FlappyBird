package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

/* Reprezentuje herní objekt umístìný do matice herní plochy*/

public interface Tile {
	
	/*ŠÍØKA A VÝŠKA DLAŽDICE V PIXELECH*/
	static final int SIZE = 20;
	
	/*KRESLÍ HERNÍ OBJEKT NA PLATNO G.
	 * PARAM X X-OVÁ SOUØADNICE V PIXELECH NA OBRAZOVCE KAM KAM SE TO VYKRESLÍ
	 * PARAM Y Y-OVÁ SOUØADNICE V PIXELECH NA OBRAZOVCE KAM KAM SE TO VYKRESLÍ
	 * */
	void draw(Graphics g, int x, int y); /*Tato metoda imlementuje objekt*/
}
