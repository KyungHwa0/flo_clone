package com.flo.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flo.R
import com.flo.data.Song
import com.flo.databinding.ActivityMainBinding
import com.flo.ui.main.home.HomeFragment
import com.flo.ui.main.locker.LockerFragment
import com.flo.ui.main.look.LookFragment
import com.flo.ui.main.search.SearchFragment
import com.flo.ui.song.SongActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Flo_clone)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()

        val song = Song(binding.mainMiniplayerTitleTv.text.toString(), binding.mainMiniplayerSingerTv.text.toString(), 0, 60, false, "star_sandy_beach")

        binding.mainPlayerLayout.setOnClickListener {
//            startActivity(Intent(this, SongActivity::class.java))
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            intent.putExtra("music", song.music)
            startActivity(intent)
        }
    }

    private fun initBottomNavigation() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}