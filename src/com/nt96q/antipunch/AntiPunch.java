package com.nt96q.antipunch;

import org.bukkit.plugin.java.JavaPlugin;

import com.nt96q.antipunch.listeners.DamageListener;

public class AntiPunch extends JavaPlugin {
	
	DamageListener damageListener;
	
	public void onEnable() {
		this.damageListener = new DamageListener(this);
	}

}
