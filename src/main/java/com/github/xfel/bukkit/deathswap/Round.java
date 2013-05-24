package com.github.xfel.bukkit.deathswap;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Round extends BukkitRunnable implements Listener {

	public static final PotionEffect RESISTANCE_EFFECT = new PotionEffect(
			PotionEffectType.DAMAGE_RESISTANCE, 20, Integer.MAX_VALUE);

	private static final float SWAP_PROBABILITY_PER_TICK = 0.3f;

	/**
	 * Will swap the two player's location.
	 * 
	 * @param p1
	 * @param p2
	 */
	public static void swapPlayers(Player p1, Player p2) {

		Location p1loc = p1.getLocation();

		Location p2loc = p2.getLocation();

		p1.addPotionEffect(RESISTANCE_EFFECT, true);
		p2.addPotionEffect(RESISTANCE_EFFECT, true);

		p1.teleport(p2loc);
		p2.teleport(p1loc);
	}

	private Player p1;

	private Player p2;

	private DeathSwapGame plugin;

	private Random rand = new Random();

	/**
	 * @param plugin
	 * @param p1
	 * @param p2
	 */
	public Round(DeathSwapGame plugin, Player p1, Player p2) {
		this.plugin = plugin;
		this.p1 = p1;
		this.p2 = p2;

		init();
	}

	@Override
	public void run() {

		if (rand.nextFloat() <= SWAP_PROBABILITY_PER_TICK) {
			swapPlayers(p1, p2);
			scheduleNextSwap();
		}

	}

	/**
	 * Called once on startup
	 */
	protected void init() {
		// TODO spieler zu den startpositionen porten, etc.

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * clean up
	 */
	protected void dispose(){
		cancel();
		
		EntityDeathEvent.getHandlerList().unregister(this);
	}

	/**
	 * wird direkt nach dem start oder direkt nach einem swap aufgerufen.
	 */
	protected void scheduleNextSwap() {
		runTaskLater(plugin, 400);// in 20 sec
	}

	/**
	 * Called if an entity dies. Will determine the winner if it was one of the
	 * players.
	 * 
	 * @param event
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().equals(p1)) {
			// TODO p2 won
			
			dispose();
		} else if (event.getEntity().equals(p2)) {
			// TODO p1 won

			dispose();
		}
	}

}
