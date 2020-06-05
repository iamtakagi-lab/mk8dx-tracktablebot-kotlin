package me.notsmatch.tracktablebot.command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import me.notsmatch.tracktablebot.service.TrackService

class TrackListCommand(val trackService: TrackService) : Command() {

    init {
        this.name = "tl"
        this.help = "全てのTrack名を表示します"
    }

    override fun execute(event: CommandEvent?) {
        event?.apply {
            channel.sendMessage(TrackService.TRACK_LIST_FILE).queue()
        }
    }
}