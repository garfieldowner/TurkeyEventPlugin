package com.hayes.turkeyevent;

import com.hayes.turkeyevent.mobs.Turkey;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TurkeySpawn extends BukkitRunnable {

    private final Player player;
    private final Main plugin;

    public TurkeySpawn(Player thisPlayer, Main plugin) {
        player = thisPlayer;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Turkey.spawnTurkey(player);
        BukkitTask task = new TurkeySpawn(player, plugin).runTaskLater(Main.plugin, 8 * 60 * 20 + (int)((4 * 60 * 20)*Math.random()));
        plugin.getTasks().remove(player.getUniqueId());
        plugin.getTasks().put(player.getUniqueId(), task);
    }

    public Player getPlayer() {
        return player;
    }
}
