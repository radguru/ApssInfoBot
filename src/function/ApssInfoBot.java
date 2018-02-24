package function;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.abilitybots.api.objects.Ability;

public class ApssInfoBot extends TelegramLongPollingBot{

	@Override
	public String getBotUsername() {
		//BotUsername
		return "ApssInfoBot";
	}

	@Override
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
						//creo la risposta al messaggio
						SendMessage risp = new SendMessage().setChatId(chat_id).setText("Eccoti la tastiera");
						//creo la variabile della tastiera
						ReplyKeyboardMarkup kbM = new ReplyKeyboardMarkup();
						//creo la lista della prima riga
						List<KeyboardRow> listkbm = new ArrayList<>();
						//creo la riga della tastiera
						KeyboardRow row = new KeyboardRow();
						//aggiungo i dati alla prima riga
						row.add("impostazioni");
						row.add("Ospedale più vicino");
						//aggiungo la riga alla lista
						listkbm.add(row);
						//associo la lista alla tastiera
						kbM.setKeyboard(listkbm);
						//setto il tempo della tastiera ad una pressione
						kbM.setOneTimeKeyboard(true);
						//aggiungo la tasitera alla risposta
						risp.setReplyMarkup(kbM);
						//invio la risposta-->try catch --> vado ad interfacciarmi con la rete
						
						try {
							execute(risp);
						} catch (TelegramApiException e1) {
							// TODO se non va a buon fine?
						}
						
						//TODO finire la tastiera
						break;
					default: 
						//creo una variabile SendMessage contenente l'id della chat a cui inviare e il testo in mess
						SendMessage smess = new SendMessage().setChatId(chat_id).setText(mess);
						
						//eseguo l'invio
						//try catch --> vado ad interfacciarmi con la rete
						try {
							execute(smess);
						} catch (TelegramApiException e) {
							// TODO se il messaggio non viene inviato?
						}
						break;
					
					}
					
					
				}
		
	}
	
	public Ability Keyboard1() {
		
	}

	@Override
	public String getBotToken() {
		//Token BotFather
		return "507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	}

}
