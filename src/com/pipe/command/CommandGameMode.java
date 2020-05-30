package com.pipe.command;

import java.util.Collections;
import java.util.List;

import com.pipe.entity.EntityPlayer;
import com.pipe.entity.Player;
import com.pipe.world.EnumGameType;

public class CommandGameMode implements Command{

	@Override
	public String getCommandName() {
		return "gamemode";
	}

	@Override
	public List<String> getCommandAliases() {
		return Collections.singletonList("gm");
	}

	@Override
	public void execute(Player sender, String[] args) throws Exception {
		EntityPlayer player = (EntityPlayer) sender;
		
		if(args.length == 0) {
			player.sendMessage("§cEssaie §l/gamemode (gamemode)§c.");
			return;
		}else {
			for(EnumGameType e : EnumGameType.values()) {
				if(e.getId() == -1) {
					continue;
				}else {
					if(args[0] == String.valueOf(e.getId()) || args[0].equalsIgnoreCase(e.getFullName()) || args[0].equalsIgnoreCase(e.getShortName()) ) {
						player.setGameMode(e);
						player.sendMessage("§aVotre gamemode à été changé avec succées !");
						return;
					}else {
						continue;
					}
				}
			}
			player.sendMessage("§cCe gamemode n'existe pas !");
		}
		
	}

}
