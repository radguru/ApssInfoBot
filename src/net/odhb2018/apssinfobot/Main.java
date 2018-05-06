package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
	public static void main (String[] arg0) {
		ApiContextInitializer.init();
		TelegramBotsApi botsApi= new TelegramBotsApi();
		try {
			botsApi.registerBot(new ApssInfoBot());
		} catch (TelegramApiRequestException e) {
			System.out.println("Wait there are some problem with Telegram");
			e.printStackTrace();
		}
	}

}
