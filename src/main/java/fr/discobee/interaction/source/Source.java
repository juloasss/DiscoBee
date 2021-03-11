package fr.discobee.interaction.source;

import fr.discobee.message.Message;
import fr.discobee.role.Roles.Role;

public interface Source {
	Message sendMessage(String string);
	void addRole(Role role);
	boolean isBotOwner();
}
