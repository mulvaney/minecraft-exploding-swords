package io.github.mulvaney.ExplodingSwords;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Map;
import java.util.HashMap;
/**
 * Equip a sword and right click, it will fire exploding arrows. more powerful
 * swords cause bigger explosions.
 * 
 */
public class ExplodingSwords implements Listener {
	private final Logger logger;

	private final Map<Material, Float> swordValues = new HashMap();
	
	public ExplodingSwords(Logger logger) {
		this.logger = logger;		
	
		swordValues.put(Material.WOOD_SWORD,    2F);
		swordValues.put(Material.STONE_SWORD,   4F);
		swordValues.put(Material.GOLD_SWORD,    8F);
		swordValues.put(Material.DIAMOND_SWORD, 16F);
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
	
		if (e.getEntity() instanceof org.bukkit.entity.Arrow) {

			Arrow arrow = (Arrow) e.getEntity();

			ProjectileSource shooter = arrow.getShooter();
			if (shooter instanceof Player) {

				Player player = (Player) shooter;
				Float boom = swordValues.get(player.getItemInHand().getType());
				if (boom != null) {
					Location loc = arrow.getLocation();
					arrow.getWorld().createExplosion(loc, boom, true);
				}
			}
		}
	}

	/**
	 * Fires an arrow out of a Golden Sword right click
	 * 
	 */
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();

		if (swordValues.containsKey(player.getItemInHand().getType())) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				player.launchProjectile(org.bukkit.entity.Arrow.class);
			}
		}
	}
}
