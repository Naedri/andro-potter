package com.naedri.andro_potter.view.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naedri.andro_potter.R
import com.naedri.andro_potter.model.Bucket
import com.naedri.andro_potter.view.library.BucketActivity.Companion.BUCKET
import com.nex3z.notificationbadge.NotificationBadge

class LibraryActivity : AppCompatActivity() {
    companion object {
        internal const val BOOK = "book"

        @JvmStatic
        var mCount = 0

        @JvmStatic
        var bucket: Bucket = Bucket();
    }

    private val viewModel by viewModels<LibraryViewModel>()
    private lateinit var recyclerViewBooks: RecyclerView
    private lateinit var badge: NotificationBadge

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_library)
        badge = findViewById(R.id.badge);
        badge.setNumber(mCount);

        viewModel.getState().observe(this) { state ->
            /*
            Toast.makeText(
                this@LibraryActivity,
                "${state.books.size} books | ${ if(state.isLoading) "Loading" else "Loaded"}",
                Toast.LENGTH_SHORT
            ).show()
            */
            if (state.books.isNotEmpty()) {
                val items = state.books
                val columns = resources.getInteger(R.integer.gallery_columns)
                recyclerViewBooks = findViewById(R.id.recyclerViewBooks)

                recyclerViewBooks.apply {
                    recyclerViewBooks.layoutManager =
                        GridLayoutManager(this@LibraryActivity, columns)
                    val bookAdapter = items?.let { BookAdapter(it) }
                    recyclerViewBooks.adapter = bookAdapter

                    bookAdapter?.onAddToCartClick = {
                        bucket.addItemBucket(it)
                        badge.setNumber(++mCount)
                        Log.d(
                            "LibraryActivity",
                            "Adding to cart button clicked for book: ${it.title}"
                        )
                        Log.d(
                            "LibraryActivity",
                            "Bucket is now: $bucket"
                        )
                    }

                    bookAdapter?.onItemClick = {
                        val intent = Intent(this@LibraryActivity, BookDetailActivity::class.java)
                        Log.d(
                            "LibraryActivity",
                            "Starting BookDetailActivity for book: ${it.title}"
                        )
                        intent.putExtra(BOOK, it)
                        startActivity(intent)
                    }
                }
            }
        }
        viewModel.loadBooks();


        val cartButton: ImageButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener {
            if (mCount > 0) {
                val intent = Intent(this@LibraryActivity, BucketActivity::class.java)
                Log.d(
                    "LibraryActivity",
                    "Starting BucketActivity with bucket: $bucket"
                )
                intent.putParcelableArrayListExtra(BUCKET, bucket.getItems())
                startActivity(intent)
            }
        }
    }
}
