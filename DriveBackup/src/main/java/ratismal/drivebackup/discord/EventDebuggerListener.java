package ratismal.drivebackup.discord;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Simple internal listener to verify AutoBackup events are being delivered to the Bukkit event system.
 */
public class EventDebuggerListener implements Listener {

    @EventHandler
    public void onAutoBackupStart(AutoBackupStartEvent event) {
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupStartEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onAutoBackupEnd(AutoBackupEndEvent event) {
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupEndEvent -> " + event.getClass().getName() + " success=" + event.getSuccess());
    }
}

