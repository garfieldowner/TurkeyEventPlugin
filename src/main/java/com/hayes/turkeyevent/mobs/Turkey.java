package com.hayes.turkeyevent.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;

public class Turkey {

    public static void spawnTurkey(Player player) {
        int x = (int) (Math.random() * 2) - 2;
        int y = (int) (Math.random() * 2) - 2;
        int z = (int) (Math.random() * 2) - 2;
        Location location = player.getLocation().add(x, y, z);
        Chicken Turkey = player.getWorld().spawn(location, Chicken.class, (settings) -> {

            settings.setAdult();
            settings.setCustomName(ChatColor.GOLD + "Turkey");
            settings.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150.0);
            settings.setHealth(150.0);
            settings.setGlowing(true);

        });
    }

}
