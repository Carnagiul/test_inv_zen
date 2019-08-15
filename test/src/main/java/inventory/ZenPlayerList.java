package inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import test.Main;
import test.ZenInv;

public class ZenPlayerList extends ZenInv {
	
	private int				id = 1;
	private int				limite = 1; //10
	private int				limite_row = 1; //5
	private int				limite_add = 4; //1
	private int				limite_start = 2;
	private int				limite_end = 3;
	
	public ZenPlayerList(Main m)
	{
		super("Player List", Material.BANNER, m);
		initRows();
		this.load();
	}
	
	public void load()
	{
		if (Bukkit.getOnlinePlayers().size() > this.getId() * this.getLimite())
		{
			ItemStack nxt = new ItemStack(Material.APPLE);
			this.initNext(nxt, "Player List");
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
			this.initPrev(prv, "Player List");
			this.getInvoke().replace(prv, "previous", "previousrow");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean execPlayer(Player p, ItemStack item)
	{
		if (item.getItemMeta().getDisplayName() != p.getName())
		{
			Player other = Bukkit.getPlayer(item.getItemMeta().getDisplayName());
			p.teleport(other);
		}
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
}
