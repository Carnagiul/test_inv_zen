package test;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cmds.ZenCommandAdminTimeDay;
import cmds.ZenCommandAdminTimeNight;
import cmds.ZenCommandAdminWeatherClear;
import cmds.ZenCommandsManager;
import events.ZenEventJoin;

public class Main extends JavaPlugin{
	
	private Listenners listen;

	private int scheduleSyncRepeatingTask;

	
	private ZenCommandsManager		cmdManager;
	private ZenEventJoin			joinEvent;
	
	
	private ZenInvManager	zenInvManager;
	
	public void onEnable()
	{
		this.setListen(new Listenners(this));
		this.setZenInvManager(new ZenInvManager(this));
		this.getZenInvManager().load();
		Bukkit.getPluginManager().registerEvents(this.getListen(), this);

		this.setCmdManager(new ZenCommandsManager(this));
		this.setJoinEvent(new ZenEventJoin(this));
		getCommand("zen").setExecutor(this.getCmdManager());
		this.loadCommands();
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
	
	public void loadCommands() {
		List<String>	args = new ArrayList<String>();
		List<String>	args2 = new ArrayList<String>();
		List<String>	args3 = new ArrayList<String>();
		args.add("admin");
		args.add("time");
		args.add("day");
		new ZenCommandAdminTimeDay(this, args);
		
		args2.add("admin");
		args2.add("time");
		args2.add("night");
		new ZenCommandAdminTimeNight(this, args2);
		
		args3.add("admin");
		args3.add("weather");
		args3.add("clear");
		new ZenCommandAdminWeatherClear(this, args3);
	}

	public ZenCommandsManager getCmdManager() {
		return cmdManager;
	}


	public void setCmdManager(ZenCommandsManager cmdManager) {
		this.cmdManager = cmdManager;
	}


	public ZenEventJoin getJoinEvent() {
		return joinEvent;
	}


	public void setJoinEvent(ZenEventJoin joinEvent) {
		this.joinEvent = joinEvent;
	}
	
}
