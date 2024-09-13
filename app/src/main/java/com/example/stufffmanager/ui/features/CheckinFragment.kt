package com.example.stufffmanager.ui.features

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.stufffmanager.R
import com.example.stufffmanager.StufffManageApplication


class CheckinFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkin, container, false)
    }

    override fun onResume() {
        super.onResume()
        val checkinlist : ImageView = requireView().findViewById(R.id.CheckInListImg)
        val chekinmanage : ImageView = requireView().findViewById(R.id.CheckInManaGerImg)
        val checkincheck :ImageView = requireView().findViewById(R.id.CheckInCheckImg)

        checkinlist.setOnClickListener{
            val intent = Intent(StufffManageApplication.context,CheckinListActivity::class.java)
            startActivity(intent)
        }

        chekinmanage.setOnClickListener{
            val intent = Intent(StufffManageApplication.context,CheckinManageActivity::class.java)
            startActivity(intent)

        }

        checkincheck.setOnClickListener{
            val intent = Intent(StufffManageApplication.context,CheckinCheckActivity::class.java)
            startActivity(intent)

        }
    }

}