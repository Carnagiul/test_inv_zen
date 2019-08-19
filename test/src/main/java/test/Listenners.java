package test;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listenners implements Listener
{
	private Main main;

	public Listenners(Main m)
	{
		this.setMain(m);
	}
	
	@EventHandler
	public void join(PlayerJoinEvent ev)
	{
		ev.getPlayer().getInventory().clear();
		for (ZenInv inv : this.getMain().getZenInvManager().getInventorys())
		{
			if (inv.getOpen() != Material.BEDROCK)
				ev.getPlayer().getInventory().addItem(new ItemStack(inv.getOpen()));
		}
	}
	
	@EventHandler
	public void place(BlockPlaceEvent ev)
	{
		ev.setCancelled(true);
	}
	
	@EventHandler
	public void place(BlockBreakEvent ev)
	{
		ev.setCancelled(true);
	}
	
	public boolean HUD(Player p, ItemStack item)
	{
		ZenInv inv = this.getMain().getZenInvManager().getZenInv(item.getType());
		if (inv != null)
		{
			if (p.getOpenInventory().getType() != this.getMain().getZenInvManager().getInventorys().get(0).getInv().getType())
				p.openInventory(inv.getInv());
			else
			{
				if (this.getMain().getZenInvManager().isZenInv(p.getOpenInventory()))
				{
					ZenInv invName = this.getMain().getZenInvManager().getZenInv(p.getOpenInventory().getTitle());

					if (ZenInv.close.equals(item.getType()))
					{
						p.closeInventory();
						p.playSound(p.getLocation(), ZenInv.close_sound, 1, 1);
					}
					else if (invName != null)
					{					
						if (invName.hud(item, p))
						{
							p.closeInventory();
						}
					}
				}
			}
		}
		else if (p.getOpenInventory() != null)
		{
			if (this.getMain().getZenInvManager().isZenInv(p.getOpenInventory()))
			{
				ZenInv invName = this.getMain().getZenInvManager().getZenInv(p.getOpenInventory().getTitle());

				if (ZenInv.close.equals(item.getType()))
				{
					p.closeInventory();
					p.playSound(p.getLocation(), ZenInv.close_sound, 1, 1);
				}
				else if (invName != null)
				{					
					if (invName.hud(item, p))
					{
						p.closeInventory();
					}
				}
			}
		}
		return true;
	}
	
	@EventHandler
	public void drag(InventoryClickEvent ev)
	{
		if (ev.getWhoClicked() instanceof Player)
		{
			ev.setCancelled(HUD((Player)ev.getWhoClicked(), ev.getCurrentItem()));
		}
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent ev)
	{
		if (ev.getPlayer().getItemInHand() != null)
		{
			ev.setCancelled(HUD(ev.getPlayer(), ev.getPlayer().getItemInHand()));
		}
	}
	

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
}
