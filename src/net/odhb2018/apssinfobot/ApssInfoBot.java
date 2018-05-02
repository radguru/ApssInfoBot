package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//Usage of AbilityBot from TelegramBot API of rubenlagus
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
		// TODO Auto-generated method stub
		return BOT_USERNAME;
	}


	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return BOT_TOKEN;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		// We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(update.getMessage().getChatId())
	                .setText(update.getMessage().getText());
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
