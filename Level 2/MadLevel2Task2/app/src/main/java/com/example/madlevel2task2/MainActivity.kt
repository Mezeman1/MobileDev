package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizes = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(quizes) { quiz: Quiz -> quizItemClicked(quiz) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun quizItemClicked(quiz: Quiz) {
        Snackbar.make(binding.rvQuestions, quiz.answer.toString().capitalize(), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = quizAdapter
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Quiz.QUESTION_NAMES.indices) {
            quizes.add(Quiz(Quiz.QUESTION_NAMES[i], Quiz.QUESTION_ANSWER[i]))
        }

        quizAdapter.notifyDataSetChanged()

        createItemTouchHelper().attachToRecyclerView(binding.rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = quizes[position]

                if (direction == ItemTouchHelper.LEFT) {
                    if (question.answer) {
                        Snackbar.make(viewHolder.itemView, "Correct!", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(
                            viewHolder.itemView,
                            "Your answer was wrong!",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    if (!question.answer) {
                        Snackbar.make(viewHolder.itemView, "Correct!", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(
                            viewHolder.itemView,
                            "Your answer was wrong!",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                quizAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }

        return ItemTouchHelper(callback);
    }
}