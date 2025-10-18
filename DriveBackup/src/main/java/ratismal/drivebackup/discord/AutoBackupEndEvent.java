package ratismal.drivebackup.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when an automatic backup (not triggered by a player) ends.
 * Contains a success flag indicating whether the backup completed without error.
 */
public class AutoBackupEndEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final boolean success;

    public AutoBackupEndEvent(boolean success) {
        super();
        this.success = success;
    }

    // 既存のわかりやすいメソッド名を残す
    public boolean wasSuccessful() {
        return success;
    }

    // DiscordSRV や他のライブラリがプロパティとして取得しやすい getter 名を追加
    public boolean isSuccess() {
        return success;
    }

    public boolean getSuccess() {
        return success;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
