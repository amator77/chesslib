package com.cyp.chess.game;

import com.cyp.game.IGameListener;

public interface ChessGameListener extends IGameListener {
	
	public void readyReceived();
	
	public void resignReceived();
	
	public void chatReceived(String text);
	
	public void moveReceived(String move);
	
	public void drawRequestReceived();
	
	public void drawAcceptedReceived();
	
	public void abortRequestReceived();
	
	public void abortAcceptedReceived();
	
	public void rematchRequestReceived();
			
	public void gameClosedReceived();
}
