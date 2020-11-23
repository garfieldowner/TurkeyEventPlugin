package com.hayes.turkeyevent;

import com.hayes.turkeyevent.mobs.Turkey;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TurkeySpawn extends BukkitRunnable {

    private final Player player;

    public TurkeySpawn(Player thisPlayer) {
        player = thisPlayer;
    }


    @Override
    public void run() {
        Turkey.spawnTurkey(player);
    }
}
