package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when a manual backup starts (triggered by a player/command).
 */
public class ManualBackupStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public ManualBackupStartEvent() {
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

