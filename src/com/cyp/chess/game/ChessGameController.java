package com.cyp.chess.game;

import com.cyp.accounts.Account;
import com.cyp.application.Application;
import com.cyp.application.Logger;
import com.cyp.game.IChallenge;
import com.cyp.game.IGame;
import com.cyp.game.IGameCommand;
import com.cyp.game.IGameListener;
import com.cyp.game.impl.GameController;
import com.cyp.transport.Connection;
import com.cyp.transport.Message;

public class ChessGameController extends GameController {

	private static final Logger log = Application.getContext().getLogger();

	public ChessGameController(Account account) {
		super(account);
	}
	
	@Override
	public boolean messageReceived(Connection source, Message message) {
		boolean isConsumed = super.messageReceived(source, message);

		if (!isConsumed) {
			String commandId = message
					.getHeader(IGameCommand.GAME_COMMAND_HEADER_KEY);

			if (commandId != null) {

				log.debug(this.getClass().getName(),
						"New chess game command recevied :" + commandId);

				String gameId = message
						.getHeader(ChessGameCommand.GAME_ID_HEADER_KEY);

				if (gameId != null) {

					ChessGame game = findGame(message.getFrom(),
							Long.parseLong(gameId));

					if (game != null) {

						switch (Integer.parseInt(commandId)) {
						case ChessGameCommand.READY_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener).readyReceived();
							}
							return true;
						case ChessGameCommand.MOVE_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.moveReceived(message.getHeader(ChessGameCommand.HEADER_MOVE_VALUE_KEY));
							}
							return true;
						case ChessGameCommand.CHAT_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.chatReceived(message.getBody());
							}
							return true;
						case ChessGameCommand.DRAW_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.drawRequestReceived();
							}
							return true;
						case ChessGameCommand.DRAW_ACCEPTED_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.drawAcceptedReceived();
							}
							return true;
						case ChessGameCommand.RESIGN_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener).resignReceived();
							}
							return true;
						case ChessGameCommand.ABORT_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.abortRequestReceived();
							}
							return true;
						case ChessGameCommand.ABORT_ACCEPTED_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.abortAcceptedReceived();
							}
							return true;
						case ChessGameCommand.REMATCH_COMMAND_ID:
							for (IGameListener listener : game
									.getGameListeners()) {
								((ChessGameListener) listener)
										.rematchRequestReceived();
							}
							return true;						
						default:
							break;
						}
					} else {
						log.debug(this.getClass().getName(), "No game for id :"
								+ gameId);
					}

				} else {
					log.debug(this.getClass().getName(), "Game id is missing!");
				}
			} else {
				log.debug(this.getClass().getName(),
						"Unknown chess game command:" + message.toString());
			}
		}

		return false;
	}
	
	@Override
	public ChessGame startGame(IChallenge challenge) {
		ChessGame cg = new ChessGame(this.getAccount(), challenge);
		
		if( challenge.isReceived() ){
			cg.setWhitePlayer(challenge.getLocalId());
			cg.setBlackPlayer(challenge.getRemoteId());
		}
		else{
			cg.setWhitePlayer(challenge.getRemoteId());
			cg.setBlackPlayer(challenge.getLocalId());
		}
		
		this.listGames().add(cg);
		return cg;
	}
	
	private ChessGame findGame(String fromId, long time) {
		for ( IGame game : this.listGames() ) {
			if (game.getChallenge().getRemoteId().equals(fromId)
					&& game.getChallenge().getTime() == time) {
				return (ChessGame)game;
			}
		}

		return null;
	}
}
