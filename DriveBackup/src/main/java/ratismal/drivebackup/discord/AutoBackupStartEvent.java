package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when an automatic backup starts (not triggered by a player).
 * Can be used by other plugins (or DiscordSRV alerts) to react to auto backups.
 */
public class AutoBackupStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public AutoBackupStartEvent() {
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

