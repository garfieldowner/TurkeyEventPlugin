package com.hayes.turkeyevent.commands;

import com.hayes.turkeyevent.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CurrentEggs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("currenteggs")) {
            Player player = (Player) sender;
            FileConfiguration uuidFile = Main.plugin.getCustomConfig();
            player.sendMessage(ChatColor.GRAY + "You have opened " + ChatColor.GREEN + uuidFile.getInt(player.getUniqueId().toString() + ".eggs") + ChatColor.GRAY + " eggs so far");
        }
        else {
            System.out.println("TURKEY EVENT - This command cannot be used in the console");
        }

        return false;
    }

}
