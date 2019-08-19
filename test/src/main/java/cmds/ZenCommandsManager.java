package cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import test.Main;

public class ZenCommandsManager implements CommandExecutor {

	private Main						plugin;
	private List<ZenCommands>			cmds = new ArrayList<ZenCommands>();
	
	public ZenCommandsManager(Main plugin) {
		this.setPlugin(plugin);
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player send = (Player)sender;
			for (ZenCommands cmd : cmds)
			{
				if (cmd.doContinue(label, args) == false)
				{
					System.out.println("C'est bien cette commande la " + cmd.getExec());
					return cmd.execute(send, command, label, args);
				}
				else
				{
					System.out.println("Ce n'es pas cette commande la " + cmd.getExec());
				}
			}
			System.out.println("Fin des commands Zen");
		}
		else
		{
			for (ZenCommands cmd : cmds)
			{
				if (cmd.doContinueConsole(label, args) == false)
				{
					return cmd.executeConsole(sender, command, label, args);
				}
			}
		}
		return false;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public List<ZenCommands> getCmds() {
		return cmds;
	}

	public void setCmds(List<ZenCommands> cmds) {
		this.cmds = cmds;
	}
}
