package io.github.mulvaney.ExplodingSwords;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class
 */
public class Plugin extends JavaPlugin {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ExplodingSwords(getLogger()), this);
	}

}
