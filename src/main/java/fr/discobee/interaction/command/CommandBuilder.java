package fr.discobee.interaction.command;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A {@link CommandBuilder} used for creating a {@link Command}
 * @author Juloass
 *
 */
public class CommandBuilder {

	/**
	 * Utility method for creating a command builder
	 * @return a {@link CommandBuilder}
	 */
	public static CommandBuilder builder() {
		return new CommandBuilder();
	}
	
	private String name;
	private String description;
	private ArrayList<Predicate<CommandContext>> requirements;
	private Consumer<CommandContext> consumer, then;
	private int thenDelay;
	
	public CommandBuilder() {
		this.name = UUID.randomUUID().toString();
		this.description = "Unknow command";
		this.requirements = new ArrayList<Predicate<CommandContext>>();
		this.consumer = (ctx -> {
			return;
		});
		this.then = null;
		this.thenDelay = 0;
	}

	/**
	 * Define the name used for the {@link Command} built by this {@link CommandBuilder}
	 * @param nameIn
	 * 
	 */
	public CommandBuilder name(String nameIn) {
		this.name = nameIn;
		return this;
	}

	/**
	 * Define the description used for the {@link Command} built by this {@link CommandBuilder}
	 * @param descriptionIn
	 * 
	 */
	public CommandBuilder description(String descriptionIn) {
		this.description = descriptionIn;
		return this;
	}

	/**
	 * Add a {@link Predicate<CommandContext> requirement} to the {@link Command} built by this {@link CommandBuilder}
	 * @param requirementIn
	 * 
	 */
	public CommandBuilder require(Predicate<CommandContext> requirementIn) {
		this.requirements.add(requirementIn);
		return this;
	}

	/**
	 * Define the {@link java.util.function.Consumer action} to do when the {@link Command command} built is called
	 * @param consumerIn
	 * 
	 */
	public CommandBuilder execute(Consumer<CommandContext> consumerIn) {
		this.consumer = consumerIn;
		return this;
	}

	/**
	 * Define the {@link java.util.function.Consumer action} to do when the {@link Command command} built has been called, after <b>{@code delayIn} seconds</b>
	 * @param consumerIn
	 * @param delayIn
	 * 
	 */
	public CommandBuilder then(Consumer<CommandContext> consumerIn, int delayIn) {
		this.then = consumerIn;
		this.thenDelay = delayIn;
		return this;
	}

	/**
	 * Build the command resulting of this Builder
	 * @return the {@link Command} resulting of this {@link CommandBuilder}
	 */
	public Command build() {
		return new Command(this.name, this.description, this.requirements, this.consumer, this.then,
				this.thenDelay);
	}

}