package com.faster.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.faster.aichat.ui.AiChatApp
import com.faster.aichat.ui.theme.AiChatTheme
import com.faster.aichat.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display for modern Android experience
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AiChatTheme {
                AiChatApp(viewModel = viewModel)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // Auto-save current conversation when app goes to background
        viewModel.autoSave()
    }
}
