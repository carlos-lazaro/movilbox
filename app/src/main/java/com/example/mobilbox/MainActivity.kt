package com.example.mobilbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobilbox.ui.MovilboxApp
import com.example.mobilbox.ui.theme.MobilboxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilboxTheme {
                MovilboxApp()
            }
        }
    }
}
