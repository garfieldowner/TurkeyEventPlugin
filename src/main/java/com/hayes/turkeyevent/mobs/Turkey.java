package com.hayes.turkeyevent.mobs;

import com.hayes.turkeyevent.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;

public class Turkey {

    public static void spawnTurkey(Player player) {
        if(!player.isOnline()) return;
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1F, 1F);
        player.sendMessage(TextUtil.convertColor("&aA wild turkey has spawned nearby! &7Kill it and harvest its egg for a Thanksgiving present!"));

        Location location = player.getLocation();
        Chicken turkey = player.getWorld().spawn(location, Chicken.class, (settings) -> {
            settings.setAdult();
            settings.setCustomName(ChatColor.YELLOW + "Turkey");
            settings.setGlowing(true);
        });
    }
}
