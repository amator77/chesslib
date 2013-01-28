package com.chess.game;

import com.gamelib.game.IChallenge;
import com.gamelib.game.impl.GenericGame;


public class ChessGame extends GenericGame {

	public ChessGame(ChessGameController ctrl,IChallenge challenge){
		super(ctrl,challenge);
	}
}