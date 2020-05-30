package me.notsmatch.tracktablebot.command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import com.jagrosh.jdautilities.doc.standard.Error
import com.jagrosh.jdautilities.doc.standard.RequiredPermissions
import com.jagrosh.jdautilities.examples.doc.Author
import com.jagrosh.jdautilities.menu.Paginator
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.exceptions.PermissionException
import java.awt.Color
import java.util.concurrent.TimeUnit

@CommandInfo(
    name = ["Guildlist"],
    description = "Gets a paginated list of the guilds the bot is on.",
    requirements = ["The bot has all necessary permissions.", "The user is the bot's owner."]
)
@Error(
    value = "If arguments are provided, but they are not an integer.",
    response = "[PageNumber] is not a valid integer!"
)
@RequiredPermissions(
    Permission.MESSAGE_EMBED_LINKS,
    Permission.MESSAGE_ADD_REACTION
)
@Author("notsmatch")
class GuildlistCommand(waiter: EventWaiter?) : Command() {
    private val pbuilder: Paginator.Builder
    override fun execute(event: CommandEvent) {
        var page = 1
        if (!event.args.isEmpty()) {
            page = try {
                event.args.toInt()
            } catch (e: NumberFormatException) {
                event.reply(event.client.error + " `" + event.args + "` is not a valid integer!")
                return
            }
        }
        pbuilder.clearItems()
        event.jda.guilds.stream()
            .map { g: Guild -> "**" + g.name + "** (ID:" + g.id + ") ~ " + g.members.size + " Members" }
            .forEach { items: String? -> pbuilder.addItems(items) }
        val p = pbuilder.setColor(
            if (event.isFromType(ChannelType.TEXT)) event.selfMember.color else Color.black
        )
            .setText(
                event.client.success + " Guilds that **" + event.selfUser
                    .name + "** is connected to"
                        + if (event.jda.shardInfo == null) ":" else "(Shard ID " + event.jda
                    .shardInfo.shardId + "):"
            )
            .setUsers(event.author)
            .build()
        p.paginate(event.channel, page)
    }

    init {
        name = "ttguildlist"
        help = "shows the list of guilds the bot is on"
        arguments = "[pagenum]"
        botPermissions = arrayOf(
            Permission.MESSAGE_EMBED_LINKS,
            Permission.MESSAGE_ADD_REACTION
        )
        guildOnly = false
        ownerCommand = true
        pbuilder = Paginator.Builder().setColumns(1)
            .setItemsPerPage(10)
            .showPageNumbers(true)
            .waitOnSinglePage(false)
            .useNumberedItems(false)
            .setFinalAction { m: Message ->
                try {
                    m.clearReactions().queue()
                } catch (ex: PermissionException) {
                    m.delete().queue()
                }
            }
            .setEventWaiter(waiter)
            .setTimeout(1, TimeUnit.MINUTES)
    }
}
