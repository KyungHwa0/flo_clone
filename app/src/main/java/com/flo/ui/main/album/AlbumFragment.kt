package com.flo.ui.main.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flo.R
import com.flo.data.Album
import com.flo.databinding.FragmentAlbumBinding
import com.flo.ui.main.MainActivity
import com.flo.ui.main.home.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()

    private val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        val albumData = arguments?.getString("album")
        val gson = Gson()

        val album = gson.fromJson(albumData, Album::class.java)

        setViews(album)
        initViewPager()
        setClickListeners()

        return binding.root
    }

    private fun setViews(album: Album) {
        binding.albumMusicTitleTv.text = album.title.toString()
        binding.albumSingerNameTv.text = album.singer.toString()
        binding.albumAlbumIv.setImageResource(album.coverImg!!)
    }

    private fun initViewPager() {
        //init viewpager
        val albumAdapter = AlbumVPAdapter(this)

        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tab, position ->
            tab.text = information[position]
        }.attach()
    }

    private fun setClickListeners() {
        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }
    }

}