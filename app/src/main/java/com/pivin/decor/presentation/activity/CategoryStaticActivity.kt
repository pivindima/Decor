package com.pivin.decor.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.databinding.ActivityCategoryStaticBinding
import com.pivin.decor.domain.model.StaticWallpaper
import com.pivin.decor.presentation.adapters.StaticAdapter
import com.pivin.decor.presentation.view_models.StaticViewModel
import com.pivin.decor.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class CategoryStaticActivity : AppCompatActivity() {

    private lateinit var viewModel: StaticViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    private val binding by lazy {
        ActivityCategoryStaticBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val categoryId = intent.getLongExtra(CATEGORY_ID, -1)
        if (categoryId == -1L) finish()

        val categoryTitle = intent.getStringExtra(CATEGORY_TITLE)
        if (categoryTitle.isNullOrBlank()) finish()

        val categoryImage = intent.getStringExtra(CATEGORY_IMAGE)
        if (categoryImage.isNullOrBlank()) finish()

        supportActionBar?.title = categoryTitle
        supportActionBar?.subtitle = "Static"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = StaticAdapter(this)
        adapter.onStaticClickListener = object : StaticAdapter.OnStaticClickListener {
            override fun onClick(staticWallpaper: StaticWallpaper) {
                startStaticActivity(staticWallpaper.id)
            }
        }
        binding.rvStatic.adapter = adapter
        binding.rvStatic.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[StaticViewModel::class.java]
        viewModel.staticWallpapersByCategory(categoryId, categoryImage!!).observe(this) {
            adapter.submitList(it)
        }
    }

    private fun startStaticActivity(id: Long) {
        val intent = StaticActivity.newIntent(this, id)
        startActivity(intent)
    }

    companion object {
        private const val CATEGORY_ID = "CATEGORY_ID"
        private const val CATEGORY_TITLE = "CATEGORY_TITLE"
        private const val CATEGORY_IMAGE = "CATEGORY_IMAGE"
        fun newIntent(context: Context, id: Long, title: String, image: String): Intent {
            return Intent(context, CategoryStaticActivity::class.java).apply {
                putExtra(CATEGORY_ID, id)
                putExtra(CATEGORY_TITLE, title)
                putExtra(CATEGORY_IMAGE, image)
            }
        }
    }
}