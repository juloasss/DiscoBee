package fr.discobee.interaction.command;

import java.util.ArrayList;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

/**
 * 
 * @author Juloass
 *
 */
public class Commands {

	private String prefix = "!";
	private ArrayList<Command> commands;
	private CommandDispatcher<CommandContext> dispatcher;

	public Commands() {
		commands = new ArrayList<Command>();
		dispatcher = new CommandDispatcher<CommandContext>();
	}

	/**
	 * Define the prefix used by this bot for commands
	 * @param prefixIn , the prefix to use
	 */
	public void definePrefix(String prefixIn) {
		this.prefix = prefixIn;
	}

	/**
	 * 
	 * @return the prefix used by the bot for commands
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * register a command into this bot registry
	 * @param the command to register
	 */
	public void register(Command command) {
		commands.add(command);
	}

	public void accept(CommandContext contextIn) {
		for(Command cmd : commands) {
			if(cmd.execute(contextIn)) {
				return;
			}
		}
		try {
			dispatcher.execute(contextIn.getCommand(), contextIn);
			return;
		} catch(CommandSyntaxException e) {
			contextIn.getSource().sendMessage(e.getMessage());
			return;
		}
	}
	
	public int count() {
		return commands.size();
	}

	/**
	 * Utility method for creating a command builder
	 * @return a {@link CommandBuilder}
	 */
	public static CommandBuilder builder() {
		return new CommandBuilder();
	}
	
	public static LiteralArgumentBuilder<CommandContext> literal(String name) {
		return LiteralArgumentBuilder.<CommandContext>literal(name);
	}

	public static <T> RequiredArgumentBuilder<CommandContext, T> argument(String name, ArgumentType<T> type) {
		return RequiredArgumentBuilder.<CommandContext, T>argument(name, type);
	}

	public void register(LiteralArgumentBuilder<CommandContext> cmd) {
		dispatcher.register(cmd);
	}
	
}
