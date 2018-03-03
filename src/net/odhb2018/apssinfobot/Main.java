package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

//just for starting the real bot
public class Main {
	
	public static void main (String[] arg0) {
		
		//Starting the Telegram API
		ApiContextInitializer.init();
		//Initialize Bot
		TelegramBotsApi botsApi= new TelegramBotsApi();
		
		//Enrolment of the Bot
		try {
			botsApi.registerBot(new ApssInfoBot());
		} catch (TelegramApiRequestException e) {
			System.out.println("Wait there are some problem with Telegram");
			e.printStackTrace();
		}
	}

}
