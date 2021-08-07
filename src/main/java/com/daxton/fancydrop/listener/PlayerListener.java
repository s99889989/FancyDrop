package com.daxton.fancydrop.listener;


import com.daxton.fancydrop.FancyDrop;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;


public class PlayerListener implements Listener {

    public static UUID test;
//
//    @EventHandler//當玩家登入
//    public void onPlayerJoin(PlayerJoinEvent event){
//        Player player = event.getPlayer();
//
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)//怪物死亡
//    public void onDeath(EntityDeathEvent event) {
//        if(!(event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
//            return;
//        }
//        if(event.getEntity().getKiller() != null){
//            Player player = event.getEntity().getKiller();
//            UUID killerUUID = player.getUniqueId();
//            Entity deathEntity = event.getEntity();
//            UUID deathUUID = deathEntity.getUniqueId();
//            test = deathUUID;
//            FancyDrop.fancyDrop.getLogger().info("怪物死亡: "+deathUUID);
//            event.getDrops().forEach(itemStack -> {
//                Item item = (Item) deathEntity.getWorld().spawnEntity((deathEntity).getLocation(), EntityType.DROPPED_ITEM);
//                item.setItemStack(itemStack);
//                item.setOwner(killerUUID);
//            });
//            event.getDrops().clear();
//        }
//    }
//    @EventHandler(priority = EventPriority.LOW)//當撿起物品
//    public void onPickup (EntityPickupItemEvent event){
//        LivingEntity pickEntity = event.getEntity();
//
//        Item item = event.getItem();
//        //item.setOwner(test);
//        item.getOwner();
//        FancyDrop.fancyDrop.getLogger().info("撿取人:"+pickEntity.getUniqueId()+" 物品主人: "+item.getOwner());
//    }

}
