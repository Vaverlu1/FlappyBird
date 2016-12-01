package cz.uhk.pro2.flapy.service;

import cz.uhk.pro2.flappy.game.GameBoard;

/**
 * Spoleèné rzhraní pro tridy umoznujici nacitat level.
 * @author vaverlu1
 *
 */
public interface GameBoardLoader {
	GameBoard loadLevel();
}
