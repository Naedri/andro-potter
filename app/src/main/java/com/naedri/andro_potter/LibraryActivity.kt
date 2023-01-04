package com.naedri.andro_potter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    lateinit var recyclerViewBooks: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
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
                    recyclerViewBooks.adapter = items?.let { BookAdapter(it) }
                }
            }
        }
        viewModel.loadBooks()
    }
}
