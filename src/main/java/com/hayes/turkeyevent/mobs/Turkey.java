package com.hayes.turkeyevent.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;

public class Turkey {

    public static void spawnTurkey(Player player) {
        Location location = player.getLocation();
        Chicken Turkey = player.getWorld().spawn(location, Chicken.class, (settings) -> {

            settings.setAdult();
            settings.setCustomName(ChatColor.GOLD + "Turkey");
            settings.setGlowing(true);

        });
    }

}
