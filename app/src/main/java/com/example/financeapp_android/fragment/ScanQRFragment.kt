package com.example.financeapp_android.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.financeapp_android.R
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import com.google.zxing.BarcodeFormat

class ScanQRFragment : Fragment() {

    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var scannerLine: View
    private lateinit var flashlightButton: Button
    private lateinit var animation: TranslateAnimation
    private val CAMERA_PERMISSION_REQUEST_CODE = 101
    private var isFlashlightOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan_qr, container, false)

        // Initialize barcode scanner view
        barcodeScannerView = view.findViewById(R.id.barcode_scanner)
        scannerLine = view.findViewById(R.id.scanner_line)
        flashlightButton = view.findViewById(R.id.flashlightButton)


        // Request camera permission or setup scanner if permission is granted
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            setupScanner() // Initialize scanner if permission is already granted
        }

        // Setup the scanner line animation
        setupScannerAnimation()

        // Set up flashlight button click listener
        flashlightButton.setOnClickListener {
            toggleFlashlight()
        }

        return view
    }

    private fun setupScanner() {
        // Only recognize QR codes
        val formats = listOf(BarcodeFormat.QR_CODE)
        barcodeScannerView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeScannerView.decodeContinuous(callback)
    }

    private fun setupScannerAnimation() {
        animation = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 1f
        ).apply {
            duration = 1500
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }
    }

    private fun toggleFlashlight() {
        if (isFlashlightOn) {
            barcodeScannerView.setTorchOff() // Turn off the flashlight
            isFlashlightOn = false
            flashlightButton.text = "Turn On Flashlight"
        } else {
            barcodeScannerView.setTorchOn() // Turn on the flashlight
            isFlashlightOn = true
            flashlightButton.text = "Turn Off Flashlight"
        }
    }

    private val callback = com.journeyapps.barcodescanner.BarcodeCallback { result ->
        if (result.text != null) {
            // Handle the scanned result here
            barcodeScannerView.pause() // Pause scanning after a successful scan
            // Process the QR code data here (e.g., navigate, display, etc.)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupScanner() // Permission granted, setup the scanner
            } else {
                // Handle permission denial (e.g., show a message to the user)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeScannerView.resume() // Ensure the scanner resumes

        // Start or restart the scanner animation
        scannerLine.startAnimation(animation)
    }

    override fun onPause() {
        super.onPause()
        barcodeScannerView.pause() // Pause the scanner when the fragment is not visible

        // Stop the scanner animation
        scannerLine.clearAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        barcodeScannerView.pause() // Ensure the scanner is paused
    }
}
