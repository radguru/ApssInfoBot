package net.odhb2018.apssinfobot.function;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Keyboard {
	
	
	public static InlineKeyboardMarkup donation() {
		InlineKeyboardMarkup inr = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> uplinr = new ArrayList<>();
		List<InlineKeyboardButton> linr=new ArrayList<>();
		InlineKeyboardButton san, org, mid, tess, cor;
		san = new InlineKeyboardButton();
		org = new InlineKeyboardButton();
		mid = new InlineKeyboardButton();
		tess = new InlineKeyboardButton();
		cor = new InlineKeyboardButton();
		san.setText("Sangue")
			.setCallbackData("blod");
		org.setText("Organi")
			.setCallbackData("organ");
		mid.setText("Midollo")
			.setCallbackData("marrow");
		tess.setText("Tessuto")
			.setCallbackData("woven");
		cor.setText("Cordone")
			.setCallbackData("umbilical");
		linr.add(san);
		linr.add(org);
		linr.add(mid);
		linr.add(tess);
		linr.add(cor);
		uplinr.add(linr);
		inr.setKeyboard(uplinr);
		return inr;
	}
	
	public static InlineKeyboardMarkup blod() {
		InlineKeyboardMarkup inr = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> uplinr = new ArrayList<>();
		List<InlineKeyboardButton> linr=new ArrayList<>();
		InlineKeyboardButton cos, dov, com;
		cos= new InlineKeyboardButton()
				.setText("Cosa donare?")
				.setCallbackData("what-blod");
		dov = new InlineKeyboardButton()
				.setText("Dove donare")
				.setCallbackData("where-blod");
		com= new InlineKeyboardButton()
				.setText("Come")
				.setCallbackData("how-blod");
		linr.add(com);
		linr.add(dov);
		linr.add(cos);
		uplinr.add(linr);
		inr.setKeyboard(uplinr);
		return inr;
	}
	

}
