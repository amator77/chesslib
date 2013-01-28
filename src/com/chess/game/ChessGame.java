package com.chess.game;

import com.gamelib.accounts.Account;
import com.gamelib.game.IChallenge;
import com.gamelib.game.impl.GenericGame;


public class ChessGame extends GenericGame {

	public ChessGame(Account account,IChallenge challenge){
		super(account,challenge);
	}
}
