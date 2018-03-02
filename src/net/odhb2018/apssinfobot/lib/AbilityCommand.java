package net.odhb2018.apssinfobot.lib;

import java.util.ArrayList;
import java.util.List;

import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class AbilityCommand   {
	
	public static SendMessage start(long chat_Id) {
		//messaggio di apertura
		SendMessage message = new SendMessage()
				.setChatId(chat_Id)
				.setText("Benvenuto nel servizio APSS BOT");
		
		//tastiera opzioni
		ReplyKeyboardMarkup rkM =new ReplyKeyboardMarkup();
		//lista delle righe
		List<KeyboardRow> keyboard =new ArrayList<>();
		//riga della tastiera
		KeyboardRow row = new KeyboardRow();
		//keyboard button
		KeyboardButton ask = new KeyboardButton();
		ask.setText("Domanda libera?");
		
		//aggiungo i parametri
		row.add(ask);
		row.add("Categorie");
		keyboard.add(row);
		rkM.setKeyboard(keyboard);
		message.setReplyMarkup(rkM);
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
		
		AIConfiguration conf = new AIConfiguration(key);
		
		AIDataService data = new AIDataService(conf);
		
		AIRequest messaggio = new AIRequest(mess);
		
		AIResponse risposta;
		
		try {
			risposta = data.request(messaggio);
			
			if(risposta.getStatus().getCode()==200) {
				return risposta.getResult().getFulfillment().getSpeech();
			}else {
				return a;
			}
		} catch (AIServiceException e) {
			return a+e.getMessage();
		}
		
	}
	

}
