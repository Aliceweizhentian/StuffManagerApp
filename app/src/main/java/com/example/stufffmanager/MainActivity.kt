package com.example.stufffmanager

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stufffmanager.ui.features.FeaturesFragment
import com.example.stufffmanager.ui.home.HomeFragment
import com.example.stufffmanager.ui.profile.LoginFragment
import com.example.stufffmanager.ui.profile.LoginViewModel
import com.example.stufffmanager.ui.profile.UserProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val loginviewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navview : NavigationView = findViewById(R.id.navView)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        //召唤toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.person_off_24dp)
        }
        supportActionBar?.let {
            setTitle("公告")
        }

        //设置侧边导航栏
        navview.setCheckedItem(R.id.login_navMeu)
        navview.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawers()
            when(item.itemId){
                R.id.login_navMeu->{
                    Log.d("cici","denglu")}
                R.id.question->{
                    Log.d("cici","help")}
            }
            true
        }

        fun goToProfile(){
            if(loginviewmodel.getLoginFlag())
                supportActionBar?.let {
                    setTitle("个人资料")
                    showFragment(UserProfileFragment())
                }else{
                    setTitle("登录")
                    showFragment(LoginFragment())
            }

        }

        fun goToHome(){
            supportActionBar?.let {
                setTitle("公告")
            }
            showFragment(HomeFragment())
        }

        fun goToFeatures(){
            supportActionBar?.let {
                setTitle("功能")
            }
            showFragment(FeaturesFragment())
        }


        //底部导航栏的点击事件
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.profile->goToProfile()
                R.id.bottom_home->goToHome()
                R.id.power->goToFeatures()
            }
            true

        }




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        when (item.itemId) {
            android.R.id.home-> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    //切换fragment
    //fragmentTransaction.replace的工作原理是：替换容器里的Fragment类，在这里
    //容器指的是R.id.homeFragment，这个定义在mainActivity.xml里的组件
    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.homeFragment)
//        if (currentFragment != null) {
//            fragmentTransaction.hide(currentFragment)
//        }
//        fragmentTransaction.add(R.id.homeFragment, fragment)
        fragmentTransaction.replace(R.id.homeFragment,fragment)
        fragmentTransaction.commit()
    }
}