package mcBatut;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ObsidianBoost {
    public static void boostPlayer(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
        Vector velocity = player.getVelocity();
        Location location = player.getLocation();
        Vector boostedVelocity = new Vector(
                location.getDirection().multiply(10).getX(),
                velocity.getY(),
                location.getDirection().multiply(10).getZ()
        );
        player.setVelocity(boostedVelocity);
    }

    public static boolean isPlateAndObsidianBelow(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block eventBlock = event.getClickedBlock();
        Location belowLocation = eventBlock.getLocation()
                .add(0, -1, 0);
        return belowLocation.getBlock().getType().equals(Material.OBSIDIAN);
    }
}
