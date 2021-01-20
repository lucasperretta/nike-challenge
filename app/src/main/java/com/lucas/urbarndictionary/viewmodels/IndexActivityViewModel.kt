package com.lucas.urbarndictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.UrbanDictionaryIndexRepository

class IndexActivityViewModel : ViewModel() {

    private val privateWordList: MutableLiveData<ArrayList<Word>?> = MutableLiveData()
    val wordList: LiveData<ArrayList<Word>?>
        get() {
            return privateWordList
        }

    init {
        UrbanDictionaryIndexRepository.getData { list ->
            privateWordList.value = list
        }
    }

}