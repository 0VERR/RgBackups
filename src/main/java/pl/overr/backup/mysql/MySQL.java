package pl.overr.backup.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    private final HikariConfig hikariConfig;
    private final HikariDataSource hikariDataSource;

    private final static String HOST = "localhost";
    private final static int PORT = 3306;
    private final static String database = "localhost";
    private final static String username = "localhost";
    private final static String password = "localhost";

    public MySQL(){
        hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + database);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public void createTable(){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS rgbackup( "+
                            "id int(16) PRIMARY KEY AUTO_INCREMENT,"+
                            "playerName VARCHAR(36) NOT NULL,"+
                            "data BIGINT(22) NOT NULL,"+
                            "killer VARCHAR (36) NOT NULL,"+
                            "ping int(16) NOT NULL,"+
                            "items TEXT,"+
                            "armor TEXT)");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection(){
        try {
            return this.hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
