package co.aladinjunior.youtube.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.youtube.R

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
       return VideoHolder(LayoutInflater.from(parent.context).inflate(
           R.layout.list_item_video,
           parent,
           false
       ))
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class VideoHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(){

        }
    }
}