package fr.discobee.interaction.command.environement;

/**
 * Represents a CommandEnvironement, can be a {@link PrivateChannelEnvironement} or {@link GuildChannelEnvironement}
 * @author Juloass
 *
 */
public interface CommandEnvironement {

	/**
	 * 
	 * @return <b>true</b> if this {@link CommandEnvironement environement} is a {@link PrivateChannelEnvironement private channel} <br> <b>false</b> otherwise
	 * 
	 */
	public abstract boolean isPrivateChannel();
	
	/**
	 * 
	 * @return <b>true</b> if this {@link CommandEnvironement environement} is a {@link GuildChannelEnvironement guild channel} <br> <b>false</b> otherwise
	 * 
	 */
	public abstract boolean isGuildChannel();
	
	/**
	 * 
	 * @return
	 */
	public abstract boolean isConsole();
	
}