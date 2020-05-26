package me.notsmatch.tracktablebot

import com.jagrosh.jdautilities.command.CommandClientBuilder
import me.notsmatch.tracktablebot.command.TrackTableCommand
import me.notsmatch.tracktablebot.command.TrackListCommand
import me.notsmatch.tracktablebot.service.TrackService
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*


class Bot (private val token: String) {

    companion object {
        @JvmStatic
        lateinit var instance: Bot
    }

    lateinit var jda: JDA

    val trackService = TrackService()

    fun start() {
        instance = this
        jda = JDABuilder(AccountType.BOT).setToken(token).setStatus(OnlineStatus.ONLINE).build()
        val builder = CommandClientBuilder()
        builder.addCommands(TrackTableCommand(trackService), TrackListCommand(trackService))

        builder.setOwnerId("695218967173922866")
        builder.setPrefix("_")

        builder.setHelpWord("ttbot")

        val client = builder.build()
        jda.addEventListener(Listener())
        jda.addEventListener(client)
    }
}

class Listener : ListenerAdapter() {

    override fun onReady(event: ReadyEvent) {
        event.jda.guilds.forEach{guild -> println(guild.name)}

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                event.jda.apply {
                    presence.setPresence(OnlineStatus.ONLINE, Activity.watching("github.com/notsmatch/mk8dx-tracktablebot | ${guilds.size} servers"))
                }
            }
        }, 0, 1000*300)
    }
}