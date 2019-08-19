package test;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.InventoryView;

import give.ZenAmountGive;
import give.ZenGivePlayerList;
import inventory.ZenAdmin;
import inventory.ZenAdmin_2;
import inventory.ZenAdmin_3;
import inventory.ZenGive;
import inventory.ZenPlayerList;
import inventory.zenInvTimes;

public class ZenInvManager{
	
	private Main		plugin;
	private List<ZenInv>	inventorys = new ArrayList<ZenInv>();

	private ZenInvManagerTimers	timer;
	
	public ZenInvManager(Main m)
	{
		this.setPlugin(m);
	}
	
	public void load()
	{
		this.inventorys.add(new zenInvTimes(this.getPlugin()));
		this.inventorys.add(new ZenGive(this.getPlugin()));
		this.inventorys.add(new ZenAdmin(this.getPlugin()));
		this.inventorys.add(new ZenAdmin_2(this.getPlugin()));
		this.inventorys.add(new ZenAdmin_3(this.getPlugin()));
		this.inventorys.add(new ZenPlayerList(this.getPlugin()));
		this.inventorys.add(new ZenAmountGive(this.getPlugin(), "Gold", Material.GOLD_INGOT));
		this.inventorys.add(new ZenGivePlayerList(this.getPlugin(), "Gold", Material.GOLD_INGOT));
		this.setTimer(new ZenInvManagerTimers(this.getPlugin()));
		this.getTimer().start();
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
	


	public List<ZenInv> getInventorys() {
		return inventorys;
	}

	public void setInventorys(List<ZenInv> inventorys) {
		this.inventorys = inventorys;
	}

	public ZenInv getZenInv(Material m)
	{
		for (ZenInv inv : this.getInventorys())
		{
			if (inv.getOpen().equals(m))
				return inv;
		}
		return null;
	}
	
	public ZenInv getZenInv(String m)
	{
		for (ZenInv inv : this.getInventorys())
		{
			if (inv.getName().equalsIgnoreCase(m))
				return inv;
		}
		return null;
	}

	public boolean isZenInv(InventoryView openInventory) {
		for (ZenInv inv : this.getInventorys())
		{
			if (inv.getName().equalsIgnoreCase(openInventory.getTitle()))
				return true;
		}
		return false;
	}

	public ZenInvManagerTimers getTimer() {
		return timer;
	}

	public void setTimer(ZenInvManagerTimers timer) {
		this.timer = timer;
	}
}
