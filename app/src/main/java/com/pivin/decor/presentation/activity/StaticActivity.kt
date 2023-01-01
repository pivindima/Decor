package com.pivin.decor.presentation.activity

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pivin.decor.App
import com.pivin.decor.R
import com.pivin.decor.databinding.ActivityStaticBinding
import com.pivin.decor.presentation.ViewModelFactory
import com.pivin.decor.presentation.view_models.StaticViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


class StaticActivity : AppCompatActivity() {

    private lateinit var viewModel: StaticViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    private val binding by lazy {
        ActivityStaticBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val progressAnimation = AnimationUtils.loadAnimation(this, R.anim.progress)
        startAnimation(binding.ivProgress, progressAnimation)

//        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.btnApply) { view, insets ->
            val systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            (view.layoutParams as MarginLayoutParams).setMargins(view.marginStart, view.marginTop, view.marginEnd, systemBarInsets.bottom)
            view.requestLayout()
            insets
        }

        supportActionBar?.hide()

        val id = intent.getLongExtra(ID, -1)
        if (id == -1L) finish()

        viewModel = ViewModelProvider(this, viewModelFactory)[StaticViewModel::class.java]
        viewModel.staticWallpaper(id).observe(this) {
            Picasso.get().load(it.urlPreview).into(binding.ivImage, object : Callback {
                override fun onSuccess() {
                    stopAnimation(binding.ivProgress)
                    binding.btnApply.visibility = View.VISIBLE
                }
                override fun onError(e: Exception?) {
                    stopAnimation(binding.ivProgress)
                    Toast.makeText(this@StaticActivity, getString(R.string.error_image_loading), Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.btnApply.setOnClickListener {
            startAnimation(binding.ivProgress, progressAnimation)
            viewModel.staticWallpaper(id).observe(this) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val bitmap = Picasso.get().load(it.urlPreview).get()
                    val wallpaperManager = WallpaperManager.getInstance(this@StaticActivity)
                    wallpaperManager.setBitmap(bitmap)
                    withContext(Dispatchers.Main) {
                        stopAnimation(binding.ivProgress)
                        Toast.makeText(this@StaticActivity, getString(R.string.wallpaper_successfully_changed), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun startAnimation(view: View, animation: Animation) {
        view.visibility = View.VISIBLE
        view.startAnimation(animation)
    }

    private fun stopAnimation(view: View) {
        view.clearAnimation()
        view.visibility = View.GONE
    }

    companion object {
        private const val ID = "ID"
        fun newIntent(context: Context, id: Long): Intent {
            return Intent(context, StaticActivity::class.java).apply {
                putExtra(ID, id)
            }
        }
    }
}