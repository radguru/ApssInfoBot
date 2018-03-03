package net.odhb2018.apssinfobot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import net.odhb2018.apssinfobot.lib.AbilityCommand;

//Usage of AbilityBot from TelegramBot API of rubenlagus
public class ApssInfoBot extends AbilityBot{
	
	/*Initialize of the token
	 * BOT_TOKEN -> Token from BotFather of the Bot owned
	 * BOT_USERNAME -> Username of this BOT
	 * API_AI_KEY -> Token from Api.ai(DialogFlow) of the project
	 */	
	public static String BOT_TOKEN="507945152:AAHB1AsKcA22QrKuWGKKxOim95B27OkA7wE";
	public static String BOT_USERNAME="ApssInfoBot";
	public static String API_AI_KEY="9af2fbbcd5b8483f9c01725b8a0ddfc8";

	
	protected ApssInfoBot() {
		super(BOT_TOKEN, BOT_USERNAME);
	}
	
	@Override
	public int creatorId() {
		return 137084354;
	}
	
	//default operation on callback
	@SuppressWarnings("unchecked")
	public Ability defaultreply() {
		return Ability
			.builder()
				.name("DEFAULT")
//				.flag(Flag.CALLBACK_QUERY)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.input(0)
				.action(ctx->{
					if(ctx.update().hasCallbackQuery()) {
						//TODO funzione del call back
						try {
							execute(CallBackApss.CallBackOne(ctx.update()));
						} catch (TelegramApiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						System.out.println("Stringa di testo");
						SendMessage l = AbilityCommand.faqrisposta(ctx.chatId(), API_AI_KEY , ctx.update().getMessage().getText());
						try {
							execute(l);
						} catch (TelegramApiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				})
				.post(null)
				.build();
	}
	//command for /start
	public Ability start() {		
		
		return Ability
				.builder()
				.name("start")
				.info("Comando per iniziare a chattare")
				.input(0)
				.locality(Locality.ALL)
				.privacy(Privacy.PUBLIC)
				.action(ctx->{
					
						silent.execute(AbilityCommand.start(ctx.chatId(),ctx.update().getMessage().getFrom().getId()));
				
				})
				.post(null)
				.build();
		
	}
	
//	@SuppressWarnings("unchecked")
//	public Ability defaultreply_text() {
//		return Ability
//			.builder()
//				.name("DEFAULT")
//				.flag(Flag.TEXT)
//				.locality(Locality.USER)
//				.privacy(Privacy.PUBLIC)
//				.input(0)
//				.action(ctx->{
//					
//				})
//				.post(null)
//				.build();
//	}
	
}
