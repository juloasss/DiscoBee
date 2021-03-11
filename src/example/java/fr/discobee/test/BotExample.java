package fr.discobee.test;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import fr.discobee.DiscoBee;
import fr.discobee.Specifications;
import fr.discobee.emote.EmoteConsumers;
import fr.discobee.interaction.command.CommandContext;
import fr.discobee.interaction.command.Commands;
import fr.discobee.interaction.reaction.Reactions;
import fr.discobee.message.Message;
import fr.discobee.message.MessageConsumers;
import fr.discobee.role.Roles;

public class BotExample extends DiscoBee {

	@Override
	public void registerCommands(Commands registry) {
		
		// defining the prefix for commands to respond to
		commands.definePrefix(configuration.string("commandPrefix", "!"));
		
		// brigadier example
		commands.register(
			LiteralArgumentBuilder
			.<CommandContext>literal("brigadier")
			.then(
				Commands.literal("info")
				.executes(
						ctx -> { 
							ctx.getSource().getSource().sendMessage("Brigadier is a command parser & dispatcher, designed and developed for Minecraft: Java Edition and now freely available for use elsewhere under the MIT license.");
							return 1; 
						}
				)
			)
			.then(
				Commands.literal("version")
				.executes(
					ctx -> {
						ctx.getSource().getSource().sendMessage("Using Brigadier v" + DiscoBee.brigadierVersion());
						return 1; 
					}
				)
			)
		);
		
		// basic command example
		commands.register(
			Commands.builder()
			.name("hi")
			.require(
				ctx -> ctx.getEnvironement().isPrivateChannel()
			)
			.execute(
				ctx -> ctx.getSource().sendMessage("Hello!")
			)
			.build()
		);
		
		commands.register(
			Commands.builder()
			.name("shutdown")
			.description("Shutdown the bot")
			.require(
				ctx -> ctx.getSource().isBotOwner()
			)
			.execute(
				ctx -> this.shutdown()
			)
			.build()
		);
		
		commands.register(
			Commands.builder()
			.name("ping")
			.require(
				ctx -> ctx.getEnvironement().isGuildChannel() || ctx.getEnvironement().isConsole()
			)
			.execute(
				ctx -> ctx.store(
						ctx.getSource().sendMessage("Pong!")
					)
			)
			.then(
				ctx -> {
					if(ctx.getEnvironement().isConsole()) return;
					ctx.getSourceMessage().delete();
					((Message) ctx.retrieve()).delete();
				},
				5
			)
			.build()
		);
		
	}

	@Override
	public void registerReactions(Reactions registry) {
		
		// defining a reaction handler
		reactions.register(
			Reactions.builder()
			.message(MessageConsumers.ANY)
			.emote(EmoteConsumers.ANY)
			.execute(
				ctx -> ctx.getReactionSource()
						.sendMessage("Action.. reaction!")
			)
			.build()
		);
		
		reactions.register(
			Reactions.builder()
			.message(
				MessageConsumers.ID(
					configuration.string("reactionRulesValidationMessageId", "0")
				)
			)
			.emote(
				EmoteConsumers.ID(
					configuration.string("reactionRulesValidationEmoteId", "0")
				)
			)
			.execute(
				ctx -> ctx.getReactionSource().addRole(
					Roles.get(
						configuration.string("reactionRulesValidationRoleId", "0")		
					)
				)
			)
			.build()
		);
		
	}

	@Override
	public Specifications getBotSpecifications() {
		return 
			Specifications.builder()
				.token(
					configuration.string("token", "Insert Token")
				)
				.name("Example Bot")
				.shards(1)
				.build()
			;	
	}

}
