package cz.uhk.pro2.flapy.service;

import cz.uhk.pro2.flappy.game.GameBoard;

/**
 * Spole�n� rzhran� pro tridy umoznujici nacitat level.
 * @author vaverlu1
 *
 */
public interface GameBoardLoader {
	GameBoard loadLevel();
}
