package net.odhb2018.apssinfobot;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;

import net.odhb2018.apssinfobot.function.CallBackFunction;

public class CallBackApss {
	
	InlineQuery query = new InlineQuery();
	
	public static EditMessageText CallBackOne(Update update) {
		String call_data = update.getCallbackQuery().getData();
		System.out.println(call_data);
		System.out.println(update.getCallbackQuery().getData());
		long message_id = update.getCallbackQuery().getMessage().getMessageId();
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		int utente = update.getCallbackQuery().getFrom().getId();
		EditMessageText done = new EditMessageText();
		
		switch(call_data) {
		case "categorie":
			done = CallBackFunction.categories(chat_id, message_id);
			break;
		case "questions":
			done = CallBackFunction.questions(chat_id, message_id, utente);
			break;
		case "blod":
			//done=
			break;
		case "organ":
			break;
		case "marrow":
			break;
		case "woven":
			break;
		case "umbilical":
			break;
		default: 
			System.out.println("Niente da fare, i pulsanti sono settati?");
			break;
			
		}
		return done;
	}
	

}
