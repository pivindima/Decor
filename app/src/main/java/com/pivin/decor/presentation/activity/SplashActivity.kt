package com.pivin.decor.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.pivin.decor.App
import com.pivin.decor.databinding.ActivitySplashBinding
import com.pivin.decor.presentation.view_models.SplashViewModel
import com.pivin.decor.presentation.view_models.ViewModelFactory
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
        viewModel.liveData.observe(this) {
            startMainActivity()
        }
        viewModel.checkVersionDb()

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                viewModel.animationFinished()
            }
        })

    }

    private fun startMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}