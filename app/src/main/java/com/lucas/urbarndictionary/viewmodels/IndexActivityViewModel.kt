package com.lucas.urbarndictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.UrbanDictionaryIndexRepository

class IndexActivityViewModel : ViewModel() {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()

    val searchTerm: MutableLiveData<String> = MutableLiveData()
    val wordList: LiveData<ArrayList<Word>?> = mutableWordList
    val isLoading: LiveData<Boolean> = mutableIsLoading

    init {
        performSearch()
    }

    private fun performSearch() {
        searchTerm.value = ""
        searchTerm.observeForever{
            mutableIsLoading.value = true
            mutableWordList.value = null
            UrbanDictionaryIndexRepository.getData(it) { list ->
                mutableWordList.value = list
                mutableIsLoading.value = false
            }
        }
    }

}