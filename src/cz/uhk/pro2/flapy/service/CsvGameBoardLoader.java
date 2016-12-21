package cz.uhk.pro2.flapy.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.tiles.BonusTile;
import cz.uhk.pro2.flappy.game.tiles.EmptyTile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

	
public class CsvGameBoardLoader implements GameBoardLoader{
	InputStream is;
	
	public CsvGameBoardLoader(InputStream is) {
		this.is = is;
	}
	
	public GameBoard loadLevel(){
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			String[] line = br.readLine().split(";");
			int typeCount = Integer.parseInt(line[0]);
			Map<String, Tile> tileTypes = new HashMap<>();
			BufferedImage imageOfTheBird = null;
			//Forcyklus pro preskoceni radku s typy dlazdic
			for (int i = 0; i < typeCount; i++){
				line = br.readLine().split(";");
				String tileType = line[0];
				String clazz = line[1];
				int x = Integer.parseInt(line[2]);
				int y = Integer.parseInt(line[3]);
				int w = Integer.parseInt(line[4]);
				int h = Integer.parseInt(line[5]);
				String url = line[6];
				String referencedTileType = (line.length >= 8) ? line[7] : "";
				Tile referencedTile = tileTypes.get(referencedTileType); 
				if(clazz.equals("Bird")){
					//urèije obrazek  ptaka
					imageOfTheBird = loadImage(x,y,w,h,url);
				} else {
					Tile tile = createTile(clazz,x,y,w,h, url, referencedTile); 
					tileTypes.put(tileType, tile);	
				}
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			//Vytvorim pole dlazdic odpovidajicich rozmeru z csv
			Tile[][] tiles = new Tile[rows][columns];
			System.out.println(rows + "," + columns);
			for(int i = 0; i < rows; i++){
				line = br.readLine().split(";");
				for(int j = 0; j < columns; j++){
					String cell;
					if(j < line.length){
						//bunka v csv existuje, ok
						cell = line[j];
					} else {
						//bunka v csv chybi, povazujeme ji za prazdnou
						cell = "";
					}
						tiles[i][j] = tileTypes.get(cell);
				}
			}
			GameBoard gb = new GameBoard(tiles, imageOfTheBird);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pøi ètìní souboru", e);
		}
	}

	private Tile createTile(String clazz, int x, int y, int w, int h, String url, Tile referencedTile) {
		try{
			BufferedImage resizedImage = loadImage(x, y, w, h, url);
			switch (clazz){
			case "Wall":
				return new WallTile(resizedImage);
			case "Empty":
				return new EmptyTile(resizedImage);
			case "Bonus":
				return new BonusTile(resizedImage, referencedTile);
			}
			throw new RuntimeException("Neznámý typ dlaždice " + clazz);
		} catch (MalformedInputException e){
			throw new RuntimeException("Špatna URL pro obrázek " + clazz + ": " + url, e);
		} catch (IOException e){
			throw new RuntimeException("Chyba pri cteni obrazku ", e);
		}
	}

	private BufferedImage loadImage(int x, int y, int w, int h, String url) throws IOException, MalformedURLException {
		BufferedImage originalImage = ImageIO.read(new URL(url));
		BufferedImage croppedImage = originalImage.getSubimage(x, y, w, h);
		BufferedImage resizedImage = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(croppedImage, 0, 0, Tile.SIZE,Tile.SIZE, null);
		return resizedImage;
	}
}
