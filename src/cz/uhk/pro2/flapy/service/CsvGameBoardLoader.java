package cz.uhk.pro2.flapy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
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
			//Forcyklus pro preskoceni radku s typy dlazdic
			for (int i = 0; i < typeCount; i++){
				br.readLine();
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
					if(!"".equals(cell)){
						tiles[i][j] = new WallTile();
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pøi ètìní souboru", e);
		}
	}
}
