package pl.overr.backup.backup;

import org.bukkit.inventory.ItemStack;

public class Backup implements Comparable<Backup> {
    private String playerName;
    private Long data;
    private String killer;
    private int ping;



    public String getPlayerName() {
        return playerName;
    }

    public Long getData() {
        return data;
    }

    public String getKiller() {
        return killer;
    }

    public int getPing() {
        return ping;
    }


    public ItemStack[] getInventoryContest() {
        return inventoryContest;
    }

    public void setInventoryContest(ItemStack[] inventoryContest) {
        this.inventoryContest = inventoryContest;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    private ItemStack[] inventoryContest;
    private ItemStack[] armor;



    public Backup(String playerName, long data, String killer, int ping, ItemStack[] inventoryContest, ItemStack[] itemStacks){
        this.playerName = playerName;
        this.data = data;
        this.killer = killer;
        this.ping = ping;
        this.inventoryContest = inventoryContest;
        this.armor = itemStacks;
    }


    @Override
    public int compareTo(Backup o) {
        return -(getData().compareTo(o.getData()));
    }
}
