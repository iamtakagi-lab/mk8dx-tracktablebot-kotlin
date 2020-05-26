package me.notsmatch.tracktablebot.command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import me.notsmatch.tracktablebot.service.TrackService
import net.dv8tion.jda.api.EmbedBuilder
import org.apache.commons.lang3.StringUtils
import java.awt.Color
import java.io.File

class TrackTableCommand(val trackService: TrackService) : Command() {

    init {
        this.name = "tt"
        this.help = "テーブル画像を表示します"
        this.arguments = "<track>"
    }

    override fun execute(event: CommandEvent?) {
        event?.apply {
            val args = StringUtils.split(args)

            if (args.isEmpty()) {
                return reply(EmbedBuilder().apply {
                    setColor(Color.RED)
                    setAuthor(
                        "Error",
                        null,
                        null
                    )
                    setDescription("``_tt <track>``")
                }.build())
            }

            if(!trackService.isExists(args[0])){
                return reply(EmbedBuilder().apply {
                    setColor(Color.RED)
                    setAuthor(
                        "Error",
                        null,
                        null
                    )
                    setDescription("そのTrackは存在しません\n``_tl``で全てのTrackを表示")
                }.build())
            }

            channel.sendFile(trackService.getTrackFile(args[0])).queue()
        }
    }
}