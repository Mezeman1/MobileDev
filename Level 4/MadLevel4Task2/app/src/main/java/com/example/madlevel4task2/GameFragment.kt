package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        activity?.findViewById<Toolbar?>(R.id.toolbar)?.setOnMenuItemClickListener{
            onMenuItemClickedListener(it)
        }
    }


    private fun onMenuItemClickedListener(menuItem: MenuItem) : Boolean {
        if (menuItem.itemId == R.id.action_history) {
            navController.navigate(
                R.id.action_FirstFragment_to_SecondFragment
            )
            return true
        }
        return false
    }

}