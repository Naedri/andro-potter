package com.naedri.andro_potter.view.library

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.naedri.andro_potter.model.Book
import com.naedri.andro_potter.R

class BookAdapter(private var items: List<Book>) : RecyclerView.Adapter<BookAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = items[position]
        holder.bind(book)
    }

    override fun getItemCount() :Int = items.size

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookTitle: TextView
        var bookPrice: TextView
        var bookCover: ImageView
        var addToCart: Button
        init {
            bookTitle = itemView.findViewById(R.id.bookTitle)
            bookPrice = itemView.findViewById(R.id.bookPrice)
            bookCover = itemView.findViewById(R.id.bookCover)
            addToCart = itemView.findViewById(R.id.buttonAddToCart)
            addToCart.setOnClickListener {
                Log.d("BooksAdapter", bookTitle.text as String)
            }
        }
        fun bind(book: Book) {
            bookTitle.text = book.title
            bookPrice.text = book.price.toString() + "€"
            bookCover.load(Uri.parse(book.cover))
        }
    }
}