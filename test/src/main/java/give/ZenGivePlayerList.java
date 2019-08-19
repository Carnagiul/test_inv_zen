package give;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import test.Main;
import test.ZenInv;

public class ZenGivePlayerList extends ZenInv {
	
	private int				id = 1;
	private int				limite = 10; //10
	private int				limite_row = 5; //5
	private int				limite_add = 1; //1
	private int				limite_start = 2;
	private int				limite_end = 3;
	
	private String			item;
	
	public ZenGivePlayerList(Main m, String item, Material gived)
	{
		super("Give " + item + " Player List", Material.BEDROCK, m);
		initRows();
		this.load();
		this.setItem(item);
	}
	
	public void load()
	{
		if (Bukkit.getOnlinePlayers().size() > this.getId() * this.getLimite())
		{
			ItemStack nxt = new ItemStack(Material.APPLE);
			this.initNext(nxt, "Give " + this.getItem() + " Player List");
			this.getInvoke().replace(nxt, "next", "nextrow");
		}
		int skip = (this.getId() - 1) * this.getLimite();
		int place = 0;
		ItemStack item;
		ItemMeta meta;
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (skip <= 0)
			{
				if (place >= this.getLimite())
				{
					break ;
				}
				else
				{
					item = new ItemStack(Material.BOOK);
					meta = item.getItemMeta();
					meta.setDisplayName(p.getName());
					item.setItemMeta(meta);
					if (place > 5)
						this.addItem(item, this.getLimite_end() * 9 + (place % this.getLimite_row()) + this.getLimite_add(), "player");
					else
						this.addItem(item, this.getLimite_start() * 9 + (place % this.getLimite_row()) + this.getLimite_add(), "player");
					place++;
				}
			}
			else
			{
				skip--;
			}
		}
		if (this.getId() > 1)
		{
			ItemStack prv = new ItemStack(Material.GOLDEN_APPLE);
			this.initPrev(prv, "Give " + this.getItem() + " Player List");
			this.getInvoke().replace(prv, "previous", "previousrow");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean execPlayer(Player p, ItemStack item)
	{
		Player other = Bukkit.getPlayer(item.getItemMeta().getDisplayName());
		ZenAmountGive inv = (ZenAmountGive) this.getPlugin().getZenInvManager().getZenInv("Give " + this.getItem() + " Amount");
		other.getInventory().addItem(new ItemStack(inv.getGived(), inv.getAmount()));
		return true;
	}
	
	public boolean doReload(Player p)
	{
		initRows();
		this.load();
		return false;
	}
	
	public void initRows()
	{
		this.getInv().clear();
		this.firstGen();
		this.getInvoke().clear();
		this.getInvokeSound().clear();

		ItemStack refresh = new ItemStack(Material.PAPER);
		ItemMeta meta = refresh.getItemMeta();
		meta.setDisplayName("Refresh");
		refresh.setItemMeta(meta);
		this.addItem(refresh, 9 + 4, "reload", Sound.SHOOT_ARROW);
		
		ItemStack all = new ItemStack(Material.NETHER_STAR);
		ItemMeta allM = all.getItemMeta();
		allM.setDisplayName("Give to All");
		all.setItemMeta(allM);
		this.addItem(all, 9 + 2, "giveall", Sound.SHOOT_ARROW);
	}
	
	public boolean doGiveall(Player p)
	{
		ZenAmountGive inv = (ZenAmountGive) this.getPlugin().getZenInvManager().getZenInv("Give "+ this.getItem() +" Amount");
		for (Player other : Bukkit.getOnlinePlayers())
			other.getInventory().addItem(new ItemStack(inv.getGived(), inv.getAmount()));
		return true;
	}
	
	public boolean doPreviousrow(Player p)
	{
		this.setId(this.getId() - 1);
		initRows();
		this.load();
		return false;
	}
	
	public boolean doNextrow(Player p)
	{
		this.setId(this.getId() + 1);
		initRows();
		this.load();
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getLimite_row() {
		return limite_row;
	}

	public void setLimite_row(int limite_row) {
		this.limite_row = limite_row;
	}

	public int getLimite_add() {
		return limite_add;
	}

	public void setLimite_add(int limite_add) {
		this.limite_add = limite_add;
	}

	public int getLimite_start() {
		return limite_start;
	}

	public void setLimite_start(int limite_start) {
		this.limite_start = limite_start;
	}

	public int getLimite_end() {
		return limite_end;
	}

	public void setLimite_end(int limite_end) {
		this.limite_end = limite_end;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
}

