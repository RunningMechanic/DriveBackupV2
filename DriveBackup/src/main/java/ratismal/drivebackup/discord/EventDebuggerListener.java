package ratismal.drivebackup.discord;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple internal listener to verify AutoBackup events are being delivered to the Bukkit event system.
 */
public class EventDebuggerListener implements Listener {

    // 時刻を記録して、直後の End イベントの成功メッセージを抑制する
    private static final AtomicLong lastAutoAttemptTime = new AtomicLong(0);
    // スキップから成功メッセージを抑制するウィンドウ（ミリ秒）
    private static final long SUPPRESS_WINDOW_MS = 5000;

    public EventDebuggerListener() {
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener constructed and ready to receive events");
    }

    @EventHandler
    public void onAutoBackupAttempt(AutoBackupAttemptEvent event) {
        try {
            // 設定から警告色（オレンジ）を取得
            Color color = Color.orange;
            DiscordController.sendEmbed("backup","Auto backup skipped — no players online.", color);
            // スキップ発生時刻を記録
            lastAutoAttemptTime.set(System.currentTimeMillis());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send attempt embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupAttemptEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onAutoBackupStart(AutoBackupStartEvent event) {
        try {
            DiscordController.sendEmbed("backup","Scheduled backup started.", Color.BLUE);
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send start embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupStartEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onAutoBackupEnd(AutoBackupEndEvent event) {
        try {
            // 終了は成功なら設定の成功色、失敗なら設定の失敗色
            if (event.getSuccess()) {
                // 直近にスキップが起きていれば成功メッセージを抑制する
                long last = lastAutoAttemptTime.get();
                if (last > 0 && (System.currentTimeMillis() - last) < SUPPRESS_WINDOW_MS) {
                    Bukkit.getLogger().info("[DriveBackupV2] Suppressing AutoBackupEnd success embed due to recent AutoBackupAttemptEvent (skip)");
                    // 抑制したらタイムスタンプをクリア
                    lastAutoAttemptTime.set(0);
                } else {
                    DiscordController.sendEmbed("backup","Scheduled backup finished successfully.", Color.GREEN);
                }
            } else {
                DiscordController.sendEmbed("backup","Scheduled backup finished with errors.", Color.RED);
            }
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send end embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupEndEvent -> " + event.getClass().getName() + " success=" + event.getSuccess());
    }

    // Manual backup handlers
    @EventHandler
    public void onManualBackupAttempt(ManualBackupAttemptEvent event) {
        try {
            // 設定から手動中止色（オレンジ）を取得
            DiscordController.sendEmbed("backup","Manual backup attempted but aborted.", Color.ORANGE);
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send manual attempt embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received ManualBackupAttemptEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onManualBackupStart(ManualBackupStartEvent event) {
        try {
            // 手動開始は青
            DiscordController.sendEmbed("backup","Manual backup started.", Color.BLUE);
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send manual start embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received ManualBackupStartEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onManualBackupEnd(ManualBackupEndEvent event) {
        try {
            // 手動終了は成功なら緑、失敗なら赤
            if (event.getSuccess()) {
                DiscordController.sendEmbed("backup","Manual backup finished (success).", new Color(39, 174, 96));
            } else {
                DiscordController.sendEmbed("backup","Manual backup finished (failed).", Color.RED);
            }
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send manual end embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received ManualBackupEndEvent -> " + event.getClass().getName() + " success=" + event.getSuccess());
    }
}
