package `in`.meetjanani.kotlindemomeet

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shimmerLayout.startShimmerAnimation()

        Handler().postDelayed({
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            setValues()
        }, 5000)




    }

    fun setValues() {
        val gson = Gson()
        val jsonString = """{"charityMedia": [

                        {

                            "id": 5,

                            "charity_id": 3,

                            "type": 2,

                            "mediaFrom": 2,

                            "url": "2IqcOqhoiNs",

                            "created_at": "2020-01-27 15:56:54",

                            "updated_at": "2020-01-27 15:56:54",

                            "deleted_at": null

                        },

                        {

                            "id": 6,

                            "charity_id": 3,

                            "type": 1,

                            "mediaFrom": 1,

                            "url": "https://digiref.s3.amazonaws.com/charity_media/ezcd34qy9qhwxotw1ljbag_1580120809383.png",

                            "created_at": "2020-01-27 15:56:54",

                            "updated_at": "2020-01-27 15:56:54",

                            "deleted_at": null

                        },

                        {

                            "id": 7,

                            "charity_id": 3,

                            "type": 2,

                            "mediaFrom": 1,

                            "url": "MG3jGHnBVQs",

                            "created_at": "2020-01-27 15:56:54",

                            "updated_at": "2020-01-27 15:56:54",

                            "deleted_at": null

                        },

                        {

                            "id": 8,

                            "charity_id": 3,

                            "type": 1,

                            "mediaFrom": 1,

                            "url": "https://digiref.s3.amazonaws.com/charity_media/ukf13oyl9r5t6fm88bsw2_1580120812029.jpeg",

                            "created_at": "2020-01-27 15:56:54",

                            "updated_at": "2020-01-27 15:56:54",

                            "deleted_at": null

                        },

                        {

                            "id": 9,

                            "charity_id": 3,

                            "type": 1,

                            "mediaFrom": 1,

                            "url": "https://digiref.s3.amazonaws.com/charity_media/7kw3g4jmdm7kh2qbc97b9_1580120813214.jpeg",

                            "created_at": "2020-01-27 15:56:54",

                            "updated_at": "2020-01-27 15:56:54",

                            "deleted_at": null

                        }

                    ]}"""
        val testModel = gson.fromJson(jsonString, MediaModel::class.java)

        val adapter = MediaAdapter(testModel.charityMedia, this)
        recycler_view_Categories_List.adapter = adapter
        recycler_view_Categories_List.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

}
