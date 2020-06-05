package me.notsmatch.tracktablebot.service

import java.io.File

class TrackService {

    companion object {
        // ベビパなし
        val TRACKS = mapOf(
            "bc" to "https://i.imgur.com/WbI9mW3.jpg",
            "bdd" to "https://i.imgur.com/A696OK7.jpg",
            "cc" to "https://i.imgur.com/cToHr4h.jpg",
            "dac" to "https://i.imgur.com/4tdK23x.jpg",
            "dbb" to "https://i.imgur.com/S1TDy2w.jpg",
            "dcl" to "https://i.imgur.com/nShXQSp.jpg",
            "ddd" to "https://i.imgur.com/kKjR74o.jpg",
            "dea" to "https://i.imgur.com/ko98pNL.jpg",
            "dhc" to "https://i.imgur.com/OPW4Nkp.jpg",
            "diio" to "https://i.imgur.com/emCKoDa.jpg",
            "dmc" to "https://i.imgur.com/fCmVSk9.jpg",
            "dnbc" to "https://i.imgur.com/CtxKxEt.jpg",
            "drir" to "https://i.imgur.com/knfQM1z.jpg",
            "drr" to "https://i.imgur.com/byppy0F.jpg",
            "dsbs" to "https://i.imgur.com/n9xwWR7.jpg",
            "dwgm" to "https://i.imgur.com/pQIYovp.jpg",
            "dww" to "https://i.imgur.com/OM1ldyy.jpg",
            "ed" to "https://i.imgur.com/QDUDXNy.jpg",
            "mc" to "https://i.imgur.com/nGmLPtI.jpg",
            "mks" to "https://i.imgur.com/VEBONKG.jpg",
            "mw" to "https://i.imgur.com/AG0HZ4V.jpg",
            "ds" to "https://i.imgur.com/65ySpBs.jpg",
            "rccb" to "https://i.imgur.com/pxFgRhG.jpg",
            "rddd" to "https://i.imgur.com/yLaXjB0.jpg",
            "rdkj" to "https://i.imgur.com/CG8dAad.jpg",
            "rdp3" to "https://i.imgur.com/fLFIbbo.jpg",
            "rgv" to "https://i.imgur.com/o563lzW.jpg",
            "rmc" to "https://i.imgur.com/3VjSpUB.jpgv",
            "rmmm" to "https://i.imgur.com/jb0SrMK.jpg",
            "rmp" to "https://i.imgur.com/wRdR3Sw.jpg",
            "rpps" to "https://i.imgur.com/CqwfEWo.jpg",
            "rr" to "https://i.imgur.com/TCDJa0K.jpg",
            "rrrd" to "https://i.imgur.com/oohb1ma.jpg",
            "rrry" to "https://i.imgur.com/FmLf8Y7.jpg",
            "rsl" to "https://i.imgur.com/E2yY1EP.jpg",
            "rtt" to "https://i.imgur.com/EYMrdTu.jpg",
            "rttc" to "https://i.imgur.com/bN4kPhl.jpg",
            "rws" to "https://i.imgur.com/mAtfUIH.jpg",
            "dyc" to "https://i.imgur.com/28CzaPW.jpg",
            "ryv" to "https://i.imgur.com/u8JeRij.jpg",
            "sa" to "https://i.imgur.com/ANQX5L4.jpg",
            "sgf" to "https://i.imgur.com/5BwVD16.jpg",
            "ssc" to "https://i.imgur.com/POjFeqX.jpg",
            "th" to "https://i.imgur.com/PmyzOOC.jpg",
            "tm" to "https://i.imgur.com/9mDJxdr.jpg",
            "tr" to "https://i.imgur.com/NnEmX8H.jpg",
            "wp" to "https://i.imgur.com/F53Gclg.jpg"
        )

        val TRACK_LIST_FILE = "https://i.imgur.com/7fkIkcA.jpg"
    }

    fun isExists(track: String) : Boolean{
        return TRACKS.contains(track.toLowerCase())
    }
}