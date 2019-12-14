package com.pipe.command;

import com.pipe.entity.Player;
import com.pipe.main.Server;

import java.util.Collections;
import java.util.List;

public class CommandKick implements Command {

    ///////////////////////////////////////////////////////////////////////////
    //  ALIASES
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return "kick";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("k");
    }


    ///////////////////////////////////////////////////////////////////////////
    //  CALLBACK
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length < 1)
            sender.sendMessage("§cCommande inconnue, essaie §l/kick (joueur) [raison]§c.");

        else {
            String targetName = args[0];
            Player target = Server.getServer().getPlayerByName(targetName);

            if (target == null)
                sender.sendMessage("§cLe joueur §l" + targetName + " §cn'est pas connecté.");

            else {
                if (args.length == 1)
                    target.kick("§cTu as été expulsé par un administrateur.");

                else {
                    StringBuilder sb = new StringBuilder("§c");

                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]).append(' ');
                    }

                    target.kick(sb.toString());
                }

                sender.sendMessage("§aTu as expulsé §l" + target.getName() + "§a.");
            }
        }
    }
}
