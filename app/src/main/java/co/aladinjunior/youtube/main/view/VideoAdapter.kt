package co.aladinjunior.youtube.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.youtube.R
import co.aladinjunior.youtube.databinding.ListItemVideoBinding
import co.aladinjunior.youtube.main.model.Video
import com.squareup.picasso.Picasso

class VideoAdapter(private val videos: List<Video>, private val onClick: (video: Video) -> Unit ) : RecyclerView.Adapter<VideoAdapter.VideoHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
       return VideoHolder(LayoutInflater.from(parent.context).inflate(
           R.layout.list_item_video,
           parent,
           false
       ))
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class VideoHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(video: Video){
            val binding = ListItemVideoBinding.bind(itemView)
            with(itemView){
                setOnClickListener {
                    onClick.invoke(video)
                }
                binding.mainVideoTitle.text = "aladin1213"



//                Picasso.get().load(video.thumbnailUrl).into(binding.mainVideoContainer)


            }

        }
    }
}