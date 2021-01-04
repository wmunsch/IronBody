package com.williammunsch.ironbody

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.williammunsch.ironbody.databinding.ActivityMainBinding
import com.williammunsch.ironbody.ui.main.MainViewModel
import com.williammunsch.ironbody.ui.main.MainViewModelFactory
import com.williammunsch.ironbody.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as WorkoutApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewPager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.mainviewmodel = mainViewModel

        //ScrollView workoutScrollView = findViewById(R.id.workout_scrollview);
    }
}