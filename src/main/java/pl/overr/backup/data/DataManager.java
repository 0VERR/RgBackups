package pl.overr.backup.data;

import com.google.common.collect.TreeMultimap;
import pl.overr.backup.backup.Backup;

import java.util.Collection;
import java.util.Collections;

public class DataManager {

    TreeMultimap<String, Backup> backupMap = TreeMultimap.create();

    public void addBackup(Backup backup){
        backupMap.put(backup.getPlayerName(), backup);
    }

    public Collection<Backup> getBackup(String playerName){
        return Collections.unmodifiableCollection(backupMap.get(playerName));
    }

    public TreeMultimap<String, Backup> getBackup(){
        return backupMap;
    }



    public void insertBackup(Backup backup){

    }
}
