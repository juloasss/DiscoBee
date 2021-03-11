package fr.discobee;

import java.util.concurrent.CompletionException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

import fr.discobee.interaction.command.CommandContext;
import fr.discobee.interaction.command.Commands;
import fr.discobee.interaction.reaction.Reactions;
import fr.discobee.jda.JDAManager;
import fr.discobee.utils.LogFormatter;

public abstract class DiscoBee {

	public static Logger logger;
	
	protected Commands commands;
	protected Reactions reactions;
	protected Configuration configuration;
	
	public DiscoBee() {
		this("config.json");
	}
	
	public DiscoBee(String configPathIn) {
		init(configPathIn);
		start();
	}
	
	public void init(String configPathIn) {  
		
		logger = Logger.getLogger("DiscoBee");
		setupLogger();
		
		logger.info("[DiscoBee] Initializing configuration");
		configuration = new Configuration(configPathIn);
		logger.info("[DiscoBee] Configuration initialized;");
		
		logger = Logger.getLogger(getBotSpecifications().getName());
		setupLogger();
		
		logger.info("[DiscoBee] Registering commands..");
		commands = new Commands();
		registerCommands(commands);
		logger.info("[DiscoBee] Registered " + commands.count() + " commands !");
		
		logger.info("[DiscoBee] Registering reactions..");
		reactions = new Reactions();
		registerReactions(reactions);
		logger.info("[DiscoBee] Registered " + reactions.count() + " reactions !");
	}
	
	private void setupLogger() {
		ConsoleHandler handler = new ConsoleHandler();
		
		Formatter formatter = new LogFormatter();
		handler.setFormatter(formatter);        
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
	}

	private volatile boolean stop = false;
	private void start() {
		ConsoleListener.start(this);
		try {
			JDAManager.buildShardManager(getBotSpecifications().getToken(), getBotSpecifications().getShards());
		} catch(CompletionException e) {
			logger.severe("[DiscoBee] Could not create the Bot, please check the error logs for details !");
			stop = true;
		}
		while(!stop);
		logger.warning("[DiscoBee] Bot is stopping..");
	}


	public void shutdown() {
		stop = true;
		ConsoleListener.stop();
		JDAManager.getShardManager().shutdown();
		configuration.save();
	}
	
	public abstract void registerCommands(Commands registry);
	public abstract void registerReactions(Reactions reactions);
	public abstract Specifications getBotSpecifications();

	public void onCommand(CommandContext ctx) {
		commands.accept(ctx);
	}

	public static String brigadierVersion() {
		return "1.0.17";
	}
	
}
