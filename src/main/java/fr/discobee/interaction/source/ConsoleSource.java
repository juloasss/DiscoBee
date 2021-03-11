package fr.discobee.interaction.source;

import fr.discobee.DiscoBee;
import fr.discobee.message.ConsoleMessage;
import fr.discobee.message.Message;
import fr.discobee.role.Roles.Role;

public class ConsoleSource implements Source {

	@Override
	public Message sendMessage(String content) {
		content = "[Console] " + content;
		DiscoBee.logger.info(content);
		return new ConsoleMessage(content);
	}

	@Override
	public void addRole(Role role) {
		return;
	}

	@Override
	public boolean isBotOwner() {
		return true;
	}

}
