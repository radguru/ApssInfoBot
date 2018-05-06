package net.odhb2018.apssinfobot.handlers;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public class CommandsHandler {
	private String command;
	private long idchat;
	public CommandsHandler(String s, long l) {
		command = s;
		idchat = l;
	}
	
	public SendMessage Commands() {
		SendMessage message = new SendMessage()
				.enableHtml(true)
				.setChatId(idchat);
		StringBuilder text = new StringBuilder("Errore comando non riconosciuto digitare /help per aiuto");
		switch(command) {
		case "/help" :
			text = new StringBuilder("\tHelp\n");
			text.append("Qui troverai tutte le info sul bot e sui suoi comandi");
			break;
		case "/start":
			text = new StringBuilder("<b>\t\tApssInfoBot</b>\n");
			text.append("Il bot per qualsiasi domanda ti da il benvenuto\n");
			text.append("Fai una domanda o segui i pulsanti qui sotto \u2B07");
			break;
		}
		message.setText(text.toString());
		return message;
	}
	
	

}
