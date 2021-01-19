package com.lucas.urbarndictionary.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lucas.urbarndictionary.R
import com.lucas.urbarndictionary.repositories.UrbanDictionaryIndexRepository
import com.lucas.urbarndictionary.viewmodels.IndexActivityViewModel

class IndexActivity : AppCompatActivity(), ViewModelStoreOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
    }

}