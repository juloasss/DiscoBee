package fr.discobee.message;

import fr.discobee.DiscoBee;

public class ConsoleMessage extends Message {
	
	public ConsoleMessage(String contentIn) {
		super("0", contentIn);
	}
	
	@Override
	public void delete() {
		DiscoBee.logger.warning("Someone tried to delete a console message! Console Messages are not Discord Messages and therefor can not be manipulated, fix this.");
		return;
	}

}
