package com.github.xfel.bukkit.deathswap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Arena {
	
	/**
	 * Will swap the two player's location.
	 * 
	 * @param p1
	 * @param p2
	 */
	public static void swapPlayers(Player p1, Player p2) {
		
		Location p1loc = p1.getLocation();
		
		Location p2loc = p2.getLocation();
		
		PotionEffect pe = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, Integer.MAX_VALUE);
		
		p1.addPotionEffect(pe,true);
		p2.addPotionEffect(pe,true);
		
		p1.teleport(p2loc);
		p2.teleport(p1loc);
	}
	
	private Player p1;
	
	private Player p2;
	
	
}
