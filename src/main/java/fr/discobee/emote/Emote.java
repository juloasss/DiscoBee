package fr.discobee.emote;

/**
 * This represents an Emote
 * @author Juloass
 *
 */
public class Emote {
	private final String id;
	private Emote(String idIn) {
		this.id = idIn;
	}
	
	/**
	 * Get the String representation of the long ID of this emote
	 * @return the id of this emote
	 */
	public String getId() {
		return this.id;
	}
}
