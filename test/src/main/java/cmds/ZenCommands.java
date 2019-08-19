package cmds;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import test.Main;

public class ZenCommands {

	private String			label;
	private List<String>	args = new ArrayList<String>();
	private String			exec;
	private Main			plugin;
	private String			permission;
	private Sound			sound;
	
	public ZenCommands(Main plugin, String label, List<String> args, String exec, String permission)
	{
		this.setLabel(label);
		this.setArgs(args);
		this.setExec(exec);
		this.setPlugin(plugin);
		this.setPermission(permission);
		this.setSound(null);
		this.getPlugin().getCmdManager().getCmds().add(this);
	}
	
	public ZenCommands(Main plugin, String label, List<String> args, String exec, String permission, Sound sound)
	{
		this.setLabel(label);
		this.setArgs(args);
		this.setExec(exec);
		this.setPlugin(plugin);
		this.setPermission(permission);
		this.setSound(sound);
		this.getPlugin().getCmdManager().getCmds().add(this);
	}
	
	@SuppressWarnings("unused")
	public boolean doContinue(String label, String[] args)
	{
		if (!label.equalsIgnoreCase(this.getLabel()))
			return true;
		if (args.length != this.getArgs().size())
			return true;
		int i = 0;
		for (String tmp : this.getArgs())
		{
			String tested = args[i++];
			if (tmp.equalsIgnoreCase("{{PLAYER_NAME}}"))
				return false;
			else if (!tested.equalsIgnoreCase(tmp))
				return true;
		}
		Class<?> clazz = this.getClass();
		while (clazz != null) {
		    Method[] methods = clazz.getDeclaredMethods();
		    for (Method method : methods) {
		        // Test any other things about it beyond the name...
		        if (method.getName().equals("doCommand" + WordUtils.capitalize(this.getExec())))
		        {
					try {
						return false;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		    clazz = clazz.getSuperclass();
		}
		if (clazz != null)
			System.out.println(ChatColor.DARK_RED + "ERREUR : Command execution not found for " + this.getExec() + " in " + clazz.getName());
		return true;
	}

	
	@SuppressWarnings("unused")
	public boolean doContinueConsole(String label, String[] args)
	{
		if (!label.equalsIgnoreCase(this.getLabel()))
			return true;
		if (args.length != this.getArgs().size())
			return true;
		int i = 0;
		for (String tmp : this.getArgs())
		{
			String tested = args[i++];
			if (tmp.equalsIgnoreCase("{{PLAYER_NAME}}"))
				return false;
			else if (!tested.equalsIgnoreCase(tmp))
				return true;
		}
		Class<?> clazz = this.getClass();
		while (clazz != null) {
		    Method[] methods = clazz.getDeclaredMethods();
		    for (Method method : methods) {
		        // Test any other things about it beyond the name...
		        if (method.getName().equals("doConsoleCommand" + WordUtils.capitalize(this.getExec())))
		        {
					try {
						return false;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		    clazz = clazz.getSuperclass();
		}
		if (clazz != null)
			System.out.println(ChatColor.DARK_RED + "ERREUR : Command execution not found for " + this.getExec() + " in " + clazz.getName());
		return true;
	}
	
	public boolean execute(Player p, Command command, String label, String[] args)
	{
		if (p.hasPermission(this.getPermission()))
		{
			Class<?> clazz = this.getClass();
			while (clazz != null) {
			    Method[] methods = clazz.getDeclaredMethods();
			    for (Method method : methods) {
			        // Test any other things about it beyond the name...
			        if (method.getName().equals("doCommand" + WordUtils.capitalize(this.getExec())))
			        {
						if (p != null)
							System.out.println("FUCK PLAYER IS NOT NULL");
						if (args != null)
							System.out.println("FUCK ARGS ARE NOT NULL");
						Object[] send = new Object[]{p, args};
						if (send != null)
							System.out.println("FUCK OBJECTS ARE NOT NULL");
						if (method != null)
							System.out.println("FUCK METHOD IS NOT NULL");
						if (this.getSound() != null)
							System.out.println("FUCK SOUND IS NOT NULL");
						try {
							if (this.getSound() != null)
								p.playSound(p.getLocation(), this.getSound(), 1, 1);
							return (Boolean) method.invoke(this, send);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    }
			    clazz = clazz.getSuperclass();
			}
		}
		else
			p.sendMessage("Vous ne possdez pas la perm");
		return false;
	}

	public boolean executeConsole(CommandSender sender, Command command, String label, String[] args) {
		Class<?> clazz = this.getClass();
		while (clazz != null) {
		    Method[] methods = clazz.getDeclaredMethods();
		    for (Method method : methods) {
		        // Test any other things about it beyond the name...
		        if (method.getName().equals("doConsoleCommand" + WordUtils.capitalize(this.getExec())))
		        {
					Object[] send = new Object[]{sender, args};
					try {
						return (Boolean) method.invoke(this, send);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		    clazz = clazz.getSuperclass();
		}
		
		return false;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getExec() {
		return exec;
	}

	public void setExec(String exec) {
		this.exec = exec;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}
