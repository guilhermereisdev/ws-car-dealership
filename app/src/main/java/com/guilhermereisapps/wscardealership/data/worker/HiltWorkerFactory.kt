package com.guilhermereisapps.wscardealership.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class HiltWorkerFactory @Inject constructor(
    private val workerFactories: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<AssistedWorkerFactory<out ListenableWorker>>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundEntry = workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value
        return factoryProvider?.get()?.create(appContext, workerParameters)
    }
}

interface AssistedWorkerFactory<T : ListenableWorker> {
    fun create(appContext: Context, params: WorkerParameters): T
}