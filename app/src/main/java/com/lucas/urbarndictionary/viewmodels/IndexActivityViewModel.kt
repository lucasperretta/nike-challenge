package com.lucas.urbarndictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.urbarndictionary.extensions.notifyObserver
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.UrbanDictionaryIndexRepository

class IndexActivityViewModel : ViewModel() {

    enum class ThumbsFilter {
        UP, DOWN
    }

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    private val mutableWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()

    val searchTerm: MutableLiveData<String> = MutableLiveData("")
    val wordList: LiveData<ArrayList<Word>?> = mutableWordList
    val isLoading: LiveData<Boolean> = mutableIsLoading
    val thumbsFilter: MutableLiveData<ThumbsFilter> = MutableLiveData(ThumbsFilter.UP)

    init {
        searchTerm.observeForever {
            performSearch(it)
        }
        thumbsFilter.observeForever {
            mutableWordList.value?.sortByDescending {
                if (thumbsFilter.value == ThumbsFilter.UP) it.thumbsUp else it.thumbsDown
            }
            mutableWordList.notifyObserver()
        }
    }

    private fun performSearch(parameter: String) {
        mutableIsLoading.value = true
        mutableWordList.value = null
        UrbanDictionaryIndexRepository.getData(parameter) { list ->
            mutableWordList.value = list
            mutableIsLoading.value = false
        }
    }

}