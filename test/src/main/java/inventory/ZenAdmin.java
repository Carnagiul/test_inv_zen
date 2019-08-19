package inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import give.ZenMenuPlayerList;
import test.Main;
import test.ZenInv;

public class ZenAdmin extends ZenInv {
		
	public ZenAdmin(Main m)
	{
		super("Admin Manager", Material.NETHER_STAR, m);
		this.load();
		this.getPlugin().getZenInvManager().getInventorys().add(new ZenMenuPlayerList(this.getPlugin(), "Game", Material.BEDROCK));
	}
	
	public void playerList()
	{
		ItemStack stack = new ItemStack(Material.SKULL_ITEM,1 , (byte)3);
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Admin Game Player List");
		stack.setItemMeta(im);
		this.addItem(stack, 2 * 9 + 1, "alp", Sound.COW_HURT, "zen.game.admin.list.player");
	}
	
	public boolean doAlp(Player p)
	{
		//Admin List Player
		ZenMenuPlayerList inv = (ZenMenuPlayerList) this.getPlugin().getZenInvManager().getZenInv("Admin Game Player List");
		p.openInventory(inv.getInv());
		return false;
	}
	
	public void load()
	{
		this.playerList();
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
