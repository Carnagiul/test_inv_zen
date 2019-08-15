package test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private Listenners listen;

	private int scheduleSyncRepeatingTask;
	
	
	
	private ZenInvManager	zenInvManager;
	
	public void onEnable()
	{
		this.setListen(new Listenners(this));
		this.setZenInvManager(new ZenInvManager(this));
		Bukkit.getPluginManager().registerEvents(this.getListen(), this);
	}

	public Listenners getListen() {
		return listen;
	}

	public void setListen(Listenners listen) {
		this.listen = listen;
	}

	public int getScheduleSyncRepeatingTask() {
		return scheduleSyncRepeatingTask;
	}

	public void setScheduleSyncRepeatingTask(int scheduleSyncRepeatingTask) {
		this.scheduleSyncRepeatingTask = scheduleSyncRepeatingTask;
	}

	public ZenInvManager getZenInvManager() {
		return zenInvManager;
	}

	public void setZenInvManager(ZenInvManager zenInvManager) {
		this.zenInvManager = zenInvManager;
	}
	
}
