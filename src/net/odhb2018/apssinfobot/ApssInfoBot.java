package net.odhb2018.apssinfobot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import net.odhb2018.apssinfobot.lib.AbilityCommand;
import net.odhb2018.apssinfobot.lib.MySqlConnection;

public class ApssInfoBot extends AbilityBot{
	
	public static String BOT_TOKEN="507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	public static String BOT_USERNAME="ApssInfoBot";
	public static String API_AI_KEY="c5d63d933ae2496fbf3d933aa5ce0ffb";

	protected ApssInfoBot() {//botToken e botUsername
		super(BOT_TOKEN, BOT_USERNAME);
	}
	
	@Override
	public int creatorId() {
		return 137084354;
	}
	
	public Ability hello() {
		return Ability
				.builder()
				.name("hello")
				.info("say hello")
				.input(0)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.action(ctx -> silent.send("Hello world", ctx.chatId()))
				.post(null)
				.build();
	}
	
	@SuppressWarnings("unchecked")
	//warning suppressed because conflict with solutions
	public Ability defaultreply() {
		return Ability
				.builder()
				.name("DEFAULT")
				.flag(Flag.TEXT)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.input(0)
				.action(ctx->{
					SendMessage mess = new SendMessage();
					/*
					 * return 1; -> if the user is on faq mode
					 * return 0; -> if the user isn't on faq mode
					 * return 2; -> if the user isn't in the database
					 * return 3; -> error no connection
					*/
					int a = MySqlConnection.readDataBase(ctx.user().id());
					switch(a) {
					case 1:
						mess = AbilityCommand.faqrisposta(ctx.chatId(), API_AI_KEY ,ctx.arguments().toString());
						break;
					case 0:
						mess.setText("Non ho capito, usa un comando");
						mess.setChatId(ctx.chatId());
						break;
					case 3:
						mess.setText("C'� un errore con la connessione");
						mess.setChatId(ctx.chatId());
						break;
					case 2:
						//TODO registra l'utente nel database
						mess.setText("Utente non registrato");
						mess.setChatId(ctx.chatId());
						break;
					}
					
					silent.execute(mess);
					})
				.post(null)
				.build();
	}
	
	public Ability start() {		
		
		return Ability
				.builder()
				.name("start")
				.info("Comando per iniziare a chattare")
				.input(0)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.action(ctx->silent.execute(AbilityCommand.start(ctx.chatId())))
				.post(null)
				.build();
		
	}
	
	public Ability faq() {
		return Ability
				.builder()
				.name("faq")
				.info("Entra nella sezione faq - Dialogflow abilitato")
				.input(0)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.action(ctx-> {
					SendMessage mess = new SendMessage();
					
					
					mess.setText("Ritorna quando vuoi nella sezione faq");
					silent.execute(mess);
				})
				.post(null)
				.build();
	}
	
	

}
