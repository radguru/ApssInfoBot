package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import net.odhb2018.apssinfobot.handlers.CommandsHandler;
import net.odhb2018.apssinfobot.handlers.QuestionsHandler;

public class ApssInfoBot extends TelegramLongPollingBot{
	
	public static String BOT_TOKEN="507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE"; //BotFather (Telegram)
	public static String BOT_USERNAME="ApssInfoBot"; //Telegram bot's username
	public static String API_AI_KEY="9af2fbbcd5b8483f9c01725b8a0ddfc8"; //Dialogflow
	public static String CREATOR_ID="137084354"; //Telegram creator id

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
		String uString = update.getMessage().getText();
		long uchatid = update.getMessage().getChatId();
		SendMessage message;
		message = new SendMessage().setChatId(uchatid)
				.setText("Formato del messaggio non supportato, riprovare");
		System.out.println(uString);
		if(update.hasMessage()) {
			if(update.getMessage().isCommand()) {
				CommandsHandler chandler = new CommandsHandler(uString,uchatid);
				message = chandler.Commands();
			}
			else if (update.getMessage().hasText()) {
		        message = QuestionsHandler.faqrisposta(uchatid, API_AI_KEY, uString);
		    }
		}
		
		try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
		
	}
	
}