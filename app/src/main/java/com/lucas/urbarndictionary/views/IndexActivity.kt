package com.lucas.urbarndictionary.views

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.urbarndictionary.R
import com.lucas.urbarndictionary.kotlin.getTint
import com.lucas.urbarndictionary.kotlin.toHtml
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.repositories.IndexRepository
import com.lucas.urbarndictionary.viewmodels.IndexActivityViewModel
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {

    val viewModel: IndexActivityViewModel get() = ViewModelProvider(this).get(IndexActivityViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        window.decorView.requestFocus()

        setupRecyclerView()

        setupObservers()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        editText.addTextChangedListener {
            viewModel.performSearch(it.toString())
        }
        thumbsUpFAB.setOnClickListener {
            viewModel.changeThumbsFilter(IndexRepository.ThumbsFilter.Up)
        }
        thumbsDownFAB.setOnClickListener {
            viewModel.changeThumbsFilter(IndexRepository.ThumbsFilter.Down)
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter()
    }

    private fun setupObservers() {
        viewModel.wordList.observe(this, {
            recyclerView.adapter?.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        viewModel.thumbsFilter.observe(this, { filter ->
            thumbsUpFAB.imageTintList = getTint(if (filter == IndexRepository.ThumbsFilter.Up) R.color.highlight else R.color.white)
            thumbsDownFAB.imageTintList = getTint(if (filter == IndexRepository.ThumbsFilter.Down) R.color.highlight else R.color.white)
        })
    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
            return WordViewHolder(layoutInflater.inflate(R.layout.recycler_view_item_word, parent,
                false))
        }

        override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
            viewModel.wordList.value?.let {
                holder.setItem(it[position], position)
            }
        }

        override fun getItemCount(): Int {
            return viewModel.wordList.value?.size ?: 0
        }

        inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private var item: Word? = null
            private val titleTextView: TextView = findViewById(R.id.titleTextView)
            private val detailTextView: TextView = findViewById(R.id.detailTextView)
            private val numberTextView: TextView = findViewById(R.id.numberTextView)
            private val thumbsUpTextView: TextView = findViewById(R.id.thumbsUpTextView)
            private val thumbsDownTextView: TextView = findViewById(R.id.thumbsDownTextView)
            private val authorTextView: TextView = findViewById(R.id.authorTextView)

            fun setItem(item: Word, position: Int) {
                this.item = item

                this.thumbsUpTextView.text = "${item.thumbsUp}"
                this.thumbsDownTextView.text = "${item.thumbsDown}"
                this.numberTextView.text = if (position + 1 == 1) getString(R.string.top_definition) else "${position + 1}"
                this.titleTextView.text = item.word
                this.detailTextView.text = item.description.toHtml()
                this.authorTextView.text = item.authorDate.toHtml()
            }

        }

    }

}