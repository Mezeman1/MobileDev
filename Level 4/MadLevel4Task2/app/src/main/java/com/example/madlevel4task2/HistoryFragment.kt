package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val games = arrayListOf<Game>()
    private val gameAdapter =
        GameAdapter(games)
    private lateinit var gameRepository: GameRepository
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolbar: Toolbar? = activity?.findViewById<Toolbar?>(R.id.toolbar)
//        toolbar?.setNavigationIcon(R.drawable.ic_delete_white_24dp)
        toolbar?.setOnMenuItemClickListener {
            onMenuItemClickedListener(it)
        }

        initView()
    }

    private fun initView() {
        gameRepository = GameRepository(requireContext())
        getGames()
        viewManager = LinearLayoutManager(activity)
        rv_history.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        rv_history.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }
    }

    private fun onMenuItemClickedListener(it: MenuItem): Boolean {
        if(it.itemId == R.id.action_history)
        {
            deleteAllGames()
            return true
        }
        return false
    }

    private fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
            getGames()
        }
    }

    private fun getGames() {
        mainScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getGames()
            }
            this@HistoryFragment.games.clear()
            this@HistoryFragment.games.addAll(games)
            this@HistoryFragment.gameAdapter.notifyDataSetChanged()
        }
    }
}