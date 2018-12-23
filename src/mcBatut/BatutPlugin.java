package mcBatut;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BatutPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @EventHandler
    public void isPlayerStepped(PlayerInteractEvent ev) {
        if (!ev.getAction().equals(Action.PHYSICAL)) return;
        Block steppedBlock = ev.getClickedBlock();
        // Check if it is an Stone PLATE
        if (!steppedBlock.getType().equals(Material.STONE_PLATE)) {
            return;
        }

        // Jump Power
        double power = 0;

        // Get info under this block
        Location belowLocation = steppedBlock.getLocation().add(0, -1, 0);
        Player player = ev.getPlayer();
        World playersWorld = player.getWorld();
        Block blockBelow = playersWorld.getBlockAt(belowLocation);

        // Check if there is Obsidian Plate & Block
        if (ObsidianBoost.isPlateAndObsidianBelow(ev)) {
            ObsidianBoost.boostPlayer(player);
            ev.setCancelled(true);
            return;
        }

        // Power depends on BLOCK TYPE
        if (blockBelow.getType().equals(Material.IRON_BLOCK)) {
            power = 0.5;
        } else if (blockBelow.getType().equals(Material.GOLD_BLOCK)) {
            power = 1;
        } else if (blockBelow.getType().equals(Material.DIAMOND_BLOCK)) {
            power = 1.5;
        }

        // Jump if power is here :)
        if (power > 0) {
            player.playSound(belowLocation, Sound.BLOCK_SLIME_FALL, 1, 1);
            player.setVelocity(new Vector(0, power, 0));
            ev.setCancelled(true);
        }
    }
}
