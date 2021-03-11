package fr.discobee.interaction.command;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Command {

	private final String name;
	private final String description;
	private final ArrayList<Predicate<CommandContext>> requirements;
	private final Consumer<CommandContext> consumer, then;
	private final int thenDelay;

	protected Command(String nameIn, String descriptionIn, ArrayList<Predicate<CommandContext>> requirementsIn,
			Consumer<CommandContext> consumerIn, Consumer<CommandContext> thenIn, int thenDelayIn) {
		this.name = nameIn;
		this.description = descriptionIn;
		this.requirements = requirementsIn;
		this.consumer = consumerIn;
		this.then = thenIn;
		this.thenDelay = thenDelayIn;
	}

	/**
	 * 
	 * @return the description of this command
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * execute this command if all {@link #requirements} are fullfilled
	 * 
	 * @param contextIn
	 */
	public boolean execute(CommandContext contextIn) {
		if (!contextIn.getCommandName().equalsIgnoreCase(this.name))
			return false;
		boolean shouldExecute = true;
		for (Predicate<CommandContext> p : requirements) {
			if (!p.test(contextIn)) {
				shouldExecute = false;
				break;
			}
		}
		if (shouldExecute)
			consumer.accept(contextIn);
		if (shouldExecute && then != null)
			Executors.newSingleThreadScheduledExecutor().schedule(() -> then.accept(contextIn), thenDelay,
					TimeUnit.SECONDS);
		return true;
	}

}