package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemQuizBinding

class QuizAdapter(val quizes: List<Quiz>, val clickListener: (Quiz) -> Unit) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemQuizBinding.bind(itemView)

        fun bind(
            quiz: Quiz,
            clickListener: (Quiz) -> Unit
        ) {
            binding.tvQuiz.text = quiz.name
            binding.tvQuiz.setOnClickListener { clickListener(quiz) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quizes[position], clickListener)
    }

    override fun getItemCount(): Int {
        return quizes.size
    }
}