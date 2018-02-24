package first;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
	
	public static void main (String[] arg0) {
		
		//Inizializzo API
		ApiContextInitializer.init();
		//inizializzo le API di telegram
		TelegramBotsApi botsApi= new TelegramBotsApi();
		
		//"registro" faccio partire il bot
		try {
			botsApi.registerBot(new ApssInfoBot());
		} catch (TelegramApiRequestException e) {
			// TODO se il bot non viene "registrato" 
			e.printStackTrace();
		}
	}

}
