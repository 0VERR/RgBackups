package pl.overr.backup.backup;

import pl.overr.backup.data.DataManager;
import pl.overr.backup.mysql.MySQL;
import pl.overr.backup.utils.ItemStackUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BackupLoader {
    private final MySQL mySQL;
    private final DataManager dataManager;

    public BackupLoader(MySQL mySQL, DataManager dataManager) {
        this.mySQL = mySQL;
        this.dataManager = dataManager;
    }

    public void getBackup(){
        try (Connection connection = mySQL.getConnection()){
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM rgbackup").executeQuery();
            while (resultSet.next()){
                dataManager.addBackup(new Backup(resultSet.getString("playerName"), resultSet.getLong("data"),
                        resultSet.getString("killer"), resultSet.getInt("ping"), ItemStackUtil.itemStackFromString(resultSet.getString("items")), ItemStackUtil.itemStackFromString(resultSet.getString("armor"))));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertBackup(Backup backup){
        try (Connection connection = mySQL.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO rgbackup VALUES (?,?,?,?,?,?,?)");
            statement.setInt(1, 0);
            statement.setString(2, backup.getPlayerName());
            statement.setLong(3, backup.getData());
            statement.setString(4, backup.getKiller());
            statement.setInt(5, backup.getPing());
            statement.setString(6, ItemStackUtil.itemStackToString(backup.getInventoryContest()));
            statement.setString(7, ItemStackUtil.itemStackToString(backup.getArmor()));
            statement.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
