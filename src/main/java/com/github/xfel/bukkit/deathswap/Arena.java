package com.github.xfel.bukkit.deathswap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
		
		
		p1.teleport(p2loc);
		p2.teleport(p1loc);
	}
	
	private Player p1;
	
	private Player p2;
	
	
}
