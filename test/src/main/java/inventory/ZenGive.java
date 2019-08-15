package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import test.Main;
import test.ZenInv;

public class ZenGive extends ZenInv {
		
	public ZenGive(Main m)
	{
		//cc
		super("Give Manager", Material.REDSTONE_TORCH_ON, m);
		this.addItem(new ItemStack(Material.DIAMOND), 2 * 9 + 1, "diamond");
		this.addItem(new ItemStack(Material.GOLD_INGOT), 2 * 9 + 2, "gold");	
	}
	
	public boolean doDiamond(Player p)
	{
		Bukkit.getLogger().info("DU DIAMS OMG");
		Bukkit.broadcastMessage("DU DIAMS OMG");
		return true;
	}
	
	public boolean doGold(Player p)
	{
		Bukkit.getLogger().info("DU GOLD OMG");
		Bukkit.broadcastMessage("DU GOLD OMG");
		return true;
	}
}
