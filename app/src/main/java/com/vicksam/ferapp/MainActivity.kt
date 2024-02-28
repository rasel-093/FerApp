package com.vicksam.ferapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Size
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.controls.Audio
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.size.SizeSelectors
import com.vicksam.ferapp.fer.FerModel
import com.vicksam.ferapp.fer.FerViewModel
import husaynhakeem.io.facedetector.FaceBounds
import husaynhakeem.io.facedetector.FaceBoundsOverlay
import husaynhakeem.io.facedetector.FaceDetector
import husaynhakeem.io.facedetector.Frame
import husaynhakeem.io.facedetector.LensFacing

class MainActivity : AppCompatActivity() {

    private lateinit var viewfinder: CameraView // Assuming CameraView is the type of your viewfinder
    private lateinit var faceBoundsOverlay:FaceBoundsOverlay
    private val viewModel = FerViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewfinder = findViewById(R.id.viewfinder)
        faceBoundsOverlay = findViewById(R.id.faceBoundsOverlay)
        var toggleCameraButton = findViewById<FloatingActionButton>(R.id.toggleCameraButton)
        val profileButtonId = findViewById<FloatingActionButton>(R.id.profileButtonId)

        profileButtonId.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val lensFacing = savedInstanceState?.getSerializable(KEY_LENS_FACING) as Facing? ?: Facing.BACK
        setupCamera(lensFacing,toggleCameraButton)

        FerModel.load(this)

        setupObservers()
    }

    override fun onStart() {
        super.onStart()
        viewfinder.open()
    }

    override fun onStop() {
        super.onStop()
        viewfinder.close()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY_LENS_FACING, viewfinder.facing)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewfinder.destroy()
    }

    private fun setupObservers() {
        viewModel.emotionLabels().observe(this) {
            it?.let { faceBoundsOverlay.updateEmotionLabels(it) }
        }
    }


    private fun setupCamera(lensFacing: Facing, toggleCameraButton: FloatingActionButton) {
        val faceDetector = FaceDetector(faceBoundsOverlay).also { it.setup() }

        viewfinder.facing = lensFacing
        viewfinder.setPreviewStreamSize(SizeSelectors.maxWidth(MAX_PREVIEW_WIDTH))
        viewfinder.audio = Audio.OFF

        viewfinder.addFrameProcessor {
            faceDetector.process(
                Frame(
                    data = it.data,
                    rotation = it.rotation,
                    size = Size(it.size.width, it.size.height),
                    format = it.format,
                    lensFacing = if (viewfinder.facing == Facing.BACK) LensFacing.BACK else LensFacing.FRONT
                )
            )
        }

        toggleCameraButton.setOnClickListener {
            viewfinder.toggleFacing()
        }
    }

    private fun FaceDetector.setup() {
        setOnFaceDetectionListener(object : FaceDetector.OnFaceDetectionResultListener {
            override fun onSuccess(faceBounds: List<FaceBounds>, faceBitmaps: List<Bitmap>) {
                viewModel.onFacesDetected(faceBounds, faceBitmaps)
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_LENS_FACING = "key-lens-facing"
        private const val MAX_PREVIEW_WIDTH = 480
    }
}