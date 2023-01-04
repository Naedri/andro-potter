package com.naedri.andro_potter.view.library

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naedri.andro_potter.R

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    private lateinit var recyclerViewBooks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        viewModel.getState().observe(this) { state ->
            Toast.makeText(
                this@LibraryActivity,
                "${state.books.size} books | isLoading ${state.isLoading}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        viewModel.loadBooks()
        recyclerViewBooks = findViewById(R.id.recyclerViewBooks)

        val items = viewModel.getState().value?.books
        val columns = resources.getInteger(R.integer.gallery_columns)

        recyclerViewBooks.apply {
            recyclerViewBooks.layoutManager = GridLayoutManager(this@LibraryActivity, columns)
            recyclerViewBooks.adapter = items?.let { BookAdapter(it) }
        }
    }
}
