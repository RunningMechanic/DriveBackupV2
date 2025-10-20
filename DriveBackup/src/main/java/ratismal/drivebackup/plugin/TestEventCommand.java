package ratismal.drivebackup.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ratismal.drivebackup.discord.AutoBackupEndEvent;
import ratismal.drivebackup.discord.AutoBackupStartEvent;
import ratismal.drivebackup.discord.AutoBackupAttemptEvent;

public class TestEventCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /dbv2event <start|end|attempt> [success]");
            return true;
        }
        String sub = args[0].toLowerCase();
        if (sub.equals("start")) {
            Bukkit.getPluginManager().callEvent(new AutoBackupStartEvent());
            sender.sendMessage("Fired AutoBackupStartEvent");
            return true;
        } else if (sub.equals("end")) {
            boolean success = true;
            if (args.length >= 2) {
                success = Boolean.parseBoolean(args[1]);
            }
            Bukkit.getPluginManager().callEvent(new AutoBackupEndEvent(success));
            sender.sendMessage("Fired AutoBackupEndEvent success=" + success);
            return true;
        } else if (sub.equals("attempt")) {
            Bukkit.getPluginManager().callEvent(new AutoBackupAttemptEvent());
            sender.sendMessage("Fired AutoBackupAttemptEvent (simulated skip)");
            return true;
        } else {
            sender.sendMessage("Unknown subcommand: " + sub);
            return true;
        }
    }
}
