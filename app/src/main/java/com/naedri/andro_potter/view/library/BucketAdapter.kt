package com.naedri.andro_potter.view.library


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naedri.andro_potter.R
import com.naedri.andro_potter.model.Book
import com.naedri.andro_potter.model.Bucket

class BucketAdapter(private val bucket: Bucket) :
    RecyclerView.Adapter<BucketAdapter.CartViewHolder>() {

    var onRemoveItemClick: ((Book) -> Unit)? = null;

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartBookName: TextView = itemView.findViewById(R.id.bookBucketTitle)
        val cartBookPrice: TextView = itemView.findViewById(R.id.bookBucketPrice)
        val cartBookQuantity: TextView = itemView.findViewById(R.id.bookBucketQuantity)
        val cartRemoveButton: Button = itemView.findViewById(R.id.buttonRemoveBucket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val vue = LayoutInflater.from(parent.context).inflate(R.layout.item_bucket, parent, false)
        return CartViewHolder(vue)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val quantity = bucket.getItems()[position].quantity
        val book = bucket.getItems()[position].book
        holder.cartBookName.text = book.title
        holder.cartBookPrice.text = book.price.toString() + '€'
        holder.cartBookQuantity.text = quantity.toString() + 'x'
        holder.cartRemoveButton.setOnClickListener {
            Log.d(
                "BucketAdapter",
                "Try to delete book from bucket: $book"
            )
            onRemoveItemClick?.invoke(book);
        }
    }

    override fun getItemCount(): Int {
        return bucket?.getItems()?.size ?: 0
    }
}