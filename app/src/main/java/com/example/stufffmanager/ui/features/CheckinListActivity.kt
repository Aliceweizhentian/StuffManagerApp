package com.example.stufffmanager.ui.features

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanager.logic.network.StuffNetwork
import com.example.stufffmanager.R
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CheckinListActivity : AppCompatActivity() {

    val checkinListViewModl by lazy{ViewModelProvider(this).get(CheckinListViewModel::class.java)}
    lateinit var adapter: CheckinListAdepter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkin_list)

        val toolbar : Toolbar = findViewById(R.id.checkinListToolbar)
        val recyclerView : RecyclerView = findViewById(R.id.checkinListRecyclerview)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = CheckinListAdepter(this, checkinListViewModl.checkinList)
        recyclerView.adapter = adapter

        val scope = lifecycleScope
        scope.launch {
            val deferredResult = async{
                StuffNetwork.getCheckinList()
            }
            val result = deferredResult.await()
            checkinListViewModl.checkinList.clear()
            checkinListViewModl.checkinList.addAll(result.data.attendanceList)
            adapter.notifyDataSetChanged()
        }

        setSupportActionBar(toolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.setTitle("考勤列表")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }
}