package me.kodysimpson.stattracker;

import me.kodysimpson.stattracker.db.Database;
import me.kodysimpson.stattracker.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public final class StatTracker extends JavaPlugin {

    private Database database;

    @Override
    public void onEnable() {

        System.out.println("Plugin started...");

        //Config stuff
        saveDefaultConfig();

        //JDBC - Java Database Connectivity API
        this.database = new Database(getConfig().getString("database.host"), getConfig().getString("database.port"), getConfig().getString("database.user"), getConfig().getString("database.password"), getConfig().getString("database.database_name"));
        try {
            this.database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        getServer().getPluginManager().registerEvents(new Listeners(database), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
