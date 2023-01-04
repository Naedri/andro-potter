package com.naedri.andro_potter.state

import com.naedri.andro_potter.model.Book

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)