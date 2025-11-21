package yourpackage;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;

public class BedListener implements Listener {

    private final GameManager gm;

    public BedListener(GameManager gm) {
        this.gm = gm;
    }

    @EventHandler
    public void onBedBreak(BlockBreakEvent e) {
        if (!gm.isRunning()) return;
        if (!e.getBlock().getType().toString().contains("BED")) return;

        var t = gm.teamManager;
        var victimTeam = t.getTeamFromBed(e.getBlock().getLocation());

        if (victimTeam == null) return;

        t.destroyBed(victimTeam);

        Bukkit.broadcastMessage("§c" + victimTeam.name() + " bed was destroyed!");
    }
}
