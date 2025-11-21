package yourpackage;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager {

    public enum BWTeam {
        RED(ChatColor.RED),
        BLUE(ChatColor.BLUE),
        GREEN(ChatColor.GREEN),
        YELLOW(ChatColor.YELLOW);

        public final ChatColor color;
        BWTeam(ChatColor color) { this.color = color; }
    }

    private final Map<Player,BWTeam> playerTeams = new HashMap<>();
    private final Map<BWTeam, Boolean> bedAlive = new HashMap<>();

    public TeamManager() {
        for (BWTeam t : BWTeam.values()) {
            bedAlive.put(t, true);
        }
    }

    public void assignTeams(List<Player> players) {
        int i = 0;
        for (Player p : players) {
            BWTeam team = BWTeam.values()[i % BWTeam.values().length];
            playerTeams.put(p, team);
            p.sendMessage("You joined team " + team.color + team.name());
            i++;
        }
    }

    public void destroyBed(BWTeam team) {
        bedAlive.put(team, false);
    }

    public boolean isBedAlive(Player p) {
        return bedAlive.get(playerTeams.get(p));
    }

    public Location getSpawn(Player p) {
        return switch (playerTeams.get(p)) {
            case RED -> new Location(Bukkit.getWorld("world"), 10, 60, 0);
            case BLUE -> new Location(Bukkit.getWorld("world"), -10, 60, 0);
            case GREEN -> new Location(Bukkit.getWorld("world"), 0, 60, 10);
            case YELLOW -> new Location(Bukkit.getWorld("world"), 0, 60, -10);
        };
    }
}
