package net.odhb2018.apssinfobot.lib;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class AbilityCommand   {
	
	public static SendMessage start(long chat_Id, int utente) {
		//Opening message
		SendMessage message = new SendMessage()
				.setChatId(chat_Id)
				.setText(null);
		
		//Returning variable based on the PSQL result
		int a;
		//Verification of the database
		if(PSqlConnection.find(utente)) {
			System.out.println("Utente presente nel record");
			a=4;
		}else {
			a = PSqlConnection.writeDataBase(utente, false);
		}
		switch(a) {
		default:
			InlineKeyboardMarkup inr = new InlineKeyboardMarkup();
			List<InlineKeyboardButton> linr=new ArrayList<>();
			InlineKeyboardButton cat, dom;
			cat= new InlineKeyboardButton();
			dom= new InlineKeyboardButton();
			dom.setText("Categorie");
			dom.setCallbackData("categorie");
			linr.add(dom);
			cat.setText("Domande libere");
			cat.setCallbackData("questions");
			linr.add(cat);
			List<List<InlineKeyboardButton>> das = new ArrayList<>();
			das.add(linr);
			inr.setKeyboard(das);
			message.setText("Benvenuto nel servizio APSS BOT");
			message.setReplyMarkup(inr);
			break;
		case 1:
			message.setText("Ti ricordo che sei nella modalità faq");
			break;
		case 3:
			message.setText("Attenzione problemi con il servizio. Per info chiamare: 848 806 806");
			break;
		}
		
		return message;
		
	}
	
	//se entro nella sezione delle faq
	public static SendMessage faqrisposta(long chat_Id, String key, String mess) {
		SendMessage message = new SendMessage();
		message.setChatId(chat_Id)
		.setText(apii(key,mess));
		return message;
	}
	
	public static String apii(String key, String mess) {
		String a = "Sicuro di essere connesso alla rete?";
		//mess="Perche' donare gli organi?";
		System.out.println(a);
		System.out.println(mess);
		AIConfiguration conf = new AIConfiguration(key);
		
		AIDataService data = new AIDataService(conf);
		
		AIRequest messaggio = new AIRequest();
		messaggio.setLanguage("it");
		messaggio.setQuery(mess);
		
		
		System.out.println(messaggio.toString());
		
		AIResponse risposta;
		
		try {
			System.out.println("cdsa");
			risposta = data.request(messaggio);
			System.out.println("CIAO:"+risposta.getResult().getFulfillment().getSpeech());
			
			if(risposta.getStatus().getCode()==200) {
				System.out.println(risposta.getResult().getFulfillment().getSpeech());
				return risposta.getResult().getFulfillment().getSpeech();
			}else {
				System.out.println("ERRORE\n\n");
				return a;
			}
		} catch (AIServiceException e) {
			System.out.println("ciao");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return a;
		}
		
	}
	

	

}
