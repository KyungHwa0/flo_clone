package com.flo.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.flo.R
import com.flo.data.Album
import com.flo.databinding.FragmentHomeBinding
import com.flo.ui.main.MainActivity
import com.flo.ui.main.album.AlbumFragment
import com.google.gson.Gson

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homePannelAlbumImg01Iv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, AlbumFragment()).commitAllowingStateLoss()
        }

        // Album 데이터 리스트에 데이터 추가
        albumDatas.apply {
            add(Album("Album 1", "Singer 1", R.drawable.img_album_exp1))
            add(Album("Album 2", "Singer 2", R.drawable.img_album_exp2))
            add(Album("Album 3", "Singer 3", R.drawable.img_album_exp3))
        }

        val todayAlbumAdapter = TodayAlbumAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter = todayAlbumAdapter

        todayAlbumAdapter.setMyItemClickListener(object : TodayAlbumAdapter.MyItemClickListener{

            override fun onItemClick(album: Album) {
                changeAlbumFragment(album)
            }
        })

        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val bannerAdapter = BannerViewPagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_01))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_02))
        binding.homeBannerVp.adapter = bannerAdapter
        // 좌우 스크롤
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    private fun changeAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }
}