package yourpackage;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidListener implements Listener {

    private final GameManager gm;

    public VoidListener(GameManager gm) {
        this.gm = gm;
    }

    @EventHandler
    public void onVoid(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getY() > 0) return;

        if (gm.teamManager.isBedAlive(p)) {
            p.teleport(gm.teamManager.getSpawn(p));
            p.sendMessage("§eYou respawned.");
        } else {
            p.setHealth(0);
            p.sendMessage("§cYou were eliminated!");
        }
    }
}
