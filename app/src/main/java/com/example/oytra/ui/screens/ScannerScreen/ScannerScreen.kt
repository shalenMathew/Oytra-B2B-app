package com.example.oytra.ui.screens.ScannerScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.oytra.util.barcode.BarcodeAnalyzer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen(
    paddingValues: PaddingValues,
    vm: ScannerViewModel = hiltViewModel()
){

    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    var isScanning by remember{
        mutableStateOf(false)
    }

//    var scannedResult by remember { mutableStateOf<String?>(null) }

    var scanResult: ScannerResult? by remember { mutableStateOf<ScannerResult?>(null) }

    BackHandler(enabled = isScanning) {
        isScanning = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {


        if (isScanning && cameraPermission.status.isGranted){
           CameraPreview(onBarcodeDetected = { result ->
//               scannedResult = result

              scanResult = vm.evaluateBarcode(result)
               isScanning = false
           })
        }else{

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.Center) {

                if (scanResult!=null) {

                    Card(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (scanResult!!.isValid) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = scanResult!!.status, fontSize = 20.sp,color = Color.Black)
                            Spacer(modifier = Modifier.height(8.dp))

                            if (scanResult!!.isValid) {
                                Text(text = "Product: ${scanResult!!.productName}", style = MaterialTheme.typography.bodyLarge,color = Color.Black)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = "Barcode: ${scanResult!!.barcode}", style = MaterialTheme.typography.bodyLarge,color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                Button(onClick = {
                    if (cameraPermission.status.isGranted) isScanning = true
                    else cameraPermission.launchPermissionRequest()
                }) {
                    Text(text = "Scan Barcode", fontSize = 15.sp, color = Color.Black)


                }

            }

        }
    }
}

@Composable
fun CameraPreview(onBarcodeDetected:(String)-> Unit) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }


    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val executor = ContextCompat.getMainExecutor(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()


                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }


                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(executor, BarcodeAnalyzer { result ->
                            onBarcodeDetected(result)
                        })
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.e("testing", "Binding failed", e)
                }
            }, executor)
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )

}


