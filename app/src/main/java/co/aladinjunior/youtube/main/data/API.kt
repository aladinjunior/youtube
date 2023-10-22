package co.aladinjunior.youtube.main.data

import co.aladinjunior.youtube.main.model.ListVideo
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class API {

    private val url = "https://tiagoaguiar.co/api/youtube-videos"

    fun getVideo() : ListVideo?{
        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

      return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful){
                GsonBuilder().create().fromJson(response.body()?.string(),
                ListVideo::class.java)
            } else {
                null
            }
        } catch (e: Exception){
            null
        }
    }

}
