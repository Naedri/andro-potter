package com.naedri.andro_potter.service.FetchBook

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchBookService {
    private fun fetchBookService() {}

    companion object {
        private var instance: Retrofit? = null
        private var service: HenriPotierService? = null

        private fun getFetchBookRetrofit(): Retrofit? {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://henri-potier.techx.fr")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

        fun getFetchBookService(): HenriPotierService? {
            if (service == null) {
                service = getFetchBookRetrofit()?.create(HenriPotierService::class.java)
            }
            return service
        }
    }
}