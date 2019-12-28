package pl.overr.backup.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.overr.backup.data.DataManager;
import pl.overr.backup.utils.TimeFormatUtil;


public class InventoryClickListener implements Listener {

    private final DataManager dataManager;

    public InventoryClickListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onClickInventoryEvent(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        String inventoryName = inventory.getName();
        if (dataManager.getBackup(inventoryName).size() >= 1){
            event.setCancelled(true);
            ItemStack clickedItemStack = event.getCurrentItem();
            String itemData = clickedItemStack.getItemMeta().getDisplayName();
            String playerName = inventory.getName();

            dataManager.getBackup(playerName).forEach(x ->{
                String data = TimeFormatUtil.timeFormater(x.getData());
                if (data.equalsIgnoreCase(itemData)){
                    Player player = Bukkit.getPlayer(playerName);


                    player.getInventory();
                    player.getInventory().setContents(x.getInventoryContest());
                    player.getInventory().setArmorContents(x.getArmor());
                }
            });

        }
    }
}
