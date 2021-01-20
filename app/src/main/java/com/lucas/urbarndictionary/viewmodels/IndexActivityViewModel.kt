package com.lucas.urbarndictionary.viewmodels

import android.util.Log
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
        searchTerm.value = ""
        searchTerm.observeForever{
            mutableIsLoading.value = true
            UrbanDictionaryIndexRepository.getData(it) { list ->
                if (list != null && list.isNotEmpty()) {
                    Log.e("A", list[0].word)
                }
                mutableWordList.value = list
                mutableIsLoading.value = false
            }
        }
    }

}