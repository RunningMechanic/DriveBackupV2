package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when an automatic backup is attempted (initiator == null) before checks like backupsRequirePlayers.
 * This lets other plugins or alerts know a backup attempt occurred even if it was aborted.
 */
public class AutoBackupAttemptEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public AutoBackupAttemptEvent() {
        super();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

