package fr.discobee.jda;

import java.util.Arrays;
import java.util.Collection;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class JDAManager {

	private static final Collection<GatewayIntent> INTENTS = Arrays.asList(GatewayIntent.GUILD_MESSAGES,
			GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGES);
	
	public static ShardManager shardManager;

	public static ShardManager getShardManager() {
		return shardManager;
	}

	public static void buildShardManager(String tokenIn, int shardsIn) {
		try {
			 shardManager = DefaultShardManagerBuilder.create(INTENTS).setToken(tokenIn).setShardsTotal(shardsIn).addEventListeners(new MessageListener()).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
}
