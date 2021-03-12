<img align="right" src="https://github.com/Juloass/DiscoBee/blob/assets/readme/logo.png?raw=true" height="200" width="200">

# DiscoBee (Java Discord Bot FrameWork)

Discord Bot Engine, or DiscoBee, is aiming to provide a simple way of creating a Discord Bot.

## Summary

1. [Introduction](#creating-the-bee)
2. [Download](#download)
3. [Documentation](#documentation)
4. [Support](#getting-help)
5. [Contributing](#contributing-to-discobee)
6. [Dependencies](#dependencies)

## Creating the Bee
### Gradle
Coming soon.. Sorry! <3

### Creating your first Bee

Creating a bot is done by extending the `DiscoBee` class. 
**Example**:
```Java
public class ExampleBot extends DiscoBee {
	
  @Override
  public void registerCommands(Commands registry) {}
  
  @Override
  public void registerReactions(Reactions registry) {}
  
  @Override
  public Specifications getBotSpecifications() {}
  
}
```
### Configuration
You can access a simple `Configuration` util by using `configuration.string(key, default)`, `configuration.integer(key, default)` and more.
**Example**:
```Java
public class ExampleBot extends DiscoBee {
  private String botToken = this.configuration.string("token", "Insert Token");
  [...]
}
```

### Specification
Your Bee require some `Specifications` to fly, allowing us to tell [JDA](https://github.com/DV8FromTheWorld/JDA/) how your Bot will work.
**Example**:

```java
@Override
public Specifications getBotSpecifications() {
  return 
    Specifications.builder()
      .token("s3cr3t")
      .name("Example Bot")
      .shards(1)
      .build();	
}
```

### Registering

For now, you can register two types of things : `Commands` and `Reactions`\*. Commands is self-explanatory, the last represent what your Bee should do when a user React to a Message with an Emote.

#### Examples:
`Simple Command`
```Java
@Override
public void registerCommands(Commands registry) {
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
}
```
`Brigadier Command`
```Java
@Override
public void registerCommands(Commands registry) {
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
}
```
`Simple Reaction Role`
```Java
@Override
public void registerReactions(Reactions registry) {
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
```

## Download

(Soon) Latest Stable Version: [GitHub Release](https://github.com/Juloass/DiscoBee/releases/latest)

## Documentation
Java Docs will be coming soon.
<br>A simple Wiki will be created in this repository's [Wiki section](https://github.com/Juloass/DiscoBee/wiki)

## Getting Help
For guides and setup help you will look at the [wiki](https://github.com/Juloass/DiscoBee/wiki)
A discord will soon be available

## Contributing to DiscoBee

I will gladly receive any contribution to the project ♥

## Dependencies:

This project requires **Java 8+**.<br>
All dependencies can be managed automatically by Gradle.
 * JDA
   * Version: **4.2.0**
   * [Github](https://github.com/DV8FromTheWorld/JDA)
 * Brigadier
   * Version: **1.0.17**
   * [Github](https://github.com/Mojang/brigadier)
 * Gson
 * commons-io
 * Log4j
