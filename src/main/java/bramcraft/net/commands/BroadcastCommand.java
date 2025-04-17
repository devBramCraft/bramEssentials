package bramcraft.net.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Gebruik: /broadcast <bericht>");
            return true;
        }

        String message = String.join(" ", args);
        String coloredMessage = ChatColor.translateAlternateColorCodes('&', "&6&l[BROADCAST] &r" + message);

        Bukkit.broadcastMessage(coloredMessage);
        return true;
    }
}
