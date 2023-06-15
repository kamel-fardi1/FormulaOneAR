package com.kamel.fardi.formulaonear.Utils

import android.content.Context
import android.media.MediaPlayer
import com.kamel.fardi.formulaonear.R
class PlayMusic {
    var mMediaPlayer: MediaPlayer? = null

    fun SoundNotification(context: Context?) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.soundialog)
        mMediaPlayer!!.start()
    }

}