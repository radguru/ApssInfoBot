package net.odhb2018.apssinfobot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

import net.odhb2018.apssinfobot.lib.AbilityCommand;

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
				.action(ctx->silent.send("Non ho capito", ctx.chatId()))
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
	
	

}
