package co.aladinjunior.youtube.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.youtube.R
import co.aladinjunior.youtube.databinding.ListItemVideoBinding
import co.aladinjunior.youtube.main.model.Video
import co.aladinjunior.youtube.util.formatted
import com.squareup.picasso.Picasso

class VideoDetailedAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<VideoDetailedAdapter.VideoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        return VideoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_detailed_list_item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class VideoHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(video: Video) {
            val binding = ListItemVideoBinding.bind(itemView)
            with(binding) {
                itemView.setOnClickListener {

                }

                Picasso.get().load(video.thumbnailUrl).into(mainVideoContainer)
                Picasso.get().load(video.publisher.pictureProfileUrl).into(mainVideoAvatar)

                mainVideoTitle.text = video.title
                mainVideoInfo.text = itemView.context.getString(R.string.video_info,
                    video.publisher.name, video.viewsCountLabel, video.publishedAt.formatted())



            }


        }
    }
}