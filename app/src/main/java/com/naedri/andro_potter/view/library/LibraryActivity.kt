package com.naedri.andro_potter.view.library

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.naedri.andro_potter.R

import com.nex3z.notificationbadge.NotificationBadge;

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    @BindView(R.id.recyclerViewBooks)
    private lateinit var recyclerViewBooks: RecyclerView
    @BindView(R.id.mainLayout)
    private lateinit var mainLayout: RelativeLayout
    @BindView(R.id.badge)
    private lateinit var badge:NotificationBadge;
    @BindView(R.id.btnCart)
    private lateinit var btnCart:FrameLayout;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_library)

        viewModel.getState().observe(this) { state ->
            /*
            Toast.makeText(
                this@LibraryActivity,
                "${state.books.size} books | ${ if(state.isLoading) "Loading" else "Loaded"}",
                Toast.LENGTH_SHORT
            ).show()
            */
            
            if(state.books.isNotEmpty()){
                val items = state.books
                val columns = resources.getInteger(R.integer.gallery_columns)
                recyclerViewBooks = findViewById(R.id.recyclerViewBooks)
                recyclerViewBooks.apply {
                    recyclerViewBooks.layoutManager = GridLayoutManager(this@LibraryActivity, columns)
                    val bookAdapter = items?.let { BookAdapter(it) }
                    recyclerViewBooks.adapter = bookAdapter

                    bookAdapter?.onItemClick = {
                        val intent = Intent(this@LibraryActivity, BookDetailActivity::class.java)
                        //val intent = Intent(BookDetailActivity::class.java)
                        intent.putExtra("book",it)
                        startActivity(intent)

                    }
                }
            }
        }
        viewModel.loadBooks()


    }
}
