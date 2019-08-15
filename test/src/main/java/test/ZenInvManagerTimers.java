package test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ZenInvManagerTimers extends BukkitRunnable {

	private Main plugin;
	private boolean start;
	private BukkitTask id;

	public ZenInvManagerTimers(Main m)
	{
		this.setPlugin(m);
		this.setStart(false);
	}
	
	public void start()
	{
		this.setStart(true);
		this.setId(this.runTaskTimer(this.getPlugin(), 20l, 20l));
	}
	
	public void stop()
	{
		this.cancel();
		this.setStart(false);
	}
	
	public void run() {
		if (!start)
			return ;
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (p.getOpenInventory() != null)
			{
				for (ZenInv inv : this.getPlugin().getZenInvManager().getInventorys())
				{
					if (p.getOpenInventory().getTitle().equalsIgnoreCase(inv.getName()))
					{
						inv.updateInv();
						break ;
					}
				}
			}
		}		
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public BukkitTask getId() {
		return id;
	}

	public void setId(BukkitTask id) {
		this.id = id;
	}

}
