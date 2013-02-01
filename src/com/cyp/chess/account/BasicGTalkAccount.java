package com.cyp.chess.account;

import java.io.IOException;

import com.cyp.accounts.Account;
import com.cyp.application.Application;
import com.cyp.chess.game.ChessGameController;
import com.cyp.transport.Connection;
import com.cyp.transport.Roster;
import com.cyp.transport.exceptions.LoginException;
import com.cyp.transport.xmpp.google.XMPPMD5Connection;

public class BasicGTalkAccount implements Account {

	private String id;

	private String credentials;

	private XMPPMD5Connection connection;

	private STATUS status;

	private LoginCallback loginCallback;
	
	private ChessGameController chessCtrl;
	
	public BasicGTalkAccount(String id, String credentials) {
		this.id = id;
		this.credentials = credentials;
		this.connection = new XMPPMD5Connection();
		this.status = STATUS.OFFLINE;
		this.chessCtrl = new ChessGameController(this);
	}

	public String getId() {
		return this.id;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public Roster getRoster() {
		if (this.connection.isConnected()) {
			return this.connection.getRoster();
		} else {
			return null;
		}
	}

	public TYPE getType() {
		return TYPE.XMPP_GOOGLE;
	}

	public STATUS getStatus() {
		return this.status;
	}

	public void login(LoginCallback callback) {
		
		if (!this.connection.isConnected() && this.loginCallback == null) {
			
			this.loginCallback = callback;
			
			try {
				connection.login(id, credentials);
				
				if( callback != null ){
					callback.onLogginSuccess();
				}
				
			} catch (IOException e) {
				Application.getContext().getLogger().error("GoogleChessAccount", "Connection error!", e);
				
				if( callback != null ){
					callback.onLogginError("Connection error!");
				}
				
			} catch (LoginException e) {
				Application.getContext().getLogger().error("GoogleChessAccount", "Invalid username or password!", e);
				
				if( callback != null ){
					callback.onLogginError("Invalid username or password!");
				}
			}
		}
	}

	public void logout() {
		if (this.connection.isConnected()) {
			this.connection.logout();
		}
	}	

	public ChessGameController getGameController() {
		return this.chessCtrl;
	}

	public String getIconTypeResource() {
		return null;
	}
	
	public String toString() {
		return "GoogleAccount [id=" + id + ", credentials=" + credentials
				+ ", connection=" + connection + ", status=" + status
				+ ", loginCallback=" + loginCallback + "]";
	}
}
