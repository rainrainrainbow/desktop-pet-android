package com.deskpet.overlay

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.view.WindowManager.LayoutParams.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat

class PetOverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: ComposeView
    private var params: WindowManager.LayoutParams? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
        showOverlay()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        removeOverlay()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "桌面宠物",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "桌面宠物悬浮窗服务"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("桌面宠物")
            .setContentText("小宠物正在桌面上陪伴你 🐾")
            .setSmallIcon(android.R.drawable.ic_menu_animal)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun showOverlay() {
        overlayView = ComposeView(this).apply {
            setContent {
                PetOverlayContent(
                    onDoubleClick = { stopSelf() }
                )
            }
        }

        params = WindowManager.LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                TYPE_APPLICATION_OVERLAY
            else
                TYPE_PHONE,
            FLAG_NOT_FOCUSABLE or FLAG_NOT_TOUCH_MODAL or FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.START or Gravity.TOP
            x = 100
            y = 300
        }

        try {
            windowManager.addView(overlayView, params)
        } catch (e: Exception) {
            // Permission not granted
            stopSelf()
        }
    }

    private fun removeOverlay() {
        try {
            if (::overlayView.isInitialized) {
                windowManager.removeView(overlayView)
            }
        } catch (e: Exception) {
            // Ignore
        }
    }

    companion object {
        private const val CHANNEL_ID = "desk_pet_channel"
        private const val NOTIFICATION_ID = 1001
    }
}

@Composable
fun PetOverlayContent(
    onDoubleClick: () -> Unit = {}
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var isHappy by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(80.dp)
            .offset(x = offsetX.dp, y = offsetY.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .clip(CircleShape)
            .shadow(8.dp, CircleShape)
            .background(
                if (isHappy) Color(0xFFFFB3C6) else Color(0xFFA8D8EA)
            )
            .clickable {
                isHappy = !isHappy
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isHappy) "😺" else "😴",
            fontSize = 36.sp
        )
    }
}
