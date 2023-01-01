package com.pivin.decor.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.R
import com.pivin.decor.databinding.ActivityCategoryLiveBinding
import com.pivin.decor.databinding.ActivityMainBinding
import com.pivin.decor.domain.model.LiveWallpaper
import com.pivin.decor.presentation.ViewModelFactory
import com.pivin.decor.presentation.adapters.LiveAdapter
import com.pivin.decor.presentation.view_models.LiveViewModel
import javax.inject.Inject

class CategoryLiveActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    private val binding by lazy {
        ActivityCategoryLiveBinding.inflate(layoutInflater)
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
        supportActionBar?.subtitle = "Live"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = LiveAdapter(this)
        adapter.onLiveClickListener = object : LiveAdapter.OnLiveClickListener {
            override fun onClick(liveWallpaper: LiveWallpaper) {
                startLiveActivity(liveWallpaper.id)
            }
        }
        binding.rvLive.adapter = adapter
        binding.rvLive.itemAnimator = null
        viewModel = ViewModelProvider(this, viewModelFactory)[LiveViewModel::class.java]
        viewModel.liveWallpapersByCategory(categoryId, categoryImage!!).observe(this) {
            adapter.submitList(it)
        }
    }

    private fun startLiveActivity(id: Long) {
        val intent = LiveActivity.newIntent(this, id)
        startActivity(intent)
    }

    companion object {
        private const val CATEGORY_ID = "CATEGORY_ID"
        private const val CATEGORY_TITLE = "CATEGORY_TITLE"
        private const val CATEGORY_IMAGE = "CATEGORY_IMAGE"
        fun newIntent(context: Context, id: Long, title: String, image: String): Intent {
            return Intent(context, CategoryLiveActivity::class.java).apply {
                putExtra(CATEGORY_ID, id)
                putExtra(CATEGORY_TITLE, title)
                putExtra(CATEGORY_IMAGE, image)
            }
        }
    }
}