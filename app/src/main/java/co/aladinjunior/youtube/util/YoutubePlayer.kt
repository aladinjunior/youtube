package co.aladinjunior.youtube.util

import android.content.Context
import android.net.Uri
import android.view.SurfaceHolder
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class YoutubePlayer(private val context: Context) : SurfaceHolder.Callback {

    private var mediaPlayer: SimpleExoPlayer? = null

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (mediaPlayer == null) {
            mediaPlayer = SimpleExoPlayer.Builder(context).build()
            mediaPlayer?.setVideoSurfaceHolder(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mediaPlayer?.release()
    }

    fun setUrl(url: String){

        mediaPlayer?.let {
            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "youtube")
            )

            val mediaSource = ProgressiveMediaSource.Factory(
                dataSourceFactory
            ).createMediaSource(Uri.parse(url))


            it.prepare(mediaSource)
            play()
        }

    }

    fun play(){
        mediaPlayer?.playWhenReady = true
    }

    fun pause(){
        mediaPlayer?.playWhenReady = false
    }

    fun release(){
        mediaPlayer?.release()
    }
}