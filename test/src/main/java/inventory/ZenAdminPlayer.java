package inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import test.Main;
import test.ZenInv;

public class ZenAdminPlayer extends ZenInv {
	
	private String				player;
	
	public ZenAdminPlayer(Main m, String player)
	{
		super("Admin Player " + player, Material.BEDROCK, m);
		this.setPlayer(player);
		this.load();
	}
	
	public void playerTp()
	{
		ItemStack stack = new ItemStack(Material.BANNER,1);
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Teleport to " + this.getPlayer());
		stack.setItemMeta(im);
		this.addItem(stack, 2 * 9 + 1, "tp", Sound.ENDERMAN_TELEPORT, "zen.game.admin.player.tp");
	}
	
	public void playerKill()
	{
		ItemStack stack = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Kill " + this.getPlayer());
		stack.setItemMeta(im);
		this.addItem(stack, 2 * 9 + 2, "kill", Sound.VILLAGER_DEATH, "zen.game.admin.player.kill");
	}
	
	public void playerHeal()
	{
		ItemStack stack = new ItemStack(Material.GOLDEN_APPLE,1);
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Heal " + this.getPlayer());
		stack.setItemMeta(im);
		this.addItem(stack, 2 * 9 + 3, "heal", Sound.HORSE_GALLOP, "zen.game.admin.player.heal");
	}
	
	public void playerFeed()
	{
		ItemStack stack = new ItemStack(Material.COOKED_BEEF,1);
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Feed " + this.getPlayer());
		stack.setItemMeta(im);
		this.addItem(stack, 2 * 9 + 4, "feed", Sound.FIRE_IGNITE, "zen.game.admin.player.feed");
	}
	
	@SuppressWarnings("deprecation")
	public boolean doFeed(Player p)
	{
		Player test = Bukkit.getPlayer(this.getPlayer());
		if (test.isOnline())
		{
			test.setSaturation(20);
			test.setFoodLevel(20);
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean doHeal(Player p)
	{
		Player test = Bukkit.getPlayer(this.getPlayer());
		if (test.isOnline())
			test.setHealth(test.getMaxHealth());
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean doKill(Player p)
	{
		Player test = Bukkit.getPlayer(this.getPlayer());
		if (test.isOnline())
		{
			if (test.getGameMode().equals(GameMode.SURVIVAL))
			{
				test.setHealth(1);
				test.damage(200);
			}
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean doTp(Player p)
	{
		Player test = Bukkit.getPlayer(this.getPlayer());
		if (test.isOnline())
			p.teleport(test.getPlayer().getLocation());
		return true;
	}
	
	public void load()
	{
		this.playerTp();
		this.playerKill();
		this.playerHeal();
		this.playerFeed();
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
}
