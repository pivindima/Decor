package com.pivin.decor.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.pivin.decor.App
import com.pivin.decor.databinding.ActivityLiveBinding
import com.pivin.decor.presentation.ViewModelFactory
import com.pivin.decor.presentation.view_models.LiveViewModel
import javax.inject.Inject


class LiveActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    private val binding by lazy {
        ActivityLiveBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportActionBar?.hide()

        val id = intent.getLongExtra(ID, -1)
        if (id == -1L) finish()

        val exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer.playWhenReady = true
        binding.playerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()

        viewModel = ViewModelProvider(this, viewModelFactory)[LiveViewModel::class.java]
        viewModel.liveWallpaper(id).observe(this) {
            println(it.urlPreview)
            val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(MediaItem.fromUri(it.urlPreview))
            exoPlayer.setMediaSource(mediaSource)
            exoPlayer.seekTo(0)
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()

//            Picasso.get().load(it.urlPreview).into(binding.ivImage)
        }

    }

    companion object {
        private const val ID = "ID"
        fun newIntent(context: Context, id: Long): Intent {
            return Intent(context, LiveActivity::class.java).apply {
                putExtra(ID, id)
            }
        }
    }
}