package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

/* Reprezentuje hern� objekt um�st�n� do matice hern� plochy*/

public interface Tile {
	
	/*���KA A V݊KA DLA�DICE V PIXELECH*/
	static final int SIZE = 20;
	
	/*KRESL� HERN� OBJEKT NA PLATNO G.
	 * PARAM X X-OV� SOU�ADNICE V PIXELECH NA OBRAZOVCE KAM KAM SE TO VYKRESL�
	 * PARAM Y Y-OV� SOU�ADNICE V PIXELECH NA OBRAZOVCE KAM KAM SE TO VYKRESL�
	 * */
	void draw(Graphics g, int x, int y); /*Tato metoda imlementuje objekt*/
}
