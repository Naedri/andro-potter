package com.naedri.andro_potter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LibraryViewModel : ViewModel() {
    fun loadBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: HenriPotierService = retrofit.create(HenriPotierService::class.java)

        state.postValue(LibraryState(emptyList(), true))

        viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.listBooks()
            }
            state.postValue(LibraryState(books, false))
        }
    }

    val state = MutableLiveData<LibraryState>()
}

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)

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
            recyclerViewBooks.layoutManager = GridLayoutManager(this@LibraryActivity,columns)
            recyclerViewBooks.adapter = items?.let { BooksAdapter(it) }
        }
    }
}
