package pl.overr.backup.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.overr.backup.backup.Backup;
import pl.overr.backup.data.DataManager;
import pl.overr.backup.utils.TimeFormatUtil;
import java.util.Arrays;
import java.util.Collection;

public class BackupGUI{

    private final DataManager dataManager;

    public BackupGUI(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    public Inventory createInventory(Player player){
        String playerName = player.getName();
        Inventory inventory = Bukkit.createInventory(null,36, playerName);
        Collection<Backup> backupCollection = dataManager.getBackup(playerName);

        backupCollection.forEach(backup -> {
            ItemStack itemStack = new ItemStack(Material.GRASS);
            ItemMeta itemMeta = itemStack.getItemMeta();

            String killer = backup.getKiller();
            Long data = backup.getData();
            int ping = backup.getPing();

            String actualTime = TimeFormatUtil.timeFormater(data);

            itemMeta.setDisplayName(actualTime);
            itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Gracz: " + ChatColor.GREEN + playerName, ChatColor.RED + "Zabojca: " + ChatColor.GREEN + killer, ChatColor.RED + "Ping: " + ChatColor.GREEN + ping));
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
        });
        return inventory;
    }
}
