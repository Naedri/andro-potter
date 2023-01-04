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
        setContentView(R.layout.activity_library)

        viewModel.state.observe(this) { state ->
            Toast.makeText(
                this@LibraryActivity,
                "${state.books.size} books | isLoading ${state.isLoading}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        viewModel.loadBooks()
        recyclerViewBooks = findViewById(R.id.recyclerViewBooks)

        val items = viewModel.state.value?.books
        val columns = getResources().getInteger(R.integer.gallery_columns);

        recyclerViewBooks.apply {
            recyclerViewBooks.layoutManager = GridLayoutManager(this@LibraryActivity, columns)
            recyclerViewBooks.adapter = items?.let { BooksAdapter(it) }
        }
    }
}
