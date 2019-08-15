package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import test.Main;
import test.ZenInv;

public class ZenAdmin_3 extends ZenInv {
		
	public ZenAdmin_3(Main m)
	{
		super("Admin Manager #3", Material.BEDROCK, m);
		this.addItem(new ItemStack(Material.DIAMOND), 2 * 9 + 1, "one");
		this.load();

	}
	
	public void load()
	{
		ItemStack prv = new ItemStack(Material.GOLDEN_APPLE);
		this.initPrev(prv, "Admin Manager #2");
	}
	
	public boolean doOne(Player p)
	{
		Bukkit.getLogger().info("ONE @3 OMG");
		Bukkit.broadcastMessage("ONE @3 OMG");
		return true;
	}
}
