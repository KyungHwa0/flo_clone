package com.flo.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.flo.R
import com.flo.data.Song
import com.flo.data.SongDatabase
import com.flo.databinding.ActivityMainBinding
import com.flo.ui.main.home.HomeFragment
import com.flo.ui.main.locker.LockerFragment
import com.flo.ui.main.look.LookFragment
import com.flo.ui.main.search.SearchFragment
import com.flo.ui.song.SongActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var song:Song = Song()
    private var gson : Gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Flo_clone)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputDummySongs()
        initBottomNavigation()

//        val song = Song(binding.mainMiniplayerTitleTv.text.toString(), binding.mainMiniplayerSingerTv.text.toString(), 0, 60, false, "star_sandy_beach")

        binding.mainPlayerLayout.setOnClickListener {
            val editor = getSharedPreferences("song", MODE_PRIVATE).edit()
            editor.putInt("songId",song.id)
            editor.apply()

            val intent = Intent(this,SongActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val songId = sharedPreferences.getInt("songId", 0)

        val songDB = SongDatabase.getInstance(this)!!

        song = if (songId == 0) {
            songDB.songDao().getSong(1)
        }else {
            songDB.songDao().getSong(songId)
        }

        setMiniPlayer(song)
    }

    private fun setMiniPlayer(song: Song) {
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainMiniplayerProgressSb.progress = (song.second*100000)/song.playTime
    }

    private fun inputDummySongs() {
        val songDB = SongDatabase.getInstance(this)!!
        val songs = songDB.songDao().getSongs()

        if (songs.isNotEmpty()) return

        songDB.songDao().insert(
            Song(
                "클럽 하트비트(Club Heartbeat)",
                "로스트아크",
                0,
                144,
                false,
                "club_heartbeat",
                R.drawable.img_album_exp1,
                false
            )
        )

        songDB.songDao().insert(
            Song(
                "리베하임(Liebeheim)",
                "로스트아크",
                0,
                127,
                false,
                "liebeheim",
                R.drawable.img_album_exp2,
                false
            )
        )

        songDB.songDao().insert(
            Song(
                "그대 기억 하나요?",
                "로스트아크",
                0,
                180,
                false,
                "oe_e_hoomanao",
                R.drawable.img_album_exp3,
                false
            )
        )

        songDB.songDao().insert(
            Song(
                "테일 오브 플레체(Tale of Pletze)",
                "로스트아크",
                0,
                176,
                false,
                "tale_of_pletze",
                R.drawable.img_album_exp4,
                false
            )
        )

        songDB.songDao().insert(
            Song(
                "로맨틱 웨폰",
                "로스트아크",
                0,
                174,
                false,
                "romantic_weapon_musical_ver",
                R.drawable.img_album_exp5,
                false
            )
        )

        songDB.songDao().insert(
            Song(
                "별빛 등대의 섬",
                "로스트아크",
                0,
                112,
                false,
                "starlight_island",
                R.drawable.img_album_exp6,
                false
            )
        )

        val _songs = songDB.songDao().getSongs()
        Log.d("DB data", _songs.toString())
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