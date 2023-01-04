package com.naedri.andro_potter

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)