package com.example.staticquotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStrean = context.assets.open("quotes.json")
        val size = inputStrean.available()
        val buffer = ByteArray(size)
        inputStrean.read(buffer)
        inputStrean.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[index++]

    fun previousQuote() = quoteList[index--]

}