package com.cyp.chess.game;

import java.io.IOException;

import com.cyp.accounts.Account;
import com.cyp.chess.model.Move;
import com.cyp.game.IChallenge;
import com.cyp.game.impl.GenericGame;

public class ChessGame extends GenericGame {

	private ChessGameController ctrl;

	private String whitePlayer;

	private String blackPlayer;

	public ChessGame(Account account, IChallenge challenge) {
		super(account, challenge);
		this.ctrl = (ChessGameController) account.getGameController();
	}

	public void sendMove(Move move) throws IOException {
		ChessGameCommand moveCmd = new ChessGameCommand(
				ChessGameCommand.MOVE_COMMAND_ID);
		moveCmd.setTo(this.getChallenge().getRemoteId());
		moveCmd.setHeader(ChessGameCommand.GAME_ID_HEADER_KEY,
				String.valueOf(this.getChallenge().getTime()));		
		moveCmd.setHeader(ChessGameCommand.HEADER_MOVE_VALUE_KEY,
				move.toString());
		this.ctrl.sendGameCommand(moveCmd);
	}

	public String getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(String whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public String getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(String blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public void sendReady() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.READY_COMMAND_ID));
	}

	public void sendDrawRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.DRAW_COMMAND_ID));
	}

	public void sendAbortRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.ABORT_COMMAND_ID));
	}

	public void resign() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.RESIGN_COMMAND_ID));
	}

	public void acceptDrawRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.DRAW_ACCEPTED_COMMAND_ID));
	}

	public void acceptAbortRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.ABORT_ACCEPTED_COMMAND_ID));
	}

	public void sendRematchRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.REMATCH_COMMAND_ID));
	}

	public void acceptRematchRequest() throws IOException {
		this.ctrl.sendGameCommand(new ChessGameCommand(
				ChessGameCommand.REMATCH_ACCEPTED_COMMAND_ID));
	}
}
