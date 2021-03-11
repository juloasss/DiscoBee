package fr.discobee.interaction.reaction;

import fr.discobee.interaction.source.Source;

/**
 * The context in which this reaction event is executed
 * 
 * @author Juloass
 *
 */
public class ReactionContext {

	private Source source;

	/**
	 * 
	 * @return the source of this action
	 */
	public Source getReactionSource() {
		return source;
	}

}
