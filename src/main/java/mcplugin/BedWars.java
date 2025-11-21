package yourpackage;

import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {

    private static BedWars instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;
        gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BedListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new VoidListener(gameManager), this);

        getCommand("bedwars").setExecutor(gameManager);

        getLogger().info("BedWars Loaded!");
    }

    public static BedWars get() { return instance; }
    public GameManager getGameManager() { return gameManager; }
}
