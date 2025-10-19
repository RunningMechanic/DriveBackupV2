package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a manual backup ends (triggered by a player/command). Contains success flag.
 */
public class ManualBackupEndEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final boolean success;

    public ManualBackupEndEvent(boolean success) {
        super();
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean getSuccess() { return success; }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

