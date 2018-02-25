import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class ApssInfoBot extends TelegramLongPollingBot{

	@Override
	public String getBotUsername() {
		return "ApssInfoBot";
	}
	
	@Override
	public String getBotToken() {
		return "507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	}

	@Override
	//onUpdateReceived --> viene richiamata con qualsiasi input da parte dell'utente
	//Update rec --> variabile che contiene i dati dell'input dell'utente
	public void onUpdateReceived(Update rec) {
		//controllo che sia un testo e non altro tipo di input
		if(rec.hasMessage()&&rec.getMessage().hasText()) {
	
			//mess --> viene inizializzata come il testo inviato dall'utente
			String mess = rec.getMessage().getText();
			//chat_id --> id della chat per riconoscere a quale utente stiamo rispondendo
			long chat_id = rec.getMessage().getChatId();
			
			//uso switch e case -- troppi if-else-if poca efficienza
			switch(mess) {
			case "/markup":
				break;
			default:
				//replica di default
				defaultreply(mess,chat_id);
				break;
			
			}
			
			
		}
		
	}
	
	public void defaultreply(String mess, long chat_id) {
		
		//creo una variabile SendMessage contenente l'id della chat a cui inviare e il testo in mess
		SendMessage smess = new SendMessage().setChatId(chat_id).setText(mess);
		//eseguo l'invio
		try {
			execute(smess);
		} catch (TelegramApiException e) {
			// TODO se il messaggio non viene inviato?
		}
		
	}
}
