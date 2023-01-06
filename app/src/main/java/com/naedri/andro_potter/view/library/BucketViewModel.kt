package com.naedri.andro_potter.view.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naedri.andro_potter.state.BucketState

class BucketViewModel : ViewModel() {
    private val state = MutableLiveData<BucketState>()
    fun getState(): MutableLiveData<BucketState> {
        return state
    }
}
