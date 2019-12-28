package pl.overr.backup.listeners;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import pl.overr.backup.backup.Backup;
import pl.overr.backup.backup.BackupLoader;
import pl.overr.backup.data.DataManager;

public class PlayerDeathListener implements Listener {

    private final DataManager dataManager;
    private final BackupLoader backupLoader;

    public PlayerDeathListener(DataManager dataManager, BackupLoader backupLoader) {
        this.dataManager = dataManager;
        this.backupLoader = backupLoader;
    }
    @EventHandler
    public void onDeathListener(PlayerDeathEvent event){

        Player player = event.getEntity().getPlayer();
        String playerName = player.getName();
        long data = System.currentTimeMillis();
        String killer;


        if (player.getKiller() != null){
            killer = player.getKiller().getName();
        } else {
            killer = ChatColor.GRAY + "Przyczyny naturalne";
        }

        int ping = ((CraftPlayer) player).getHandle().ping;
        ItemStack[] inventory = player.getInventory().getContents();
        ItemStack[] armor = player.getInventory().getArmorContents();

        Backup backup = new Backup(playerName,data,killer,ping, inventory, armor);
        dataManager.addBackup(backup);
        event.getEntity().getPlayer().sendMessage("siema");
        backupLoader.insertBackup(backup);
    }
}
