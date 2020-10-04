package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PortalAddFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener{
            onAddPortal()
        }

    }

    private fun onAddPortal() {
        val portalTitle = tiTitle.text.toString()
        val portalUrl = tiUrl.text.toString()


        if (portalTitle.isNotBlank() && portalUrl.isNotBlank()) {
            val portal = Portal(portalTitle, portalUrl)
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))

            findNavController().popBackStack()
        } else {
            Toast.makeText(
                activity,
                "Invalid Portal", Toast.LENGTH_SHORT
            ).show()
        }
    }
}