package com.github.xfel.bukkit.deathswap;

import java.util.Collection;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * 
 * backs up a players state and can restore it later
 * 
 * @author Xfel
 * 
 */
public class PlayerStateBackup implements ConfigurationSerializable{

	private Player player;

	private Location oldLocation;

	private ItemStack[] oldInventory;

	private int oldLevel;

	private float oldExp;

	private Collection<PotionEffect> oldPotionEffects;

	private float oldExhaustion;

	private int oldFoodLevel;

	private float oldSaturation;

	private int oldHealth;

	private GameMode oldGameMode;

	public PlayerStateBackup(Map<String, Object> data) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param player
	 */
	public PlayerStateBackup(Player player) {
		this.player = player;

		// backup player state
		oldLocation = player.getLocation();
		oldInventory = player.getInventory().getContents();
		oldLevel = player.getLevel();
		oldExp = player.getExp();
		oldPotionEffects = player.getActivePotionEffects();
		oldExhaustion = player.getExhaustion();
		oldFoodLevel = player.getFoodLevel();
		oldSaturation = player.getSaturation();
		oldHealth = player.getHealth();
		oldGameMode = player.getGameMode();
	}

	/**
	 * restores the player's properties
	 */
	public void restorePlayer() {
		player.teleport(oldLocation);
		player.getInventory().setContents(oldInventory);
		player.setLevel(oldLevel);
		player.setExp(oldExp);

		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
		player.addPotionEffects(oldPotionEffects);

		player.setExhaustion(oldExhaustion);
		player.setFoodLevel(oldFoodLevel);
		player.setSaturation(oldSaturation);
		player.setHealth(oldHealth);
		player.setGameMode(oldGameMode);
	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}
