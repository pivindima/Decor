package com.pivin.decor.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.databinding.FragmentStaticBinding
import com.pivin.decor.domain.model.StaticWallpaper
import com.pivin.decor.presentation.activity.StaticActivity
import com.pivin.decor.presentation.adapters.StaticAdapter
import com.pivin.decor.presentation.view_models.StaticViewModel
import com.pivin.decor.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class StaticFragment : Fragment() {

    private lateinit var viewModel: StaticViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentStaticBinding? = null
    private val binding: FragmentStaticBinding
        get() = _binding ?: throw RuntimeException("FragmentStaticBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStaticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StaticAdapter(requireContext())
        adapter.onStaticClickListener = object : StaticAdapter.OnStaticClickListener {
            override fun onClick(staticWallpaper: StaticWallpaper) {
                startStaticActivity(staticWallpaper.id)
            }
        }
        binding.rvStatic.adapter = adapter
        binding.rvStatic.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[StaticViewModel::class.java]
        viewModel.staticWallpapersList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun startStaticActivity(id: Long) {
        val intent = StaticActivity.newIntent(requireContext(), id)
        startActivity(intent)
    }

}