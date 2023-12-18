package com.github.wildsource;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import com.github.wildsource.utils.PositionLogger;
import com.github.wildsource.utils.PositionReader;
import com.github.wildsource.utils.TokenReader;

public class CraftLogBot {

	public static void main(String[] args) {
		String token = TokenReader.getToken("src/main/resources/Token");

		DiscordApi api = new DiscordApiBuilder().setToken(token)
												.addIntents(Intent.MESSAGE_CONTENT)
												.login()
												.join();

		api.addMessageCreateListener(event -> {
			if (event	.getMessageContent()
						.equalsIgnoreCase("!ls")) {
				event	.getChannel()
						.sendMessage(PositionReader	.listPositions()
													.toString());
			}
		});

		api.addMessageCreateListener(event -> {
			if (event	.getMessageContent()
						.equalsIgnoreCase("!help")) {
				event	.getChannel()
						.sendMessage("!help :: shows this \n" + "!newPos name x y z :: Creates a new coordinates \n"
								+ "!ls :: Lists all registered coordinates");
			}
		});

		// Syntax with n being the coordinates
		// !newPos nameOfCoord x y z
		api.addMessageCreateListener(event -> {
			if (event	.getMessageContent()
						.contains("!newPos")) {
				if (PositionLogger.createCoordinate(event.getMessageContent())) {
					event	.getChannel()
							.sendMessage("Minecraft Coordinates Recorded Successfully !");
				} else {
					event	.getChannel()
							.sendMessage("Minecraft Coordinates not Recorded because of error :(");
				}
			}
		});

		// Print the invite url of your bot
		System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
	}
}
