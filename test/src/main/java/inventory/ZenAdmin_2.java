package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import test.Main;
import test.ZenInv;

public class ZenAdmin_2 extends ZenInv {
		
	public ZenAdmin_2(Main m)
	{
		super("Admin Manager #2", Material.BEDROCK, m);
		this.addItem(new ItemStack(Material.DIAMOND), 2 * 9 + 1, "one");
		this.load();

	}
	
	public void load()
	{
		ItemStack nxt = new ItemStack(Material.APPLE);
		this.initNext(nxt, "Admin Manager #3");
		ItemStack prv = new ItemStack(Material.GOLDEN_APPLE);
		this.initPrev(prv, "Admin Manager");
	}
	
	public boolean doOne(Player p)
	{
		Bukkit.getLogger().info("ONE @2 OMG");
		Bukkit.broadcastMessage("ONE @2 OMG");
		
		return true;
	}
}
