package com.lucas.urbarndictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.IndexRepository

class IndexActivityViewModel : ViewModel() {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mutableWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()
    private val mutableThumbsFilter: MutableLiveData<IndexRepository.ThumbsFilter> = MutableLiveData(IndexRepository.ThumbsFilter.Up)

    val wordList: LiveData<ArrayList<Word>?> = mutableWordList
    val isLoading: LiveData<Boolean> = mutableIsLoading
    val thumbsFilter: LiveData<IndexRepository.ThumbsFilter> = mutableThumbsFilter

    fun performSearch(search: String) {
        mutableIsLoading.value = true
        mutableWordList.value = null
        IndexRepository.getData(search, thumbsFilter.value ?: IndexRepository.ThumbsFilter.Up) { list ->
            mutableWordList.value = list
            mutableIsLoading.value = false
        }
    }

    fun changeThumbsFilter(filter: IndexRepository.ThumbsFilter) {
        mutableThumbsFilter.value = filter
        mutableWordList.value?.let { filter.method(it) }
    }

}