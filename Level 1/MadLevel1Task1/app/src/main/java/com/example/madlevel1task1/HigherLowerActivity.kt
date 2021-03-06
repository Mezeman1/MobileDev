package com.example.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding
import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     * Set the initial (UI) state of the game.
     */
    private fun initViews() {
        updateUI()
        btnEqual.setOnClickListener { onEqualsClick() }
        btnHigher.setOnClickListener { onHigherClick() }
        btnLower.setOnClickListener { onLowerClick() }
    }

    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onHigherClick() {
        rollDice()
        if(currentThrow > lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerWrong()
        }
    }

    private fun onLowerClick() {
        rollDice()
        if(currentThrow < lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerWrong()
        }
    }

    private fun onEqualsClick() {
        rollDice()
        if(currentThrow == lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerWrong()
        }
    }

    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    private fun onAnswerWrong() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }

    /**
     * Update the last throw text and the dice image resource drawable with the current throw.
     */
    private fun updateUI() {
        binding.tvLastThrow.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> ivDice.setImageResource(R.drawable.dice1)
            2 -> ivDice.setImageResource(R.drawable.dice2)
            3 -> ivDice.setImageResource(R.drawable.dice3)
            4 -> ivDice.setImageResource(R.drawable.dice4)
            5 -> ivDice.setImageResource(R.drawable.dice5)
            6 -> ivDice.setImageResource(R.drawable.dice6)
        }
    }

}