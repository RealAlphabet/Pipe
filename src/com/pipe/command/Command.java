package com.pipe.command;

import com.pipe.entity.Player;

import java.util.List;

public interface Command {

    String getCommandName();

    List<String> getCommandAliases();

    void execute(Player player, String[] args) throws Exception;
}
