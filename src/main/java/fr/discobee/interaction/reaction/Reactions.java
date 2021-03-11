package fr.discobee.interaction.reaction;

import java.util.ArrayList;

import fr.discobee.emote.Emote;
import fr.discobee.message.Message;

public class Reactions {

	private ArrayList<Reaction> reactions;

	public Reactions() {
		this.reactions = new ArrayList<Reaction>();
	}

	public void register(Reaction build) {
		reactions.add(build);
	}

	public void accept(Message messageIn, Emote emoteIn, ReactionContext contextIn) {
		reactions.forEach(r -> r.execute(messageIn, emoteIn, contextIn));
	}
	
	public int count() {
		return reactions.size();
	}

	public static ReactionBuilder builder() {
		return new ReactionBuilder();
	}

}
