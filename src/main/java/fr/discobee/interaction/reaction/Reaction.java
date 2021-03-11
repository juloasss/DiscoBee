package fr.discobee.interaction.reaction;

import java.util.function.Consumer;
import java.util.function.Predicate;

import fr.discobee.emote.Emote;
import fr.discobee.message.Message;

public class Reaction {
	private final Predicate<Message> messagePredicate;
	private final Predicate<Emote> emotePredicate;
	private final Consumer<ReactionContext> consumer;

	Reaction(Predicate<Message> messagePredicateIn, Predicate<Emote> emotePredicateIn, Consumer<ReactionContext> consumerIn) {
		this.messagePredicate = messagePredicateIn;
		this.emotePredicate = emotePredicateIn;
		this.consumer = consumerIn;
	}

	public void execute(Message messageIn, Emote emoteIn, ReactionContext contextIn) {
		if (messagePredicate.test(messageIn) && emotePredicate.test(emoteIn))
			consumer.accept(contextIn);
	}
}
