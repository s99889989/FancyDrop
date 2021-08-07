package com.daxton.fancydrop.listener;

import com.daxton.fancycore.api.task.PackEntity;
import com.daxton.fancydrop.FancyDrop;
import com.daxton.fancydrop.config.FileConfig;
import com.daxton.fancymobs.api.FancyMob;
import com.daxton.fancymobs.api.event.FancyMobDeathEvent;
import com.daxton.fancymobs.api.event.FancyMobSpawnEvent;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FancyMobListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)//怪物出生
    public void onFancyMobSpawn(FancyMobSpawnEvent event){

    }

    @EventHandler(priority = EventPriority.LOW)//怪物死亡
    public void onFancyMobDeath(FancyMobDeathEvent event){
        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        Player killer = event.getKiller();
        UUID killerUUID = killer.getUniqueId();
        String itemAllocation = config.getString("ItemSettings.ItemAllocation")+"";
        boolean itemNameEnable = config.getBoolean("ItemSettings.ItemShow.Enable");
        String itemShowName = config.getString("ItemSettings.ItemShow.Display")+"";
        FancyMob fancyMob = event.getFancyMob();
        Location location = fancyMob.getEntity().getLocation();

        if(itemAllocation.equalsIgnoreCase("Random")){
           List<UUID> uuidList = Lists.newArrayList(event.getThreatTable().keySet());
           if(!uuidList.isEmpty()){
               Collections.shuffle(uuidList);
               UUID uuid = uuidList.get(0);
               for(ItemStack itemStack : event.getDropItems()){
                   int amount = itemStack.getAmount();
                   String itemName = itemStack.getType().name();
                   if(itemStack.getItemMeta() != null && !itemStack.getItemMeta().getDisplayName().isEmpty()){
                       itemName = itemStack.getItemMeta().getDisplayName();
                   }
                   Item item = (Item) location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);
                   item.setItemStack(itemStack);
                   item.setOwner(uuid);
                   if(itemNameEnable){
                       PackEntity.setName(item.getEntityId(), itemShowName.replace("%player_name%", Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName()).replace("%item_amount%", String.valueOf(amount)).replace("%item_name%", itemName));
                   }
               }
           }
        }else if(itemAllocation.equalsIgnoreCase("MaxThreat")){
            UUID mastUUID = null;
            double most = 0;
            for(UUID uuid : fancyMob.getThreatTable().keySet()){
                double amount = fancyMob.getThreatTable().get(uuid);
                if(amount > most){
                    most = amount;
                    mastUUID = uuid;
                }
            }
            if(mastUUID != null){
                for(ItemStack itemStack : event.getDropItems()){
                    int amount = itemStack.getAmount();
                    String itemName = itemStack.getType().name();
                    if(itemStack.getItemMeta() != null && !itemStack.getItemMeta().getDisplayName().isEmpty()){
                        itemName = itemStack.getItemMeta().getDisplayName();
                    }
                    Item item = (Item) location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);
                    item.setItemStack(itemStack);
                    item.setOwner(mastUUID);
                    if(itemNameEnable){
                        PackEntity.setName(item.getEntityId(), itemShowName.replace("%player_name%", Objects.requireNonNull(Bukkit.getPlayer(mastUUID)).getName()).replace("%item_amount%", String.valueOf(amount)).replace("%item_name%", itemName));
                    }
                }
            }
        }else {
            event.getDropItems().forEach(itemStack -> {
                int amount = itemStack.getAmount();
                String itemName = itemStack.getType().name();
                if(itemStack.getItemMeta() != null && !itemStack.getItemMeta().getDisplayName().isEmpty()){
                    itemName = itemStack.getItemMeta().getDisplayName();
                }
                Item item = (Item) location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);
                item.setItemStack(itemStack);
                item.setOwner(killerUUID);
                if(itemNameEnable){
                    PackEntity.setName(item.getEntityId(), itemShowName.replace("%player_name%", killer.getName()).replace("%item_amount%", String.valueOf(amount)).replace("%item_name%", itemName));
                }
            });
        }
        event.removeItems();
//        List<ItemStack> dropItems = new ArrayList<>();
//        dropItems.add(new ItemStack(Material.PLAYER_HEAD));
//        event.setDropItems(dropItems);
//        event.setMoney(10);
//        event.setDropExp(10);


//        killer.sendMessage("怪物死亡2");
//        event.getDropItems().forEach(itemStack ->  killer.sendMessage("獲得物品: "+itemStack.getType()));
//        killer.sendMessage("目前經驗:" + killer.getTotalExperience());
//        killer.sendMessage("目前存款: " + Currency.getMoneyAmount(killer));
    }

}
