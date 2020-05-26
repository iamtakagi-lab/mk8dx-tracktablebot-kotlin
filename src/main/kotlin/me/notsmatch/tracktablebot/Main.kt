package me.notsmatch.tracktablebot

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Bot(System.getenv("TRACKTABLEBOT_TOKEN")).start()
    }
}