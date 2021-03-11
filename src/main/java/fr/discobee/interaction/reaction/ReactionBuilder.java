package fr.discobee.interaction.reaction;

import java.util.function.Consumer;
import java.util.function.Predicate;

import fr.discobee.emote.Emote;
import fr.discobee.emote.EmoteConsumers;
import fr.discobee.message.Message;
import fr.discobee.message.MessageConsumers;

public class ReactionBuilder {

	private Predicate<Message> messagePredicate = MessageConsumers.ANY;
	private Predicate<Emote> emotePredicate = EmoteConsumers.ANY;
	private Consumer<ReactionContext> consumer = null;

	public ReactionBuilder message(Predicate<Message> messagePredicateIn) {
		this.messagePredicate = messagePredicateIn;
		return this;
	}

	public ReactionBuilder emote(Predicate<Emote> emotePredicateIn) {
		this.emotePredicate = emotePredicateIn;
		return this;
	}

	public ReactionBuilder execute(Consumer<ReactionContext> consumerIn) {
		consumer = consumerIn;
		return this;
	}

	public Reaction build() {
		return new Reaction(messagePredicate, emotePredicate, consumer);
	}

}