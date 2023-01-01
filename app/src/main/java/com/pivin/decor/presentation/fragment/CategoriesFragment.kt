package com.pivin.decor.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.databinding.FragmentCategoriesBinding
import com.pivin.decor.domain.model.Category
import com.pivin.decor.presentation.view_models.CategoryViewModel
import com.pivin.decor.presentation.ViewModelFactory
import com.pivin.decor.presentation.activity.CategoryLiveActivity
import com.pivin.decor.presentation.activity.CategoryStaticActivity
import com.pivin.decor.presentation.adapters.CategoryAdapter
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentCategoriesBinding? = null
    private val binding: FragmentCategoriesBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoriesBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryAdapter(requireContext())
        adapter.onCategoryClickListener = object : CategoryAdapter.OnCategoryClickListener {
            override fun onClick(category: Category) {
                startCategoryDetailActivity(category)
            }
        }
        binding.rvCategories.adapter = adapter
        binding.rvCategories.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[CategoryViewModel::class.java]
        viewModel.categoryList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun startCategoryDetailActivity(category: Category) {
        val intent = if (category.live) CategoryLiveActivity.newIntent(requireContext(), category.id, category.name, category.image) else CategoryStaticActivity.newIntent(requireContext(), category.id, category.name, category.image)
        startActivity(intent)
    }

}