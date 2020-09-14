package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener { checkAllAnswers() }
    }

    private fun checkAllAnswers() {
        val t1 = etShouldBeTrue.text.toString()
        val f1 = etShouldBeFalse1.text.toString()
        val f2 = etShouldBeFalse2.text.toString()
        val f3 = etShouldBeFalse4.text.toString()




        if (t1.get(0).toLowerCase() == 't' &&
            f1.get(0).toLowerCase() == 'f' &&
            f2.get(0).toLowerCase() == 'f' &&
            f3.get(0).toLowerCase() == 'f'
        ) {

            Toast.makeText(
                this, getString(R.string.correct),
                Toast.LENGTH_LONG
            ).show()

            return
        }

        Toast.makeText(
            this, getString(R.string.incorrect),
            Toast.LENGTH_LONG
        ).show()

    }
}