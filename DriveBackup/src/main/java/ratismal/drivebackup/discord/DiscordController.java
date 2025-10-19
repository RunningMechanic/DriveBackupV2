package ratismal.drivebackup.discord;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

import java.awt.Color;

public class DiscordController {

    /**
     * 指定されたマッピング名のチャンネルにEmbedメッセージを送信します（デフォルト色）。
     *
     * @param channelMapping DiscordSRVのチャンネルマッピング名
     * @param title Embedのタイトル
     */
    public static void sendEmbed(String channelMapping, String title) {
        sendEmbed(channelMapping, title, Color.CYAN);
    }

    /**
     * 指定されたマッピング名のチャンネルに色付きEmbedメッセージを送信します。
     *
     * @param channelMapping DiscordSRVのチャンネルマッピング名
     * @param title Embedのタイトル
     * @param color Embedの色（java.awt.Color）
     */
    public static void sendEmbed(String channelMapping, String title, Color color) {
        try {
            TextChannel channel = DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(channelMapping);

            if (channel == null) {
                Bukkit.getLogger().warning("[Controller] Discordチャンネル '" + channelMapping + "' が見つかりません。");
                return;
            }

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(title)
                    .setColor(color);

            channel.sendMessageEmbeds(embed.build()).queue(
                    success -> Bukkit.getLogger().info("[DriveBackupV2][DiscordController] Discordチャンネル '" + channelMapping + "' にメッセージを送信しました。"),
                    error -> Bukkit.getLogger().warning("[DriveBackupV2][DiscordController] Discordへの送信に失敗しました: " + error.getMessage())
            );

        } catch (Exception e) {
            Bukkit.getLogger().severe("[DriveBackupV2][DiscordController] Discordへの送信中にエラーが発生しました: " + e.getMessage());
        }
    }
}