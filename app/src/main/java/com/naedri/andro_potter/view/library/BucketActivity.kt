package com.naedri.andro_potter.view.library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naedri.andro_potter.R
import com.naedri.andro_potter.model.Bucket
import com.naedri.andro_potter.model.ItemBucket

class BucketActivity : AppCompatActivity() {

    companion object {
        internal const val BUCKET = "BUCKET"
    }

    private lateinit var bucket: Bucket
    private lateinit var bucketItems: ArrayList<ItemBucket>
    private lateinit var recyclerViewBucket: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_bucket)

        bucketItems = intent.getParcelableArrayListExtra<ItemBucket>(BUCKET)!!;
        bucket = Bucket(bucketItems)
        Log.d(
            "BucketActivity",
            "Content of the bucket: $bucket"
        )

        recyclerViewBucket = findViewById(R.id.recyclerViewBucket)
        val adapter = BucketAdapter(bucket)
        recyclerViewBucket.layoutManager = LinearLayoutManager(this)
        recyclerViewBucket.adapter = adapter
    }
}
