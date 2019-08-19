package cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import test.Main;

public class ZenCommandAdminTimeNight extends ZenCommands {
	
	public ZenCommandAdminTimeNight(Main plugin, List<String> args)
	{	
		super(plugin, "zen", args, "night", "zen.command.admin.time.night");
	}
	
	public boolean doCommandNight(Player p, String[] args)
	{
		 p.getWorld().setTime(22000L);
		 return true;
	}
	
	public boolean doConsoleCommandNight(CommandSender sender, String[] args)
	{
		for (World w : Bukkit.getWorlds())
			w.setTime(22000L);
		 return true;
	}
}
