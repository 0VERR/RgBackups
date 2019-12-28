package pl.overr.backup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.overr.backup.inventory.BackupGUI;

public class BackupCommand implements CommandExecutor {

    private final BackupGUI backupGUI;

    public BackupCommand(BackupGUI backupGUI) {
        this.backupGUI = backupGUI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        player.openInventory(backupGUI.createInventory(player));
        return true;
    }
}
