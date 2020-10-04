package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_portal_overview.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalOverviewFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter

        for (i in Portal.PORTAL_TITLES.indices) {
            portals.add(Portal(Portal.PORTAL_TITLES[i], Portal.PORTAL_URLS[i]))
        }

        portalAdapter.notifyDataSetChanged()

        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = it

                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")

        }
    }
}