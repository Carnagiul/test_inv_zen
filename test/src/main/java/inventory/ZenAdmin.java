package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import test.Main;
import test.ZenInv;

public class ZenAdmin extends ZenInv {
		
	public ZenAdmin(Main m)
	{
		super("Admin Manager", Material.GLOWSTONE, m);
		this.addItem(new ItemStack(Material.DIAMOND), 2 * 9 + 1, "one");
		this.load();
	}
	
	public void load()
	{
		ItemStack nxt = new ItemStack(Material.APPLE);
		this.initNext(nxt, "Admin Manager #2");
	}
	
	public boolean doOne(Player p)
	{
		Bukkit.getLogger().info("ONE OMG");
		Bukkit.broadcastMessage("ONE OMG");
		return true ;
	}
}
