package com.naedri.andro_potter.state

import com.naedri.andro_potter.model.Book
import com.naedri.andro_potter.view.bucket.ItemBucket
import java.util.*

class BucketState {
        private var bucket: LinkedList<ItemBucket> = LinkedList()

        fun addItemBucket(book: Book) {
            var found = false
            bucket.filter { it.book.isbn == book.isbn }.forEach {
                it.quantity.inc()
                found = true
            }
            if (!found) bucket.add(ItemBucket(1, book))
        }

        fun removeItemBucket(book: Book) {
            bucket.filter { it.book.isbn == book.isbn }.forEach {
                if(it.quantity > 1) it.quantity.dec()
                else bucket.remove(it)
            }
        }

        fun emptyBucket() {
            bucket.clear()
        }

        fun countItemBucket():Int{
            var acc = 0
            bucket.map { itemBucket -> acc += itemBucket.quantity }
            return acc;
        }

        fun getBucketPrice():Int{
            var acc = 0
            bucket.map { itemBucket -> acc += itemBucket.quantity * itemBucket.book.price }
            return acc;
        }
    }