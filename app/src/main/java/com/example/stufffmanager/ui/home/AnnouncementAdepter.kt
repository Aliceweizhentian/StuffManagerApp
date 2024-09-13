package com.example.stufffmanager.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanager.logic.model.Announcement
import com.example.stufffmanager.R

class AnnouncementAdepter(private val fragment: Fragment, private val announcementList: List<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdepter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val annouceTitle: TextView = view.findViewById(R.id.annouceTitle)
        val annouceContent: TextView = view.findViewById(R.id.annouceContent)
        val annouceTme: TextView = view.findViewById(R.id.annouceTime)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_item,
            parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val announcement = announcementList[position]
        holder.annouceTitle.text = announcement.title
        holder.annouceContent.text = announcement.content
        holder.annouceTme.text = announcement.time
    }
    override fun getItemCount() = announcementList.size
}

