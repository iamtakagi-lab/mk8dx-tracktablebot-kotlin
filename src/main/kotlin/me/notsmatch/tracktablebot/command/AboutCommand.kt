package me.notsmatch.tracktablebot.command

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.commons.JDAUtilitiesInfo
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import com.jagrosh.jdautilities.examples.doc.Author
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDAInfo
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Guild
import org.slf4j.LoggerFactory
import java.awt.Color

@CommandInfo(name = ["ttbotabout"], description = "Gets information about the bot.")
@Author("notsmatch")
class AboutCommand (
    private val color: Color,
    val githubUrl: String,
    vararg perms: Permission
) :
    Command() {
    private var IS_AUTHOR = true
    private var REPLACEMENT_ICON = "+"
    private val perms: Array<Permission>
    private var oauthLink: String? = null
    fun setIsAuthor(value: Boolean) {
        IS_AUTHOR = value
    }

    fun setReplacementCharacter(value: String) {
        REPLACEMENT_ICON = value
    }

    override fun execute(event: CommandEvent) {
        if (oauthLink == null) {
            oauthLink = try {
                val info = event.jda.retrieveApplicationInfo().complete()
                if (info.isBotPublic) info.getInviteUrl(0L, *perms) else ""
            } catch (e: Exception) {
                val log = LoggerFactory.getLogger("OAuth2")
                log.error("Could not generate invite link ", e)
                ""
            }
        }
        val builder = EmbedBuilder()
        builder.setColor(if (event.guild == null) color else event.guild.selfMember.color)
        builder.setAuthor("All about " + event.selfUser.name + "!", null, event.selfUser.avatarUrl)
        val join =
            !(event.client.serverInvite == null || event.client.serverInvite.isEmpty())
        val inv = !oauthLink!!.isEmpty()
        val invline = ((if (join) "Join my server [`here`](" + event.client
            .serverInvite + ")" else if (inv) "Please " else "")
                + (if (inv) (if (join) ", or " else "") + "[`invite`](" + oauthLink + ") me to your server" else "") + "!")
        val descr =
            StringBuilder().append("Hello! I am **").append(event.selfUser.name).append("**")
                .append("\nType `").append(event.client.textualPrefix)
                .append(event.client.helpWord)
                .append("` to see my commands! ").append(if (join || inv) invline else "")
                .append("\nGithub: ${githubUrl}")
        builder.setDescription(descr)
        if (event.jda.shardInfo == null) {
            builder.addField(
                "Stats", """${event.jda.guilds.size} servers
1 shard""", true
            )
            builder.addField(
                "Users",
                """${event.jda.users.size} unique
""" + event.jda.guilds.stream().mapToInt { g: Guild -> g.members.size }.sum() + " total",
                true
            )
            builder.addField(
                "Channels",
                """${event.jda.textChannels.size} Text
""" + event.jda
                    .voiceChannels.size + " Voice",
                true
            )
        } else {
            builder.addField(
                "Stats",
                """${event.client.totalGuilds} Servers
Shard """ + (event.jda.shardInfo
                    .shardId + 1)
                        + "/" + event.jda.shardInfo.shardTotal,
                true
            )
            builder.addField(
                "This shard",
                """${event.jda.users.size} Users
${event.jda.guilds.size} Servers""",
                true
            )
            builder.addField(
                "",
                """${event.jda.textChannels.size} Text Channels
""" + event.jda
                    .voiceChannels.size + " Voice Channels",
                true
            )
        }
        builder.setFooter("Last restart", null)
        builder.setTimestamp(event.client.startTime)
        event.reply(builder.build())
    }

    init {
        name = "ttbotabout"
        help = "shows info about the bot"
        guildOnly = false
        this.perms = perms as Array<Permission>
        botPermissions =
            arrayOf(Permission.MESSAGE_EMBED_LINKS)
    }
}