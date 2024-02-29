package com.vicksam.ferapp

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaActionSound
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Audio
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.size.SizeSelectors
import com.vicksam.ferapp.db.guidance.Guidance
import com.vicksam.ferapp.db.guidance.GuidanceViewModel
import com.vicksam.ferapp.db.guidance.GuidanceViewModelFactory
import com.vicksam.ferapp.db.history.History
import com.vicksam.ferapp.db.history.HistoryViewModel
import com.vicksam.ferapp.db.history.HistoryViewModelFactory
import com.vicksam.ferapp.fer.FerModel
import com.vicksam.ferapp.fer.FerViewModel
import com.vicksam.ferapp.functions.byteArrayToBitmap
import com.vicksam.ferapp.functions.getCurrentDateTime
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

        val historyViewModel: HistoryViewModel = ViewModelProvider(this, HistoryViewModelFactory(application))[HistoryViewModel::class.java]
        val guidanceViewModel: GuidanceViewModel = ViewModelProvider(this,
            GuidanceViewModelFactory(application))[GuidanceViewModel::class.java]
        if(guidanceViewModel.allGuidance.value.isNullOrEmpty()){
            guidanceViewModel.insertGuidance(Guidance( guidanceText = "Enjoy the moment!", emotionType = "happy"))
            guidanceViewModel.insertGuidance(Guidance( guidanceText = "Take a deep breath and count to ten before reacting.", emotionType = "anger"))
            guidanceViewModel.insertGuidance(Guidance( guidanceText = "Observe the situation objectively.", emotionType = "neutral"))
            guidanceViewModel.insertGuidance(Guidance( guidanceText = "Take a moment to process what just happened.", emotionType = "surprised"))

        }

        viewfinder = findViewById(R.id.viewfinder)
        faceBoundsOverlay = findViewById(R.id.faceBoundsOverlay)

        val toggleCameraBtn = findViewById<FloatingActionButton>(R.id.toggleCameraButton)
        val profileBtn = findViewById<FloatingActionButton>(R.id.profileButtonId)
        val captureCameraBtn = findViewById<FloatingActionButton>(R.id.captureButtonId)
        val lensFacing = savedInstanceState?.getSerializable(KEY_LENS_FACING) as Facing? ?: Facing.BACK
        setupCamera(lensFacing,toggleCameraBtn)
        FerModel.load(this)
        setupObservers()

        profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        captureCameraBtn.setOnClickListener {
            viewfinder.mode = Mode.PICTURE
            viewfinder.takePictureSnapshot()
            val mediaActionSound = MediaActionSound()
            mediaActionSound.play(MediaActionSound.SHUTTER_CLICK)

            viewfinder.addCameraListener(object : CameraListener(){
                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                    val byteArrayImage = result.data
                    val bitmapImage = byteArrayToBitmap(byteArrayImage)
                    val uid = (0..10).random()
                    val emotion = bitmapImage?.let { it1 -> FerModel.classify(it1) }

                    val history = emotion?.let { it1 ->
                        var guidanceId:Int = 0
                        if(it1 == "happy"){
                            guidanceId = 1
                        }
                        else if(it1 == "anger"){
                            guidanceId = 2
                        }
                        else if(it1 == "neutral"){
                            guidanceId = 3
                        }
                        else if(it1 == "surprised"){
                            guidanceId = 4
                        }
                        History(
                            historyId = uid,
                            dateTime = getCurrentDateTime(),
                            userId = 2,
                            expressionType = 3,
                            capturedFace = byteArrayImage,
                            emotion = it1,
                            guidanceId =guidanceId
                        )
                    }

                    if (history != null) {
                        historyViewModel.insertHistory(history = history)
                    }
                    Toast.makeText(this@MainActivity, "Snapshot Taken", Toast.LENGTH_SHORT).show()
                }
            })
        }
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


    private fun setupCamera(
        lensFacing: Facing,
        toggleCameraButton: FloatingActionButton
    ) {
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