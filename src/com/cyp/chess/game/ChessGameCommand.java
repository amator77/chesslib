package com.cyp.chess.game;

import com.cyp.game.IGame;
import com.cyp.game.impl.GenericSendGameCommand;

public class ChessGameCommand extends GenericSendGameCommand {
	
	public static final String HEADER_MOVE_VALUE_KEY = "mv";
	
	public static final int MOVE_COMMAND_ID = 8;	
		
	public static final int DRAW_COMMAND_ID = 9;
	
	public static final int DRAW_ACCEPTED_COMMAND_ID = 10;
		
	public static final int RESIGN_COMMAND_ID = 11;
	
	public static final int ABORT_COMMAND_ID = 12;
	
	public static final int ABORT_ACCEPTED_COMMAND_ID = 13;
	
	public static final int REMATCH_COMMAND_ID = 14;
		
	public static final int GAME_CLOSED_COMMAND_ID = 15;
		
	public ChessGameCommand(IGame game, int commandId) {
		super(commandId);
		this.setTo(game.getChallenge().getRemoteContact().getId());
		this.setHeader(ChessGameCommand.GAME_ID_HEADER_KEY,String.valueOf(game.getChallenge().getTime()));
		this.setHeader(ChessGameCommand.GAME_COMMAND_HEADER_KEY,String.valueOf(commandId));				
	}
}
