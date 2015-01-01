package com.nt96q.antipunch.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.nt96q.antipunch.AntiPunch;

public class DamageListener implements Listener {
	
	AntiPunch plugin;
	
	public DamageListener(AntiPunch plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player victim = (Player) event.getEntity();
		Player attacker = this.getAttacker(event.getDamager());
		
		if (attacker == null) {
			return;
		}
		
		if (!victim.equals(attacker)) {
			return;
		}
		
		if (!attacker.getItemInHand().containsEnchantment(Enchantment.ARROW_KNOCKBACK)) {
			return;
		}
		
		event.setCancelled(true);
		return;
	}
	
	@SuppressWarnings("deprecation")
	public Player getAttacker(Entity entity) {
		if (entity instanceof Player) {
			return (Player) entity;
		} else if (entity instanceof Projectile) {
			Projectile projectile = (Projectile) entity;
			if (!(projectile.getShooter() instanceof Player)) {
				return null;
			} else {
				return (Player) projectile.getShooter();
			}
		}
		return null;
	}

}
