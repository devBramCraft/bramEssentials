package bramcraft.net.essentials;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsPlugin extends JavaPlugin {

    private Location spawnLocation;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadSpawnLocation();
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("lobby").setExecutor(new SpawnCommand());
        getCommand("hub").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
    }

    private void loadSpawnLocation() {
        if (getConfig().contains("spawn")) {
            spawnLocation = new Location(
                    Bukkit.getWorld(getConfig().getString("spawn.world")),
                    getConfig().getDouble("spawn.x"),
                    getConfig().getDouble("spawn.y"),
                    getConfig().getDouble("spawn.z"),
                    (float) getConfig().getDouble("spawn.yaw"),
                    (float) getConfig().getDouble("spawn.pitch")
            );
        }
    }

    public class SpawnCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player player && spawnLocation != null) {
                player.teleport(spawnLocation);
                player.sendMessage("§aTeleported to spawn.");
            } else {
                sender.sendMessage("§cSpawn not set.");
            }
            return true;
        }
    }

    public class SetSpawnCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player player) {
                Location loc = player.getLocation();
                getConfig().set("spawn.world", loc.getWorld().getName());
                getConfig().set("spawn.x", loc.getX());
                getConfig().set("spawn.y", loc.getY());
                getConfig().set("spawn.z", loc.getZ());
                getConfig().set("spawn.yaw", loc.getYaw());
                getConfig().set("spawn.pitch", loc.getPitch());
                saveConfig();
                loadSpawnLocation();
                player.sendMessage("§aSpawn location set!");
            }
            return true;
        }
    }
}
