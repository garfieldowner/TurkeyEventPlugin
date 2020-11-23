package com.hayes.turkeyevent.commands;

import com.hayes.turkeyevent.mobs.Turkey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnTurkey implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("spawnturkey") && sender.hasPermission("turkeyevent.spawnturkey")) {
            Player player = ((Player) sender).getPlayer();
            Turkey.spawnTurkey(player);
        }
        else {
            System.out.println("TURKEY EVENT - This command cannot be used in the console");
        }

        return false;
    }
}
