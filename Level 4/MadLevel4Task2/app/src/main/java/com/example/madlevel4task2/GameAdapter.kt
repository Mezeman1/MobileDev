package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            var playerDrawable = setImage(game.player)
            var botDrawable = setImage(game.computer)
            var resultText = setResult(game.result)
            var date = Date(game.timestamp)
            var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.getDefault())

            itemView.findViewById<TextView>(R.id.tv_result).text = resultText
            itemView.findViewById<TextView>(R.id.tv_date).text = dateFormat.format(date)
            itemView.findViewById<ImageView>(R.id.iv_bot).setImageResource(botDrawable)
            itemView.findViewById<ImageView>(R.id.iv_player).setImageResource(playerDrawable)
        }

        private fun setResult(result: Int): String {
            when (result) {
                WIN -> return itemView.context.getString(R.string.win)
                DRAW -> return itemView.context.getString(R.string.draw)
                LOSE -> return itemView.context.getString(R.string.lose)
            }

            return ""
        }

        private fun setImage(type: Int): Int {
            when (type) {
                SCISSORS -> return R.drawable.scissors
                ROCK -> return R.drawable.rock
                PAPER -> return R.drawable.paper
            }

            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}