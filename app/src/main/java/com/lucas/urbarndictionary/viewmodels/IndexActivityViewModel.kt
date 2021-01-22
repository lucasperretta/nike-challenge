package com.lucas.urbarndictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.IndexRepository

class IndexActivityViewModel : ViewModel() {

    enum class ThumbsFilter {
        UP, DOWN
    }

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mutableWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()
    private val mutableThumbsFilter: MutableLiveData<ThumbsFilter> = MutableLiveData(ThumbsFilter.UP)

    val wordList: LiveData<ArrayList<Word>?> = mutableWordList
    val isLoading: LiveData<Boolean> = mutableIsLoading
    val thumbsFilter: LiveData<ThumbsFilter> = mutableThumbsFilter

    fun performSearch(search: String) {
        mutableIsLoading.value = true
        mutableWordList.value = null
        IndexRepository.getData(search, thumbsFilter.value!!) { list ->
            mutableWordList.value = list
            mutableIsLoading.value = false
        }
    }

    fun setThumbsFilter(filter: ThumbsFilter) {
        mutableThumbsFilter.value = filter
        mutableWordList.value = IndexRepository.sortResults(filter)
    }

}