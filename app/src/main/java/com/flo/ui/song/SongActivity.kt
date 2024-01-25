package com.flo.ui.song

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.flo.R
import com.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.songDownIb.setOnClickListener { 
            finish()
        }
        
        binding.songMiniplayerIv.setOnClickListener { 
            setPlayerStatus(false)
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }
    }

    fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }
}