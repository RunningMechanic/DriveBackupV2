package ratismal.drivebackup.discord;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.Color;
import ratismal.drivebackup.config.ConfigParser;

/**
 * Simple internal listener to verify AutoBackup events are being delivered to the Bukkit event system.
 */
public class EventDebuggerListener implements Listener {

    public EventDebuggerListener() {
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener constructed and ready to receive events");
    }

    @EventHandler
    public void onAutoBackupAttempt(AutoBackupAttemptEvent event) {
        try {
            // 設定から警告色（オレンジ）を取得
            Color color = Color.orange;
            DiscordController.sendEmbed("backup","Auto backup skipped — no players online.", color);
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[DriveBackupV2] EventDebuggerListener failed to send attempt embed: " + ex.getMessage());
            ex.printStackTrace();
        }
        Bukkit.getLogger().info("[DriveBackupV2] EventDebuggerListener received AutoBackupAttemptEvent -> " + event.getClass().getName());
    }

    @EventHandler
    public void onAutoBackupStart(AutoBackupStartEvent event) {
        try {
            // 設定から開始色（明るい緑）を取得
            Color color = Color.green;
            DiscordController.sendEmbed("backup","Scheduled backup started.", color);
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
                DiscordController.sendEmbed("backup","Scheduled backup finished successfully.", Color.green);
            } else {
                DiscordController.sendEmbed("backup","Scheduled backup finished with errors.", Color.red);
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
            DiscordController.sendEmbed("backup","Manual backup attempted but aborted.", Color.orange);
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
