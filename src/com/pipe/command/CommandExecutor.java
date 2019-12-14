package com.pipe.command;

import com.pipe.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        registerCommand(new CommandTeleport());
        registerCommand(new CommandKick());
    }


    ///////////////////////////////////////////////////////////////////////////
    //  EXECUTE
    ///////////////////////////////////////////////////////////////////////////

    public static boolean executeCommand(Player player, String message) {
        if (message.startsWith("/")) {
            String[] args = message.substring(1).split(" ");
            Command command = commandMap.get(args[0]);

            if (command == null)
                player.sendMessage("§cCommande inconnue, essaie §l/help§c.");

            else {
                args = Arrays.copyOfRange(args, 1, args.length);

                try {
                    command.execute(player, args);

                } catch (Exception e) {
                    player.sendMessage("§cUne erreur est survenue lors de l'éxécution de la commande.");
                    e.printStackTrace();
                }
            }

            return true;
        }

        return false;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  UTILS
    ///////////////////////////////////////////////////////////////////////////

    public static void registerCommand(Command command) {
        commandMap.put(command.getCommandName(), command);

        for (String alias : command.getCommandAliases()) {
            commandMap.put(alias, command);
        }
    }
}
