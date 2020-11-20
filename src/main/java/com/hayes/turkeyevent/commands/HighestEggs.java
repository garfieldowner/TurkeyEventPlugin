package com.hayes.turkeyevent.commands;

import com.hayes.turkeyevent.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HighestEggs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("highesteggs")) {
            Player player = (Player) sender;
            FileConfiguration uuidFile = Main.plugin.getCustomConfig();
            int highestEggs = uuidFile.getInt("highesteggs.eggs");
            UUID playerUUID = UUID.fromString(uuidFile.getString("highesteggs.uuid"));
            String playerName = Bukkit.getPlayer(playerUUID).getDisplayName();
            player.sendMessage(ChatColor.GREEN + playerName + ChatColor.GRAY + " has opened the highest amount of eggs opened so far at " + ChatColor.GREEN + highestEggs);
        }
        else {
            System.out.println("TURKEY EVENT - This command cannot be used in the console");
        }

        return false;
    }

}
