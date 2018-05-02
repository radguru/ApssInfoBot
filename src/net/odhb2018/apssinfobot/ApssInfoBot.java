package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import net.odhb2018.apssinfobot.lib.AbilityCommand;

public class ApssInfoBot extends TelegramLongPollingBot{
	
	/*Initialize of the token
	 * BOT_TOKEN -> Token from BotFather of the Bot owned
	 * BOT_USERNAME -> Username of this BOT
	 * API_AI_KEY -> Token from Api.ai(DialogFlow) of the project
	 */	
	public static String BOT_TOKEN="507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	public static String BOT_USERNAME="ApssInfoBot";
	public static String API_AI_KEY="9af2fbbcd5b8483f9c01725b8a0ddfc8";
	public static String CREATOR_ID="137084354";

	@Override
	public String getBotUsername() {
		return BOT_USERNAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage()) {
			if(update.getMessage().isCommand()) {
				if(update.getMessage().getText().equals("/start")) {
					SendMessage mes = new SendMessage()
							.setChatId(update.getMessage().getChatId())
							.setText("Benvenuto, stai scrivendo con ApssInfoBot");
					try {
						execute(mes);
					}catch(TelegramApiException e) {
						e.printStackTrace();
						e.getMessage();
					}
				}
			}
			else if (update.getMessage().hasText()) {
		        SendMessage message = AbilityCommand.faqrisposta(update.getMessage().getChatId(), API_AI_KEY, update.getMessage().getText());
		        try {
		            execute(message);
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
	
}