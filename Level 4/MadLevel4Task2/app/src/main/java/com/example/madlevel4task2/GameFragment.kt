package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

// Constants
const val ROCK = 0
const val PAPER = 1
const val SCISSORS = 2

const val WIN = 0
const val LOSE = 1
const val DRAW = 2

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        navController = findNavController()
        activity?.findViewById<Toolbar?>(R.id.toolbar)?.setOnMenuItemClickListener {
            onMenuItemClickedListener(it)
        }

        gameRepository = GameRepository(requireContext())

        view.findViewById<ImageView>(R.id.iv_rock).setOnClickListener {
            startGame(view, ROCK)
        }

        view.findViewById<ImageView>(R.id.iv_paper).setOnClickListener {
            startGame(view, PAPER)
        }

        view.findViewById<ImageView>(R.id.iv_scissors).setOnClickListener {
            startGame(view, SCISSORS)
        }

    }


    private fun startGame(view: View, playerChoose: Int) {
        val botChoose = Random.nextInt(0, 3)
        val botImageView = view.findViewById<ImageView>(R.id.iv_bot)
        val playerImageView = view.findViewById<ImageView>(R.id.iv_player)

        setImage(botImageView, botChoose)
        setImage(playerImageView, playerChoose)
        var result = setResult(playerChoose, botChoose)

        setResultText(view, result)

        mainScope.launch {
            val game = Game(
                timestamp = System.currentTimeMillis(),
                result = result,
                computer = botChoose,
                you = playerChoose,
            )
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

    private fun setResultText(view: View, result: Int) {
        val resultTextView = view.findViewById<TextView>(R.id.tv_result)

        when (result) {
            DRAW -> resultTextView.text = getString(R.string.draw)
            WIN -> resultTextView.text = getString(R.string.win)
            LOSE -> resultTextView.text = getString(R.string.lose)
        }

    }

    private fun setResult(playerChoose: Int, botChoose: Int) : Int {
        return when {
            playerChoose == ROCK && botChoose == SCISSORS
                    || playerChoose == PAPER && botChoose == ROCK
                    || playerChoose == SCISSORS && botChoose == PAPER
            -> WIN
            playerChoose === botChoose -> DRAW
            else -> LOSE
        }
    }

    private fun setImage(imageView: ImageView, choose: Int) {
        when (choose) {
            0 -> imageView.setImageResource(R.drawable.rock)
            1 -> imageView.setImageResource(R.drawable.paper)
            2 -> imageView.setImageResource(R.drawable.scissors)
        }
    }


    private fun onMenuItemClickedListener(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.action_history) {
            navController.navigate(
                R.id.action_FirstFragment_to_SecondFragment
            )
            return true
        }
        return false
    }

}