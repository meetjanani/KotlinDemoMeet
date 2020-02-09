package `in`.meetjanani.kotlindemomeet

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_liem_1.view.*
import org.w3c.dom.Attr
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import android.webkit.WebChromeClient


class MediaAdapter(val userList: List<MediaModel.CharityMedia>, val ctx : Context) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_liem_1, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position] , ctx)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: MediaModel.CharityMedia, ctx : Context) {

            if (user.type == 1)
            {
                Picasso.with(ctx)
                    .load(  user.url + "")
                    .resize(250, 250)
                    .centerCrop()
                    //.transform(transformation)
                    .into(itemView.imageView)
                itemView.webView.visibility = View.GONE
            }
            else
            {

                itemView.webView.settings.javaScriptEnabled = true
                //itemView.webView.webChromeClient = WebChromeClient()
                itemView.webView.loadData( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + user.url + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>",
                    "text/html" , "utf-8" )

                itemView.imageView.visibility = View.GONE
//                itemView.buton_VideoPlay.setOnClickListener {
//                    val url = user.url + ""
//                    //val videoUrl = getUrlVideoRTSP("http://meetjanani.in/VID.mp4")
//                    val videoUrl = getUrlVideoRTSP(user.url + "")
//
//                    itemView.videoView.setVideoURI(Uri.parse(videoUrl))
//                    val mc = MediaController(ctx)
//                    itemView.videoView.setMediaController(mc)
//                    itemView.videoView.requestFocus()
//                    mc.show()
//                    itemView.videoView.start()
//                }
            }

        }

        @SuppressLint("LongLogTag")
        fun getUrlVideoRTSP(urlYoutube: String): String {
            try {
                val gdy = "http://gdata.youtube.com/feeds/api/videos/"
                val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                val id = extractYoutubeId(urlYoutube)
                val url = URL(gdy + id)
                val connection = url.openConnection() as HttpURLConnection
                val doc = documentBuilder.parse(connection.getInputStream())
                val el = doc.getDocumentElement()
                val list = el.getElementsByTagName("media:content")///media:content
                var cursor = urlYoutube
                for (i in 0 until list.getLength()) {
                    val node = list.item(i)
                    if (node != null) {
                        val nodeMap = node.getAttributes()
                        val maps = HashMap<String, String>()
                        for (j in 0 until nodeMap.getLength()) {
                            val att = nodeMap.item(j) as Attr
                            maps[att.getName()] = att.getValue()
                        }
                        if (maps.containsKey("yt:format")) {
                            val f = maps["yt:format"]
                            if (maps.containsKey("url")) {
                                cursor = maps["url"].toString()
                            }
                            if (f == "1")
                                return cursor
                        }
                    }
                }
                return cursor
            } catch (ex: Exception) {
                Log.v("Exception 1", ex.toString())
            }

            return urlYoutube

        }

        @Throws(MalformedURLException::class)
        protected fun extractYoutubeId(url: String): String? {
            var id: String? = null
            try {
                val query = URL(url).query
                if (query != null) {
                    val param =
                        query.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    for (row in param) {
                        val param1 =
                            row.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if (param1[0] == "v") {
                            id = param1[1]
                        }
                    }
                } else {
                    if (url.contains("embed")) {
                        id = url.substring(url.lastIndexOf("/") + 1)
                    }
                }
            } catch (ex: Exception) {
                Log.v("Exception 2", ex.toString())
            }

            return id
        }
    }
}