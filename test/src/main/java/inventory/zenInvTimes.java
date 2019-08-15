package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import test.Main;
import test.ZenInv;

public class zenInvTimes extends ZenInv {
		
	public zenInvTimes(Main m)
	{
		super("Time's Manager", Material.TORCH, m);
		this.addItem(new ItemStack(Material.ANVIL), 2 * 9 + 1, "day", Sound.BLAZE_BREATH);
		this.addItem(new ItemStack(Material.ENCHANTMENT_TABLE), 2 * 9 + 2, "night", Sound.BLAZE_HIT);	
	}
	
	public boolean doDay(Player p)
	{
		Bukkit.getLogger().info("DAY OMG");
		Bukkit.broadcastMessage("DAY OMG");
		p.getWorld().setTime(12000L);
		return true;
	}
	
	public boolean doNight(Player p)
	{
		Bukkit.getLogger().info("NIGHT OMG");
		Bukkit.broadcastMessage("NIGHT OMG");
		p.getWorld().setTime(0L);
		return true;
	}
}
