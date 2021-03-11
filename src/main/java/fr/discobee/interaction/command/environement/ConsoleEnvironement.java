package fr.discobee.interaction.command.environement;

public class ConsoleEnvironement implements CommandEnvironement {

	@Override
	public boolean isPrivateChannel() {
		return false;
	}

	@Override
	public boolean isGuildChannel() {
		return false;
	}

	@Override
	public boolean isConsole() {
		return true;
	}

}
