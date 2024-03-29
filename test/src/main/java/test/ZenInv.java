package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ZenInv {
	
	private String			name;
	private	String			next = null;
	private String			previous = null;
	private Inventory		inv;
	private Player[]		openers;
	private Material		open;
	public static short		color_id = 0;
	public static short		max_color_id = 15;
	public static int		row = 6;
	public static int		size = 9 * row; 
	public static Material	material = Material.STAINED_GLASS_PANE;
	public static Material	close = Material.SLIME_BALL;
	public static Sound		close_sound = Sound.SLIME_WALK;
	public static Sound		previous_sound = Sound.ANVIL_USE;
	public static Sound		next_sound = Sound.BAT_HURT;
	
	private HashMap<ItemStack, String>	invoke = new HashMap<ItemStack, String>();
	private HashMap<ItemStack, Sound>	invokeSound = new HashMap<ItemStack, Sound>();
	private HashMap<ItemStack, String>	invokePermission = new HashMap<ItemStack, String>();
	
	
	private Main			plugin;
	
	
	public ZenInv(String name, Material open, Main m)
	{
		this.setName(name);
		this.setOpen(open);
		this.setPlugin(m);
		this.createInv(Bukkit.createInventory(null, size, this.getName()));
	}
	
	public void addItem(ItemStack item, int position, String invoke)
	{
		this.getInv().setItem(position, item);
		this.getInvoke().put(item, invoke);
	}
	
	public void addItem(ItemStack item, int position, String invoke, Sound sound)
	{
		this.getInv().setItem(position, item);
		this.getInvoke().put(item, invoke);
		this.getInvokeSound().put(item, sound);
	}
	
	public void addItem(ItemStack item, int position, String invoke, Sound sound, String perm)
	{
		this.getInv().setItem(position, item);
		this.getInvoke().put(item, invoke);
		this.getInvokeSound().put(item, sound);
		this.getInvokePermission().put(item, perm);
	}
	
	public void addItemWithPerm(ItemStack item, int position, String invoke, String perm) {
		this.addItem(item, position, invoke);
		this.getInvokePermission().put(item, perm);
	}
	
	public void initNext(ItemStack item, String next)
	{
		this.setNext(next);
		ItemStack nxt = item.clone();
		ItemMeta meta = nxt.getItemMeta();
		meta.setDisplayName("Page suivante");
		nxt.setItemMeta(meta);
		this.addItem(nxt, (row - 1) * 9 + 8, "next", next_sound);
	}
	
	public void initPrev(ItemStack item, String prev)
	{
		this.setPrevious(prev);
		ItemStack prv = item.clone();
		ItemMeta meta = prv.getItemMeta();
		meta.setDisplayName("Page d'avant");
		prv.setItemMeta(meta);
		
		this.addItem(prv, (row - 1) * 9, "previous", previous_sound);

	}
	
	public boolean doNext(Player p)
	{
		p.closeInventory();
		if (this.getNext() != "")
		{
			ZenInv inv = this.getPlugin().getZenInvManager().getZenInv(this.getNext());
			if (inv != null)
			{
				p.openInventory(inv.getInv());
				return false;
			}
		}
		return true;
	}
	
	public boolean doPrevious(Player p)
	{
		p.closeInventory();
		if (this.getPrevious() != "")
		{
			ZenInv inv = this.getPlugin().getZenInvManager().getZenInv(this.getPrevious());
			if (inv != null)
			{
				p.openInventory(inv.getInv());
				return false;
			}
		}
		return true;
	}
	
	public boolean doError(Player p)
	{
		p.sendMessage("Vous ne possedez pas la permission pour effectuer cette action...");
		return false;
	}
	
	public boolean hud(ItemStack item, Player p)
	{
		if (this.getInvoke().containsKey(item) && this.getInv().contains(item))
		{			
			Class<?> clazz = this.getClass();
			while (clazz != null) {
			    Method[] methods = clazz.getDeclaredMethods();
			    for (Method method : methods) {
			        // Test any other things about it beyond the name...
			        if (method.getName().equals("do" + WordUtils.capitalize(this.getInvoke().get(item))))
			        {
			        	boolean perm_ok = false;
			        	if (this.getInvokePermission().containsKey(item)) {
			        		if (p.hasPermission(this.getInvokePermission().get(item)))
			        			perm_ok = true;
			        		else
			        			perm_ok = false;
			        	}
			        	else
			        		perm_ok = true;
			        	if (perm_ok == false)
			        		return this.doError(p);
						try {
							if (this.getInvokeSound().containsKey(item))
								p.playSound(p.getLocation(), this.getInvokeSound().get(item), 1, 1);
							return (Boolean) method.invoke(this, new Object[]{p});
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			        else if (method.getName().equals("exec" + WordUtils.capitalize(this.getInvoke().get(item))))
			        {
			        	boolean perm_ok = false;
			        	if (this.getInvokePermission().containsKey(item)) {
			        		if (p.hasPermission(this.getInvokePermission().get(item)))
			        			perm_ok = true;
			        		else
			        			perm_ok = false;
			        	}
			        	else
			        		perm_ok = true;
			        	if (perm_ok == false)
			        		return this.doError(p);
						try {
							if (this.getInvokeSound().containsKey(item))
								p.playSound(p.getLocation(), this.getInvokeSound().get(item), 1, 1);
							return (Boolean) method.invoke(this, new Object[]{p, item});
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    }
			    clazz = clazz.getSuperclass();
			}
			Bukkit.getLogger().info("Method not Found");
		}
		else
		{
			Bukkit.getLogger().info("Item not found");
		}
		return false;
	}
	
	public void updateInv()
	{
			int pos = 0;
			for (ItemStack i : inv.getContents())
			{
				if (i != null)
				{
					ItemStack item = new ItemStack(material, 1, color_id++);
					if (color_id > max_color_id)
						color_id = 0;
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("ZenGames.fr");
					List<String> Lore = new ArrayList<String>();
					Lore.add(ChatColor.GOLD + "IP : "+ChatColor.GRAY+"zengames.fr");
					Lore.add(ChatColor.GOLD + "Mumble : "+ChatColor.GRAY+"mumble.zengames.fr:64738");
					meta.setLore(Lore);
					item.setItemMeta(meta);
					if (i.getType().equals(item.getType()))
					{
						inv.setItem(pos, item);
					}
				}
				pos++;
			}
	}

	protected void firstGen()
	{
		ItemStack item = new ItemStack(material, 1, color_id++);

		if (color_id > max_color_id)
			color_id = 0;
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("ZenGames.fr");
		List<String> Lore = new ArrayList<String>();
		Lore.add(ChatColor.GOLD + "IP : "+ChatColor.GRAY+"zengames.fr");
		Lore.add(ChatColor.GOLD + "Mumble : "+ChatColor.GRAY+"mumble.zengames.fr:64738");
		meta.setLore(Lore);
		item.setItemMeta(meta);
		for (int i = 0; i < size; i++)
		{
			if (i < 9)
				inv.setItem(i, item);
			if (i > size - 9)
				inv.setItem(i, item);
			if (i % 9 == 0)
				inv.setItem(i, item);
			if (i % 9 == 8)
				inv.setItem(i, item);/*
			if (i == (9 * 1 + 1) || i == (9 * 1 + 7))
				inv.setItem(i, item);
			if (i == (9 * (row - 2) + 1) || i == (9 * (row - 2) + 7))
				inv.setItem(i, item);*/
		}
		
		ItemStack close = new ItemStack(getClose());
		meta = close.getItemMeta();
		meta.setDisplayName("Fermer le menu");
		close.setItemMeta(meta);
		inv.setItem((row - 1) * 9 + 4, close);
	}
	
	private void createInv(Inventory createInventory) {
		this.setInv(createInventory);
		this.firstGen();
		updateInv();
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public Player[] getOpeners() {
		return openers;
	}

	public void setOpeners(Player[] openers) {
		this.openers = openers;
	}

	public static Material getClose() {
		return close;
	}

	public static void setClose(Material close) {
		ZenInv.close = close;
	}

	public HashMap<ItemStack, String> getInvoke() {
		return invoke;
	}

	public void setInvoke(HashMap<ItemStack, String> invoke) {
		this.invoke = invoke;
	}

	public Material getOpen() {
		return open;
	}

	public void setOpen(Material open) {
		this.open = open;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public HashMap<ItemStack, Sound> getInvokeSound() {
		return invokeSound;
	}

	public void setInvokeSound(HashMap<ItemStack, Sound> invokeSound) {
		this.invokeSound = invokeSound;
	}

	public HashMap<ItemStack, String> getInvokePermission() {
		return invokePermission;
	}

	public void setInvokePermission(HashMap<ItemStack, String> invokePermission) {
		this.invokePermission = invokePermission;
	}
}
