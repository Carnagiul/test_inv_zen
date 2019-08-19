package modes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import test.Main;

public class ZenMode implements Listener {
		
	private List<ZenMode>	modes = new ArrayList<ZenMode>();
	private Main			plugin;
	
	public ZenMode(Main plugin)
	{
		this.setPlugin(plugin);
		Bukkit.getPluginManager().registerEvents(this, this.getPlugin());
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public List<ZenMode> getModes() {
		return modes;
	}

	public void setModes(List<ZenMode> modes) {
		this.modes = modes;
	}
}
