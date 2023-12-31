package co.aladinjunior.youtube.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.youtube.R
import co.aladinjunior.youtube.databinding.ActivityMainBinding
import co.aladinjunior.youtube.databinding.VideoDetailedBinding
import co.aladinjunior.youtube.databinding.VideoDetailedContentBinding
import co.aladinjunior.youtube.main.data.API
import co.aladinjunior.youtube.main.model.Video
import co.aladinjunior.youtube.main.model.VideoBuilder
import co.aladinjunior.youtube.util.YoutubePlayer
import co.aladinjunior.youtube.util.publisher
import co.aladinjunior.youtube.util.video
import co.aladinjunior.youtube.util.videos
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override var coroutineContext: CoroutineContext = Dispatchers.IO + job

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoDetailedBinding: VideoDetailedBinding
    private lateinit var detailedContentBinding: VideoDetailedContentBinding

    private lateinit var adapter: VideoAdapter
    private lateinit var youtubePlayer: YoutubePlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        videoDetailedBinding = VideoDetailedBinding.bind(binding.root)
        detailedContentBinding = VideoDetailedContentBinding.bind(binding.root)
        setContentView(binding.root)








        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        val videos: MutableList<Video> = mutableListOf()
        adapter = VideoAdapter(videos) {
            showOverlayView(it)

        }
        binding.mainRv.layoutManager = LinearLayoutManager(this)
        binding.mainRv.adapter = adapter




        launch {

            val response = async { API().getVideo() }
            val listVideo = response.await()
            listVideo?.data
            withContext(Dispatchers.Main) {
                listVideo?.let {
                    videos.clear()
                    videos.addAll(listVideo.data)
                    adapter.notifyDataSetChanged()

                }

            }

        }

        prepareVideo()
    }


    private fun prepareVideo() {
        youtubePlayer = YoutubePlayer(this@MainActivity)
        videoDetailedBinding.surfaceView.holder.addCallback(youtubePlayer)
    }

    private fun showOverlayView(video: Video) {
        videoDetailedBinding.viewLayer.animate().apply {
            duration = 400
            alpha(0.5f)
        }

        binding.motionContainer.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                if (p3 > 0.5f) videoDetailedBinding.viewLayer.alpha = 1.0f - p3
                else videoDetailedBinding.viewLayer.alpha = 0.5f
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })


        val detailedAdapter = VideoDetailedAdapter(videos())
        detailedContentBinding.rvSimilar.layoutManager = LinearLayoutManager(this)
        detailedContentBinding.rvSimilar.adapter = detailedAdapter

        Picasso.get().load(video.publisher.pictureProfileUrl).into(detailedContentBinding.imgChannel)
        detailedContentBinding.contentTitle.text = video.title
        detailedContentBinding.contentChannel.text = video.publisher.name
        detailedAdapter.notifyDataSetChanged()

        videoDetailedBinding.videoPlayer.visibility = View.GONE
        youtubePlayer.setUrl(video.videoUrl)



    }


    override fun onDestroy() {
        super.onDestroy()
        youtubePlayer.release()
    }

    override fun onPause() {
        super.onPause()
        youtubePlayer.pause()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}