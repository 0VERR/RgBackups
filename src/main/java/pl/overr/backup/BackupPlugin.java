package pl.overr.backup;

import org.bukkit.plugin.java.JavaPlugin;
import pl.overr.backup.backup.BackupLoader;
import pl.overr.backup.commands.BackupCommand;
import pl.overr.backup.data.DataManager;
import pl.overr.backup.inventory.BackupGUI;
import pl.overr.backup.listeners.InventoryClickListener;
import pl.overr.backup.listeners.PlayerDeathListener;
import pl.overr.backup.mysql.MySQL;

public class BackupPlugin extends JavaPlugin {

    public static BackupPlugin getShopPlugin(){
        return backupPlugin;
    }
    static BackupPlugin backupPlugin;

    public void onEnable(){
        backupPlugin = this;
        MySQL mySQL = new MySQL();
        mySQL.createTable();
        DataManager dataManager = new DataManager();
        BackupGUI backupGUI = new BackupGUI(dataManager);
        BackupLoader backupLoader = new BackupLoader(mySQL, dataManager);

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(dataManager, backupLoader),this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(dataManager), this);
        getCommand("backup").setExecutor(new BackupCommand(backupGUI));
        backupLoader.getBackup();
    }
}
