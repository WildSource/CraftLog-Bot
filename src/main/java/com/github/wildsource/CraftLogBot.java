package com.github.wildsource;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import com.github.wildsource.utils.PositionLogger;
import com.github.wildsource.utils.PositionReader;
import com.github.wildsource.utils.TokenReader;

public class CraftLogBot {

	public static void main(String[] args) {
		String token = TokenReader.getToken("Token");

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
						.sendMessage("Les commandes commence par ! \n" + "example: !command \n" + "\n"
								+ "help -> montre les commandes dispo \n"
								+ "newPos nom x y z -> Cree une coord en remplacent \"nom\" avec le nom de la coord et x y z avec les coords\n"
								+ "ls -> Liste les coords");
			}
		});

		// Syntax with n being the coordinates
		// !newPos nameOfCoord x y z
		api.addMessageCreateListener(event -> {
			if (event	.getMessageContent()
						.contains("!newPos")) {
				if (PositionLogger.createCoordinate(event.getMessageContent())) {
					event	.getChannel()
							.sendMessage("Coord enregistrer !");
				} else {
					event	.getChannel()
							.sendMessage("Coord non enregistrer a cause d'une erreur :(");
				}
			}
		});

		// Print the invite url of your bot
		System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
	}
}
