package me.notsmatch.tracktablebot.service

import java.io.File

class TrackService {

    companion object {
        // ベビパなし

        val TRACKS = arrayOf(
            "bc", "bdd", "cc", "dac",
            "dbb", "dcl", "ddd", "dea",
            "dhc", "diio", "dmc", "dnbc",
            "drir", "drr", "dsbs", "dwgm",
            "dww", "ed", "mc", "mks",
            "mw", "ps", "rccb", "rddd",
            "rdkj", "rdp3", "rgv", "rmc",
            "rmmm", "rmp", "rpps", "rr",
            "rrrd", "rrry", "rsl", "rtt",
            "rttc", "rws", "dyc", "ryv",
            "sa", "sgf", "ssc", "th",
            "tm", "tr", "wp")

        val TRACK_LIST_FILE = File("/root/trackreosouces/list.jpg")
    }

    fun isExists(track: String) : Boolean{
        return TRACKS.contains(track.toLowerCase())
    }

    fun getTrackFile(track: String) : File {
        return File(File("/root/trackreosouces/tracks/${track.toLowerCase()}.jpg").path)
    }
}