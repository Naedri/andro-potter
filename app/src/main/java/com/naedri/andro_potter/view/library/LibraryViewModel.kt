package com.naedri.andro_potter.view.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naedri.andro_potter.model.Book
import com.naedri.andro_potter.service.FetchBook.FetchBookService
import com.naedri.andro_potter.service.FetchBook.HenriPotierService
import com.naedri.andro_potter.state.LibraryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryViewModel : ViewModel() {

    private val state = MutableLiveData<LibraryState>()
    fun getState(): MutableLiveData<LibraryState> {
        return state
    }

    fun loadBooks() {
        val service: HenriPotierService? = FetchBookService.getFetchBookService()
        var books: List<Book>
        state.postValue(LibraryState(emptyList(), true))

        viewModelScope.launch(context = Dispatchers.Main) {
            books = if (service != null) {
                withContext(Dispatchers.IO) {
                    service.listBooks()
                }
            } else {
                emptyList()
            }
            state.postValue(LibraryState(books, false))
        }
    }
}