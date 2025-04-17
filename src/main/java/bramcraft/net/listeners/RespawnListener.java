package bramcraft.net.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RespawnListener implements Listener {

    private final JavaPlugin plugin;

    public RespawnListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (plugin.getConfig().contains("spawn")) {
            Location spawn = new Location(
                    Bukkit.getWorld(plugin.getConfig().getString("spawn.world")),
                    plugin.getConfig().getDouble("spawn.x"),
                    plugin.getConfig().getDouble("spawn.y"),
                    plugin.getConfig().getDouble("spawn.z"),
                    (float) plugin.getConfig().getDouble("spawn.yaw"),
                    (float) plugin.getConfig().getDouble("spawn.pitch")
            );
            event.setRespawnLocation(spawn);
        }
    }
}
