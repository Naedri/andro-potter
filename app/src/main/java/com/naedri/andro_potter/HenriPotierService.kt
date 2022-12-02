package com.naedri.andro_potter

import retrofit2.http.GET

interface HenriPotierService {
    @GET("/books")
    suspend fun listBooks(): List<Book>
}