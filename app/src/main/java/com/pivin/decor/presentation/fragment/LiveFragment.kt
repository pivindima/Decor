package com.pivin.decor.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.R
import com.pivin.decor.databinding.FragmentLiveBinding
import com.pivin.decor.domain.model.LiveWallpaper
import com.pivin.decor.presentation.ViewModelFactory
import com.pivin.decor.presentation.activity.LiveActivity
import com.pivin.decor.presentation.activity.StaticActivity
import com.pivin.decor.presentation.adapters.LiveAdapter
import com.pivin.decor.presentation.view_models.LiveViewModel
import javax.inject.Inject

class LiveFragment : Fragment() {

    private lateinit var viewModel: LiveViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentLiveBinding? = null
    private val binding: FragmentLiveBinding
        get() = _binding ?: throw RuntimeException("FragmentLiveBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LiveAdapter(requireContext())
        adapter.onLiveClickListener = object : LiveAdapter.OnLiveClickListener {
            override fun onClick(liveWallpaper: LiveWallpaper) {
                startLiveActivity(liveWallpaper.id)
            }
        }
        binding.rvLive.adapter = adapter
        binding.rvLive.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[LiveViewModel::class.java]
        viewModel.liveWallpapersList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun startLiveActivity(id: Long) {
        val intent = LiveActivity.newIntent(requireContext(), id)
        startActivity(intent)
    }

}