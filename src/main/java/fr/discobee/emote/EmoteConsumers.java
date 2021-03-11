package fr.discobee.emote;

import java.util.function.Predicate;

/**
 * 
 * @author Juloass
 *
 */
public abstract class EmoteConsumers {
	
	public static Predicate<Emote> ANY = new Predicate<Emote>() {
		@Override
		public boolean test(Emote emote) {
			return true;
		}
	};
	
	public static Predicate<Emote> ID(String idIn) {
		return new Predicate<Emote>() {
			@Override
			public boolean test(Emote emote) {
				return emote.getId().equalsIgnoreCase(idIn);
			}
		};
	}
	
}
