package com.naedri.andro_potter.service.FetchBook

import com.naedri.andro_potter.model.Book
import retrofit2.http.GET

interface HenriPotierService {
    @GET("/books")
    suspend fun listBooks(): List<Book>
}