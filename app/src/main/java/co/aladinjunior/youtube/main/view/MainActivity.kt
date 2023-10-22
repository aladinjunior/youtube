package co.aladinjunior.youtube.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import co.aladinjunior.youtube.databinding.ActivityMainBinding
import co.aladinjunior.youtube.main.data.API
import co.aladinjunior.youtube.main.model.Video
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override var coroutineContext: CoroutineContext = Dispatchers.IO + job

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videos: MutableList<Video> = mutableListOf()
        adapter = VideoAdapter(videos) {

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


}