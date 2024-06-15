package com.guilhermereisapps.wscardealership.data.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.guilhermereisapps.wscardealership.R
import com.guilhermereisapps.wscardealership.data.model.Compra
import com.guilhermereisapps.wscardealership.data.remote.api.ApiService
import com.guilhermereisapps.wscardealership.domain.repository.CompraRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

@HiltWorker
class CompraWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val compraRepository: CompraRepository,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val compras = compraRepository.getAllCompras()
            if (compras.isNotEmpty()) {
                sendCompras(compras)
            }
            showNotification("Envio de Compras", "Dados enviados com sucesso! Em 15 minutos serão enviados novamente.")
            Result.success()
        } catch (e: Exception) {
            showNotification("Envio de Compras", "Falha ao enviar dados.")
            Result.retry()
        }
    }

    private suspend fun sendCompras(compras: List<Compra>) {
        val client = OkHttpClient()
        val gson = Gson()
        val json = gson.toJson(compras)
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body: RequestBody = json.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://www.wswork.com.br/cars/leads")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException(response.toString())
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(applicationContext, "COMPRA_WORKER_CHANNEL")
            .setSmallIcon(R.drawable.ic_car)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(1, builder.build())
        }
    }
}