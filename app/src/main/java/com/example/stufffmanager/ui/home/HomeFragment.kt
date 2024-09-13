package com.example.stufffmanager.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanager.ui.AnnouncementViewModel
import com.example.stufffmanager.R



class HomeFragment : Fragment() {
    val announcementViewModel  by lazy{ViewModelProvider(this).get(AnnouncementViewModel::class.java)}

    private lateinit var adapter: AnnouncementAdepter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = requireView().findViewById(R.id.announcementRecyclerView)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = AnnouncementAdepter(this, announcementViewModel.AnnoucementList)
        recyclerView.adapter = adapter
        announcementViewModel.getAnnouncement()
        announcementViewModel.response.observe(viewLifecycleOwner, Observer {result ->
            val annoucement = result.getOrNull()
            if(annoucement != null)
            {
                Log.d("cici","announcement get!")
                Log.d("cici","${annoucement.toString()}")
                recyclerView.visibility = view.visibility
                announcementViewModel.AnnoucementList.clear()
                announcementViewModel.AnnoucementList.addAll(annoucement.data.announcementList)
                adapter.notifyDataSetChanged()
            }
            else{
                Log.d("cici","announcement is null")
            }

        })
    }

    override fun onResume() {
        super.onResume()

    }



}