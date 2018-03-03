package net.odhb2018.apssinfobot.function;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;

import net.odhb2018.apssinfobot.lib.PSqlConnection;

public class CallBackFunction {
	
	public static EditMessageText categories(long chatId, long message_id) {
		System.out.println(message_id);
		EditMessageText new_mess = new EditMessageText()
				.setChatId(chatId)
				.setMessageId(Math.toIntExact(message_id))
				.setText("Sai che..." + "Donazioni:")
				.setReplyMarkup(Keyboard.donation());
		
		return new_mess;
				
	}
	
	public static EditMessageText questions(long chatId, long message_id, int utente) {
		System.out.println("Indirizzo del messaggio da aggiornare/aggiornato: " + message_id);
		EditMessageText new_mess = new EditMessageText()
				.setChatId(chatId)
				.setMessageId(Math.toIntExact(message_id))
				.setText("Benvenuto nella sessione di domanda libera\nDialogFlow\nPonimi qualsiasi domanda")
				.setReplyMarkup(null);
		if(PSqlConnection.readDataBase(utente)==1) {
			System.out.println("User presente sul database\nModalita' api.ai attiva da precedente sessione");
			
		}else if(PSqlConnection.readDataBase(utente)==2) {
			PSqlConnection.writeDataBase(utente, true);
			System.out.println("Registrazione utente\nModalita' api.ai attiva");
			
		}else if(PSqlConnection.readDataBase(utente)==3) {
			System.out.println("\n\nERROR\n\n");
		}else if(PSqlConnection.readDataBase(utente)==0) {
			PSqlConnection.changeData(utente, true);
			System.out.println("User presente sul database\nAttivazione modalita' api.ai");
		}
		return new_mess;
	}
	
	public static EditMessageText blod(long chatId, long message_id) {
		System.out.println("Sanguee");
		int a =1;
		EditMessageText new_mess = new EditMessageText()
				.setChatId(chatId)
				.setMessageId(Math.toIntExact(message_id))
				.setReplyMarkup(Keyboard.blod())
				.setText(PSqlConnection.readData(a));
		return new_mess;
	}

}
