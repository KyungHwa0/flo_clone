package com.flo.ui.main.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flo.R
import com.flo.databinding.FragmentHomeBinding
import com.flo.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {
    private lateinit var binding : FragmentLockerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)
        return binding.root
    }
}