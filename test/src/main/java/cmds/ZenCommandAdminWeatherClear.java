package cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import test.Main;

public class ZenCommandAdminWeatherClear extends ZenCommands {
	
	public ZenCommandAdminWeatherClear(Main plugin, List<String> args)
	{	
		super(plugin, "zen", args, "clear", "zen.command.admin.weather.clear");
	}
	
	public boolean doCommandClear(Player p, String[] args)
	{
		 p.getWorld().setStorm(false);
		 p.getWorld().setThundering(false);
		 p.getWorld().setWeatherDuration(99999);
		 p.getWorld().setThunderDuration(99999);
		 return true;
	}
	
	public boolean doConsoleCommandClear(CommandSender sender, String[] args)
	{
		for (World w : Bukkit.getWorlds())
		{
			w.setStorm(false);
			w.setThundering(false);
			w.setWeatherDuration(99999);
			w.setThunderDuration(99999);
		}
		return true;
	}
}
