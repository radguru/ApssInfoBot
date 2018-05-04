package net.odhb2018.apssinfobot.lib;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class AbilityCommand   {
	
	public static SendMessage faqrisposta(long chat_Id, String key, String mess) {
		SendMessage message = new SendMessage();
		message.setChatId(chat_Id)
		.setText(apii(key,mess));
		return message;
	}
	
	public static String apii(String key, String mess) {
		String a = "Errore di connessione con i server";
		System.out.println(mess);
		AIConfiguration conf = new AIConfiguration(key);
		AIDataService data = new AIDataService(conf);
		AIRequest messaggio = new AIRequest();
		messaggio.setLanguage("it");
		messaggio.setQuery(mess);
		System.out.println(messaggio.toString());
		AIResponse risposta;
		try {
			risposta = data.request(messaggio);
			System.out.println("CIAO:"+risposta.getResult().getFulfillment().getSpeech());
			if(risposta.getStatus().getCode()==200) {
				return risposta.getResult().getFulfillment().getSpeech();
			}else {
				System.out.println("ERRORE\n\n");
				return a;
			}
		} catch (AIServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return a;
		}
	}
}
