package yourpackage;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements CommandExecutor {

    private final BedWars plugin;
    public enum State { LOBBY, RUNNING, END }
    private State state = State.LOBBY;

    public List<Player> players = new ArrayList<>();
    public TeamManager teamManager;
    public GeneratorManager generatorManager;

    public GameManager(BedWars plugin) {
        this.plugin = plugin;
        this.teamManager = new TeamManager();
        this.generatorManager = new GeneratorManager();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return false;

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("start")) {
            startGame();
            return true;
        }

        return false;
    }

    public void startGame() {
        if (state != State.LOBBY) return;

        players.addAll(Bukkit.getOnlinePlayers());
        teamManager.assignTeams(players);

        for (Player p : players) {
            p.teleport(teamManager.getSpawn(p));
            p.sendTitle("§aBedWars", "§fFight!", 10, 40, 10);
        }

        generatorManager.startGenerators();
        state = State.RUNNING;
    }

    public boolean isRunning() {
        return state == State.RUNNING;
    }
}
