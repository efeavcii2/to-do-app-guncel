import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.to_do_project.R

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        sendNotification("Hatırlatma", "Uygulamaya geri dönmeyi unutma!")
        return Result.success()
    }

    @SuppressLint("NotificationPermission")
    private fun sendNotification(title: String, message: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "reminder_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
            if (notificationManager?.areNotificationsEnabled() == false) {
                // İzin verilmemişse, kullanıcıyı izin vermesi için
            }
        }

        // Android 8.0 ve sonrası için bir bildirim kanalı
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Reminder Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Bildirim oluştur
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.logooo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Bildirimi gönder
        notificationManager.notify(1, notification)
    }
}