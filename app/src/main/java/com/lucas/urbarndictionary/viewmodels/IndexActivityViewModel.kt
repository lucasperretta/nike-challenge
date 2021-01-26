package com.lucas.urbarndictionary.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucas.urbarndictionary.kotlin.notifyObservers
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.IndexRepository

class IndexActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mutableWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()
    private val mutableThumbsFilter: MutableLiveData<IndexRepository.ThumbsFilter> = MutableLiveData(IndexRepository.ThumbsFilter.Up)

    val wordList: LiveData<ArrayList<Word>?> = mutableWordList
    val isLoading: LiveData<Boolean> = mutableIsLoading
    val thumbsFilter: LiveData<IndexRepository.ThumbsFilter> = mutableThumbsFilter

    private val context: Context
        get() { return (getApplication() as Application).applicationContext }

    fun performSearch(search: String) {
        mutableIsLoading.value = true
        mutableWordList.value = null
        IndexRepository.getData(context, search, thumbsFilter.value ?: IndexRepository.ThumbsFilter.Up) { result ->
            mutableIsLoading.value = false
            result.getOrNull()?.let { mutableWordList.value = it.list }
        }
    }

    fun changeThumbsFilter(filter: IndexRepository.ThumbsFilter) {
        mutableThumbsFilter.value = filter
        mutableWordList.value?.let { filter.method(it) }
        mutableWordList.notifyObservers()
    }

}