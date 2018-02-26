package net.odhb2018.apssinfobot.lib;

import java.util.ArrayList;
import java.util.List;

import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class AbilityCommand   {
	
	public static SendMessage start(long chat_Id) {
		//messaggio di apertura
		SendMessage message = new SendMessage()
				.setChatId(chat_Id)
				.setText("Cosa vuoi sapere?");
		
		//tastiera opzioni
		ReplyKeyboardMarkup rkM =new ReplyKeyboardMarkup();
		//lista delle righe
		List<KeyboardRow> keyboard =new ArrayList<>();
		//riga della tastiera
		KeyboardRow row = new KeyboardRow();
		//aggiungo i parametri
		row.add("Impostazioni");
		row.add("Informazioni");
		keyboard.add(row);
		rkM.setKeyboard(keyboard);
		message.setReplyMarkup(rkM);
		return message;
		
	}

}
