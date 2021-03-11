package fr.discobee.message;

import java.util.function.Predicate;

public interface MessageConsumers {
	
	public static Predicate<Message> ANY = new Predicate<Message>() {
		@Override
		public boolean test(Message message) {
			return true;
		}
	};
	
	public static Predicate<Message> ID(String idIn) {
		return new Predicate<Message>() {
			@Override
			public boolean test(Message message) {
				return message.getId().equalsIgnoreCase(idIn);
			}
		};
	}
	
}
