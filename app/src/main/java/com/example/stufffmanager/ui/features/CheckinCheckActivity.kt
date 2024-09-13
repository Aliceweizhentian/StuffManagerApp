package com.example.stufffmanager.ui.features

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanager.logic.network.StuffNetwork
import com.example.stufffmanager.R
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CheckinCheckActivity : AppCompatActivity() {

    val checkinListViewModl by lazy{ ViewModelProvider(this).get(CheckinListViewModel::class.java)}
    lateinit var adapter: CheckinCheckAdepter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkin_check)

        val toolbar : Toolbar = findViewById(R.id.checkinCheckToolbar)
        val recyclerView : RecyclerView = findViewById(R.id.checkinCheckRecyclerview)
        val layoutManager = LinearLayoutManager(this)


        recyclerView.layoutManager = layoutManager
        adapter = CheckinCheckAdepter(this, checkinListViewModl.checkinList,lifecycleScope)
        recyclerView.adapter = adapter

        val scope = lifecycleScope
        scope.launch {
            val deferredResult = async{
                StuffNetwork.getCheckinList()
            }
            val result = deferredResult.await()
            checkinListViewModl.checkinList.clear()
            checkinListViewModl.checkinList.addAll(result.data.attendanceList)
            checkinListViewModl.checkinList.filter { it.audit == "0" }
            Log.d("cici","checkcheckok${result.data.attendanceList}")
            adapter.notifyDataSetChanged()
        }


        setSupportActionBar(toolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.setTitle("考勤审批")
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