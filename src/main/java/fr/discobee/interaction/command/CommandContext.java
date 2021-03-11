package fr.discobee.interaction.command;

import java.util.ArrayDeque;

import fr.discobee.interaction.command.environement.CommandEnvironement;
import fr.discobee.interaction.source.Source;
import fr.discobee.message.Message;

/**
 * The context in which this command is executed
 * @author Juloass
 *
 */
public class CommandContext {
	
	private CommandEnvironement environement = null;
	private Source source = null;
	private ArrayDeque<Object> stored = new ArrayDeque<Object>();
	private Message sourceMessage;
	private String command;
	
	public CommandContext(CommandEnvironement environementIn, Source sourceIn, Message sourceMessageIn, String commandIn) {
		this.environement = environementIn;
		this.source = sourceIn;
		this.sourceMessage = sourceMessageIn;
		this.command = commandIn;
	}
	
	public CommandEnvironement getEnvironement() {
		return this.environement;
	}

	/**
	 * 
	 * Get the {@link Source} of this command, can be either a {@link ChannelSource} or {@link MemberSource}
	 * @return the source of this command
	 */
	public Source getSource() {
		return this.source;
	}
	
	/**
	 * 
	 * @return the {@link Message} that triggered this command
	 */
	public Message getSourceMessage() {
		return this.sourceMessage;
	}
	
	/**
	 *
	 * @param objectIn an object to be stored for later use in execution of this command
	 */
	public void store(Object objectIn) {
		this.stored.addLast(objectIn);
	}
	
	/**
	 * Retrieve an object previously stored using {@link #store store} method
	 * This method is FIFO
	 * @return the first available object stored
	 */
	public Object retrieve() {
		return this.stored.pollFirst();
	}
	
	/**
	 * 
	 * @return command name used to trigger this execution
	 */
	public String getCommandName() {
		return this.command.split(" ")[0];
	}

	public String getCommand() {
		return this.command;
	}

}
