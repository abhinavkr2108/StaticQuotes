package com.example.staticquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var tvQuotes: TextView
    lateinit var tvAuthor: TextView
    lateinit var btnNext: Button
    lateinit var btnPrevious: Button
    lateinit var fab: FloatingActionButton

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvQuotes = findViewById(R.id.quotesView)
        tvAuthor = findViewById(R.id.tvAuthorName)
        btnNext= findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)
        fab = findViewById(R.id.fabShareQuote)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)
        SetQuote(mainViewModel.getQuote())

        btnNext.setOnClickListener {
            NextQuote()
        }

        btnPrevious.setOnClickListener {
            PreviousQuote()
        }

        fab.setOnClickListener{
            ShareQuote()
        }

    }

    fun SetQuote(quote: Quote){
        tvQuotes.text = quote.text
        tvAuthor.text = quote.author
    }

    fun NextQuote(){
        SetQuote(mainViewModel.nextQuote())
    }

    fun PreviousQuote(){
        SetQuote(mainViewModel.previousQuote())
    }

    fun ShareQuote(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().toString())
        startActivity(intent)

    }
}