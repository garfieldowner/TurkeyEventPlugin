package com.hayes.turkeyevent;

import com.hayes.turkeyevent.commands.CurrentEggs;
import com.hayes.turkeyevent.commands.HighestEggs;
import com.hayes.turkeyevent.commands.ReLore;
import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.bukkit.Sound.ENTITY_CHICKEN_EGG;

public final class Main extends JavaPlugin implements Listener {

    private File customUuidFile;
    private FileConfiguration uuidFile;

    public static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        createCustomConfig();
        this.getCommand("relore").setExecutor(new ReLore());
        this.getCommand("currenteggs").setExecutor(new CurrentEggs());
        this.getCommand("highesteggs").setExecutor(new HighestEggs());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

            }
        }, (int) (Math.random() * 12000) + 12000, 12000L);
        // Output to Console
        System.out.println("TURKEY EVENT - Plugin has loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Output to Console
        System.out.println("TURKEY EVENT - Plugin has unloaded");
    }

    public FileConfiguration getCustomConfig() {
        return this.uuidFile;
    }

    private void createCustomConfig() {
        customUuidFile = new File(getDataFolder(), "data.yml");
        if (!customUuidFile.exists()) {
            customUuidFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        uuidFile = new YamlConfiguration();
        try {
            uuidFile.load(customUuidFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        Entity ent = e.getEntity();
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FileConfiguration uuidFile = this.getCustomConfig();
        String playerUUID = player.getUniqueId().toString();
        if (!uuidFile.contains(playerUUID)) {
            uuidFile.createSection(playerUUID + ".eggs");
            uuidFile.set(playerUUID + ".eggs", 0);
        }
        try {
            uuidFile.save(customUuidFile);
        } catch (IOException exception1) {
            exception1.printStackTrace();
        }
    }


    @EventHandler
    public void onEggThrow(ProjectileLaunchEvent e) {
        if (e.getEntityType().equals(EntityType.EGG) && e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            ItemStack egg = player.getInventory().getItemInMainHand();
            List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
            if (lore != null && lore.get(0).equals(ChatColor.GREEN + "Holiday Item")) {

                int randNum = (int) (Math.random() * 5) + 1;
                ItemStack randItem;
                e.setCancelled(true);

                switch (randNum) {
                    case 1:
                        randItem = new ItemStack(Material.DIAMOND);
                        break;
                    case 2:
                        randItem = new ItemStack(Material.EMERALD);
                        break;
                    case 3:
                        randItem = new ItemStack(Material.IRON_INGOT);
                        break;
                    case 4:
                        randItem = new ItemStack(Material.GOLD_INGOT);
                        break;
                    default:
                        randItem = new ItemStack(Material.APPLE);
                }

                player.getWorld().dropItem(player.getLocation(), randItem);
                player.playSound(player.getLocation(), ENTITY_CHICKEN_EGG, 1, 0);
                player.sendMessage(ChatColor.GREEN + "You have opened a Reward Egg!");

                if (egg.getAmount() == 1) {

                    egg = new ItemStack(Material.AIR);

                } else {

                    egg.setAmount(egg.getAmount() - 1);

                }

                player.getInventory().setItemInMainHand(egg);
                if (!(player.getGameMode() == GameMode.CREATIVE)) {
                    String playerUUID = player.getUniqueId().toString();
                    FileConfiguration uuidFile = this.getCustomConfig();
                    int currentEggs = uuidFile.getInt(playerUUID + ".eggs");
                    currentEggs++;
                    uuidFile.set(playerUUID + ".eggs", currentEggs);
                    if (!(uuidFile.contains("highesteggs.eggs"))) {
                        uuidFile.createSection("highesteggs.eggs");
                    }
                    if (!(uuidFile.contains("highesteggs.uuid"))) {
                        uuidFile.createSection("highesteggs.uuid");
                    }
                    int highestEggs = uuidFile.getInt("highesteggs.eggs");
                    if (currentEggs > highestEggs) {
                        uuidFile.set("highesteggs.eggs", currentEggs);
                        uuidFile.set("highesteggs.uuid", playerUUID);
                    }
                    try {
                        uuidFile.save(customUuidFile);
                    } catch (IOException exception1) {
                        exception1.printStackTrace();
                    }
                }
            }
        }
    }

}