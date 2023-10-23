package co.aladinjunior.youtube.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import co.aladinjunior.youtube.R
import co.aladinjunior.youtube.databinding.ActivityMainBinding
import co.aladinjunior.youtube.databinding.VideoDetailedBinding
import co.aladinjunior.youtube.main.data.API
import co.aladinjunior.youtube.main.model.Video
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override var coroutineContext: CoroutineContext = Dispatchers.IO + job

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoDetailedBinding: VideoDetailedBinding
    private lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoDetailedBinding = VideoDetailedBinding.bind(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        val videos: MutableList<Video> = mutableListOf()
        adapter = VideoAdapter(videos) {
            showOverlayView()
        }
        binding.mainRv.layoutManager = LinearLayoutManager(this)
        binding.mainRv.adapter = adapter



        launch {

            val response = async { API().getVideo() }
            val listVideo = response.await()
            listVideo?.data
            withContext(Dispatchers.Main){
                listVideo?.let {
                    videos.clear()
                    videos.addAll(listVideo.data)
                    adapter.notifyDataSetChanged()

                }

            }

        }



    }

    private fun showOverlayView() {
        videoDetailedBinding.viewLayer.animate().apply {
            duration = 400
            alpha(0.5f)
        }

        binding.motionContainer.setTransitionListener(object : MotionLayout.TransitionListener{
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}