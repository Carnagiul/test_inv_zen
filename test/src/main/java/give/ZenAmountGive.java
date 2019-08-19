package give;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import test.Main;
import test.ZenInv;

public class ZenAmountGive extends ZenInv {
	
	private int				amount = 1;
	private Material		gived;
	private String			itemName;
	
	public ZenAmountGive(Main m, String item, Material gived)
	{
		super("Give "+item+" Amount", Material.BEDROCK, m);
		this.setItemName(item);
		this.setGived(gived);
		initRows();
		this.load();
	}
	
	public void load()
	{
		this.getInv().setItem(3 * 9 + 4, new ItemStack(this.getGived(), this.getAmount()));
		this.generateRemove10();
		this.generateRemove5();
		this.generateRemove1();
		this.generateAdd10();
		this.generateAdd5();
		this.generateAdd1();
		ItemStack nxt = new ItemStack(Material.APPLE);
		this.initNext(nxt, "Give " + this.getItemName() + " Player List");
	}
	
	public boolean updateInv(Player p, int update)
	{
		this.setAmount(this.getAmount() + update);
		if (this.getAmount() < 1)
			this.setAmount(1);
		if (this.getAmount() > 64)
			this.setAmount(64);
		this.getInv().setItem(3 * 9 + 4, new ItemStack(this.getGived(), this.getAmount()));
		return false;
	}
	
	public boolean doRemove10(Player p)
	{
		return updateInv(p, -10);
	}
	
	public boolean doRemove5(Player p)
	{
		return updateInv(p, -5);
	}
	
	public boolean doRemove1(Player p)
	{
		return updateInv(p, -1);
	}
	
	public boolean doAdd10(Player p)
	{
		return updateInv(p, 10);
	}
	
	public boolean doAdd5(Player p)
	{
		return updateInv(p, 5);
	}
	
	public boolean doAdd1(Player p)
	{
		return updateInv(p, 1);
	}

	public void generateRemove10()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), -10);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Retirer 10");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 1, "remove10", Sound.CREEPER_DEATH);
	}

	public void generateRemove5()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), -5);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Retirer 5");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 2, "remove5", Sound.CREEPER_DEATH);
	}
	
	public void generateRemove1()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), -1);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Retirer 1");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 3, "remove1", Sound.CREEPER_DEATH);
	}

	public void generateAdd10()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), 10);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Ajouter 10");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 7, "add10", Sound.CREEPER_DEATH);
	}

	public void generateAdd5()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), 5);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Ajouter 5");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 6, "add5", Sound.CREEPER_DEATH);
	}
	
	public void generateAdd1()
	{
		ItemStack remove10 = new ItemStack(this.getGived(), 1);
		ItemMeta metaremove10 = remove10.getItemMeta();
		metaremove10.setDisplayName("Ajouter 1");
		remove10.setItemMeta(metaremove10);
		this.addItem(remove10, 3 * 9 + 5, "add1", Sound.CREEPER_DEATH);
	}
	
	public void initRows()
	{
		this.getInv().clear();
		this.firstGen();
		this.getInvoke().clear();
		this.getInvokeSound().clear();
	
		ItemStack refresh = new ItemStack(Material.PAPER);
		ItemMeta meta = refresh.getItemMeta();
		meta.setDisplayName("Reset");
		refresh.setItemMeta(meta);
		this.addItem(refresh, 9 + 4, "reset", Sound.SHOOT_ARROW);
	}
	
	public boolean doReset(Player p)
	{
		this.setAmount(1);
		initRows();
		this.load();
		return false;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Material getGived() {
		return gived;
	}

	public void setGived(Material gived) {
		this.gived = gived;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
