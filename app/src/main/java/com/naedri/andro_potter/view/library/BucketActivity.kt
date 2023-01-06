package com.naedri.andro_potter.view.library

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naedri.andro_potter.R
import com.naedri.andro_potter.model.Bucket
import com.naedri.andro_potter.model.ItemBucket

class BucketActivity : AppCompatActivity() {

    companion object {
        internal const val BUCKET = "BUCKET"

        @JvmStatic
        var bucket: Bucket = Bucket();
    }

    private lateinit var bucketItems: ArrayList<ItemBucket>
    private lateinit var recyclerViewBucket: RecyclerView
    private lateinit var emptyBucketButton: Button
    private val viewModel by viewModels<BucketViewModel>()

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

        val columns = resources.getInteger(R.integer.gallery_columns)
        recyclerViewBucket.layoutManager = GridLayoutManager(this, columns)
        recyclerViewBucket.adapter = adapter

        adapter?.onRemoveItemClick = {
            Log.d(
                "BucketActivity",
                "Removing book: ${it.title}"
            )
            bucket.removeItemBucket(it)
            Log.d(
                "LibraryActivity",
                "Bucket is now: ${bucket}"
            )
        }

        emptyBucketButton = this.findViewById(R.id.buttonBuy);
        emptyBucketButton?.setOnClickListener {
            val quantity = bucket.getItems().size
            Log.d(
                "BucketAdapter",
                "Try to flush bucket containing $quantity isbn(s)"
            )
            bucket.flushBucket()
            recyclerViewBucket.adapter?.notifyItemRangeRemoved(0, quantity);
        }
    }
}
