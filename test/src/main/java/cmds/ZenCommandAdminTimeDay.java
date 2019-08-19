package cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import test.Main;

public class ZenCommandAdminTimeDay extends ZenCommands {
	
	public ZenCommandAdminTimeDay(Main plugin, List<String> args)
	{	
		super(plugin, "zen", args, "day", "zen.command.admin.time.day");
	}
	
	public boolean doCommandDay(Player p, String[] args)
	{
		 p.getWorld().setTime(8000L);
		 return true;
	}
	
	public boolean doConsoleCommandDay(CommandSender sender, String[] args)
	{
		for (World w : Bukkit.getWorlds())
			w.setTime(8000L);
		 return true;
	}
}
