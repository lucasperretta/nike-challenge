package com.lucas.urbarndictionary.views

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.urbarndictionary.R
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.viewmodels.IndexActivityViewModel
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity(), ViewModelStoreOwner {

    val viewModel: IndexActivityViewModel
        get() {
            return ViewModelProvider(this).get(IndexActivityViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        setupBehavior()

        setupRecyclerView()

        setupObservers()

    }

    private fun setupBehavior() {
        editText.addTextChangedListener {
            viewModel.searchTerm.value = it.toString()
        }

    }

    private fun setupRecyclerView() {
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = RecyclerViewAdapter()
    }

    private fun setupObservers() {
        viewModel.wordList.observe(this, Observer {
            recylerView.adapter!!.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
            return WordViewHolder(layoutInflater.inflate(R.layout.recycler_view_item_word, parent,
                false))
        }

        override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
            holder.setItem(viewModel.wordList.value!![position], position)
        }

        override fun getItemCount(): Int {
            return if (viewModel.wordList.value != null) viewModel.wordList.value!!.size else 0
        }

        inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private var item: Word? = null
            private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            private val detailTextView: TextView = itemView.findViewById(R.id.detailTextView)
            private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
            private val thumbsUpTextView: TextView = itemView.findViewById(R.id.thumbsUpTextView)
            private val thumbsDownTextView: TextView = itemView.findViewById(R.id.thumbsDownTextView)

            fun setItem(item: Word, position: Int) {
                this.item = item

                thumbsUpTextView.text = "${item.thumbsUp}"
                thumbsDownTextView.text = "${item.thumbsDown}"
                this.numberTextView.text = if (position + 1 == 1) getString(R.string.top_definition) else "${position + 1}"
                this.titleTextView.text = item.word
                this.detailTextView.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }

        }

    }

}