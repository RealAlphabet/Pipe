package com.pipe.command;

import com.pipe.entity.EntityPlayer;
import com.pipe.entity.Player;
import com.pipe.main.Server;

import java.util.Collections;
import java.util.List;

public class CommandTeleport implements Command {

    ///////////////////////////////////////////////////////////////////////////
    //  ALIASES
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return "teleport";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("tp");
    }


    ///////////////////////////////////////////////////////////////////////////
    //  CALLBACK
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void execute(Player sender, String[] args) {
        EntityPlayer player = (EntityPlayer) sender;

        switch (args.length) {
            case 1:
                teleportToPlayer(sender, args[0]);
                break;

            case 2:
                teleportPlayerToPlayer(sender, args[0], args[1]);
                break;

            case 3:
                try {
                    double posX = parseCoordinate(player.posX, args[0]);
                    double posY = parseCoordinate(player.posY, args[1]);
                    double posZ = parseCoordinate(player.posZ, args[2]);

                    player.teleport(posX, posY, posZ, player.yaw, player.pitch);
                    player.sendMessage("§aTéléportation en cours.");

                } catch (NumberFormatException e) {
                    player.sendMessage("§cLa location spécifiée est invalide.");
                }

                break;

            case 4:
//                teleportPlayerToCoordinate(player, args[0], args[1], args[2], args[3]);
                player.sendMessage("§cPas encore implémenté.");
                break;

            default:
                player.sendMessage("§cEssaie §l/tp (x) (y) (z)§c.");
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    //  UTILS
    ///////////////////////////////////////////////////////////////////////////

    private void teleportToPlayer(Player sender, String targetName) {
        Player target = Server.getServer().getPlayerByName(targetName);

        if (target == null)
            sender.sendMessage("§cLe joueur §l" + targetName + " §cn'est pas connecté.");

        else {
            if (target == sender)
                sender.sendMessage("§aTéléportation vers toi même.");

            else {
                sender.sendMessage("§aTéléportation vers §l" + target.getName() + "§a.");
            }

            sender.teleport(target);
        }
    }

    private void teleportPlayerToPlayer(Player sender, String fromName, String toName) {
        Player from = Server.getServer().getPlayerByName(fromName);

        if (from == null)
            sender.sendMessage("§cLe joueur §l" + fromName + " §cn'est pas connecté.");

        else {
            Player to = Server.getServer().getPlayerByName(toName);

            if (to == null)
                sender.sendMessage("§cLe joueur §l" + toName + " §cn'est pas connecté.");

            else {
                sender.sendMessage("§aTéléportation de §l" + from.getName() + "§a vers §l" + to.getName() + "§a.");
                from.teleport(to);
            }
        }
    }

    private double parseCoordinate(double base, String arg) {
        if (arg.startsWith("~"))
            if (arg.length() > 1)
                return base + Double.parseDouble(arg.substring(1));

            else {
                return base;
            }

        else {
            return Double.parseDouble(arg);
        }
    }
}
