package com.hayes.turkeyevent;

import com.hayes.turkeyevent.commands.CurrentEggs;
import com.hayes.turkeyevent.commands.HighestEggs;
import com.hayes.turkeyevent.commands.ReLore;
import com.hayes.turkeyevent.mobs.Turkey;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Sound.ENTITY_CHICKEN_EGG;

public final class Main extends JavaPlugin implements Listener {

    private File customUuidFile;
    private FileConfiguration uuidFile;
    private int playerTask;

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

        playerTask = new BukkitRunnable() {
            @Override
            public void run() {
                Turkey.spawnTurkey(player);
            }
        }.runTaskTimer(this, 1L, (int) (Math.random() * 12000) + 12000).getTaskId();

        try {
            uuidFile.save(customUuidFile);
        } catch (IOException exception1) {
            exception1.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Bukkit.getScheduler().cancelTask(playerTask);
    }


    @EventHandler
    public void onEggThrow(ProjectileLaunchEvent e) {
        if (e.getEntityType().equals(EntityType.EGG) && e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            ItemStack egg = player.getInventory().getItemInMainHand();
            List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
            if (lore != null && lore.get(1).equals(ChatColor.GREEN + "Thanksgiving Item")) {

                double randNum = (Math.random() * 5);
                ItemStack randItem;
                e.setCancelled(true);

                if (randNum >= 0 && randNum <= 0.25) {
                    randItem = new ItemStack(Material.EMERALD, 4);
                }
                else if (randNum >= 0.26 && randNum <= 0.50) {
                    randItem = new ItemStack(Material.LAPIS_LAZULI, 16);
                }
                else if (randNum >= 0.51 && randNum <= 0.75) {
                    randItem = new ItemStack(Material.REDSTONE, 16);
                }
                else if (randNum >= 0.76 && randNum <= 1.00) {
                    randItem = new ItemStack(Material.DIAMOND);
                }
                else if (randNum >= 1.01 && randNum <= 1.25) {
                    randItem = new ItemStack(Material.IRON_INGOT, 4);
                }
                else if (randNum >= 1.26 && randNum <= 1.50) {
                    randItem = new ItemStack(Material.GOLD_INGOT, 2);
                }
                else if (randNum >= 1.51 && randNum <= 1.75) {
                    randItem = new ItemStack(Material.DIAMOND);
                }
                else if (randNum >= 1.76 && randNum <= 1.77) {
                    randItem = new ItemStack(Material.NETHERITE_SCRAP);
                }
                else if (randNum >= 1.78 && randNum <= 2.00) {
                    randItem = new ItemStack(Material.COAL_BLOCK);
                }
                else if (randNum >= 2.01 && randNum <= 2.25) {
                    randItem = new ItemStack(Material.FIREWORK_ROCKET, 16);
                }
                else if (randNum >= 2.26 && randNum <= 2.27) {
                    randItem = new ItemStack(Material.WITHER_SKELETON_SKULL);
                }
                else if (randNum >= 2.28 && randNum <= 2.50) {
                    randItem = new ItemStack(Material.CAKE);
                }
                else if (randNum >= 2.51 && randNum <= 2.75) {
                    randItem = new ItemStack(Material.GOLDEN_APPLE);
                }
                else if (randNum >= 2.76 && randNum <= 3.00) {
                    randItem = new ItemStack(Material.COAL_BLOCK);
                }
                else if (randNum >= 3.01 && randNum <= 3.25) {
                    randItem = new ItemStack(Material.LARGE_FERN, 4);
                }
                else if (randNum >= 3.26 && randNum <= 3.50) {
                    randItem = new ItemStack(Material.PUFFERFISH);
                }
                else if (randNum >= 3.51 && randNum <= 3.75) {
                    randItem = new ItemStack(Material.RABBIT_FOOT);
                }
                else if (randNum >= 3.76 && randNum <= 4.00) {
                    randItem = new ItemStack(Material.SADDLE);
                }
                else if (randNum >= 4.01 && randNum <= 4.25) {
                    randItem = new ItemStack(Material.NAME_TAG);
                }
                else if (randNum >= 4.26 && randNum <= 4.50) {
                    randItem = new ItemStack(Material.LEAD);
                }
                else if (randNum >= 4.51 && randNum <= 4.75) {
                    randItem = new ItemStack(Material.FLINT, 8);
                }
                else if (randNum >= 4.76 && randNum <= 5.00) {
                    randItem = new ItemStack(Material.COOKIE, 4);
                }
                else {
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

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            if (e.getEntity() instanceof Chicken && e.getEntity().getCustomName().equals(ChatColor.GOLD + "Turkey")) {
                Player player = e.getEntity().getKiller();

                e.getDrops().clear();

                ItemStack item = new ItemStack(Material.EGG);
                List<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(ChatColor.GREEN + "Thanksgiving Item");
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(ChatColor.GOLD + "Turkey" + ChatColor.WHITE + " Egg");
                item.setItemMeta(itemMeta);

                LootContext.Builder contextBuilder = new LootContext.Builder(e.getEntity().getLocation()).lootedEntity(e.getEntity()).killer(player);
                AttributeInstance attributeLuck = player.getAttribute(Attribute.GENERIC_LUCK);

                if (attributeLuck != null) {
                    contextBuilder.luck(((float) attributeLuck.getValue()));
                }

                contextBuilder.lootingModifier(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));

                LootContext context = contextBuilder.build();

                LootTable lootile = new LootTable() {
                    @Override
                    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
                        Collection<ItemStack> itemStack = new ArrayList<>();
                        for (int i = -1; i < context.getLootingModifier() * (int) (Math.random() * 2); i++) {
                            itemStack.add(item);
                        }
                        return itemStack;
                    }

                    @Override
                    public void fillInventory(Inventory inventory, Random random, LootContext context) {

                    }

                    @Override
                    public NamespacedKey getKey() {
                        return null;
                    }
                };

                for (ItemStack loot : lootile.populateLoot(ThreadLocalRandom.current(), context)) {
                    e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), loot);
                }
            }
        }
    }

}