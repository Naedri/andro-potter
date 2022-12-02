package com.naedri.andro_potter

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
