// Controller.java
package ratismal.drivebackup.discord;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

import java.awt.Color;

public class DiscordController {

    /**
     * 指定されたマッピング名のチャンネルにEmbedメッセージを送信します。
     *
     * @param channelMapping DiscordSRVのチャンネルマッピング名 (例: "backup-log" など)
     * @param title Embedのタイトル
     */
    public static void sendEmbed(String channelMapping, String title) {
        try {
            TextChannel channel = DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(channelMapping);

            if (channel == null) {
                Bukkit.getLogger().warning("[Controller] Discordチャンネル '" + channelMapping + "' が見つかりません。");
                return;
            }

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(title)
                    .setColor(Color.CYAN)
                    .setFooter("送信: " + Bukkit.getServer().getName());

            channel.sendMessageEmbeds(embed.build()).queue(
                    success -> Bukkit.getLogger().info("[DriveBackupV2][DiscordController] Discordチャンネル '" + channelMapping + "' にメッセージを送信しました。"),
                    error -> Bukkit.getLogger().warning("[DriveBackupV2][DiscordController] Discordへの送信に失敗しました: " + error.getMessage())
            );

        } catch (Exception e) {
            Bukkit.getLogger().severe("[DriveBackupV2][DiscordController] Discordへの送信中にエラーが発生しました: " + e.getMessage());
        }
    }
}
