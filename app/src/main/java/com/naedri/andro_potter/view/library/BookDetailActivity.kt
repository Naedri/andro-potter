package com.naedri.andro_potter.view.library

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.naedri.andro_potter.R
import com.naedri.andro_potter.model.Book

class BookDetailActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_book_detail)
        val book = intent.getParcelableExtra<Book>("book");

        if(book != null){
            val title:TextView = findViewById(R.id.bookTitle)
            val price:TextView = findViewById(R.id.bookPrice)
            val cover: ImageView = findViewById(R.id.bookCover)
            val synopsis:TextView = findViewById(R.id.bookSynopsis)

            title.text = book.title
            price.text = book.price.toString() + "€"
            cover.load(Uri.parse(book.cover))
            synopsis.text = book.synopsis[0]
        }

    }
}