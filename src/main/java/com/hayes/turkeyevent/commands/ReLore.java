package com.hayes.turkeyevent.commands;

import com.hayes.turkeyevent.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReLore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("relore")) {
            Player player = (Player) sender;
            if (args.length == 0) {

                System.out.println(ChatColor.RED + "You must provide an argument for what you want the lore to be");

            } else {

                if (player.hasPermission("turkeyevent.relore")) {

                    List<String> lore = new ArrayList<>();
                    lore.add(TextUtil.join(args));
                    ItemStack itemThing = player.getInventory().getItemInMainHand();
                    ItemMeta itemMeta = itemThing.getItemMeta();
                    itemMeta.setLore(lore);
                    itemThing.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(itemThing);
                    player.sendMessage(ChatColor.GREEN + "Item Lore Added");

                } else {

                    player.sendMessage(ChatColor.GREEN + "TURKEY EVENT" + ChatColor.GRAY + "- You don't have permission");

                }

            }
        }
        else {
            System.out.println("TURKEY EVENT - This command cannot be used in the console");
        }

        return false;
    }

}
