package events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import test.Main;

public class ZenEventJoin extends modes.ZenMode {

	public ZenEventJoin(Main plugin) {
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void event(PlayerJoinEvent ev)
	{
		ev.setJoinMessage("[GAME] : " + ev.getPlayer().getName() + "");
	}
}
