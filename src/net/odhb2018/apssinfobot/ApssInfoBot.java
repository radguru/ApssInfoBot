package net.odhb2018.apssinfobot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;;

public class ApssInfoBot extends AbilityBot{
	
	public static String BOT_TOKEN="507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	public static String BOT_USERNAME="ApssInfoBot";

	protected ApssInfoBot() {//bottoken e botUsername
		super(BOT_TOKEN, BOT_USERNAME);
	}
	
	@Override
	public int creatorId() {
		return 137084354;
	}

	/*@Override
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
		
	}*/
	
	public Ability hello() {
		return Ability
				.builder()
				.name("hello")
				.info("say hello")
				.input(0)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.action(ctx -> silent.send("Hello world", ctx.chatId()))
				.post(ctx-> silent.send("Bye world", ctx.chatId()))
				.build();
	}
	
	@SuppressWarnings("unchecked")
	public Ability defaultreply() {
		return Ability
				.builder()
				.name("DEFAULT")
				.flag(Flag.TEXT)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.input(0)
				.action(ctx->silent.send("Non ho capito", ctx.chatId()))
				.build();
	}

}
