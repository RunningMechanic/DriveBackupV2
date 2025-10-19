package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a manual backup is attempted but not started.
 */
public class ManualBackupAttemptEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public ManualBackupAttemptEvent() {
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

