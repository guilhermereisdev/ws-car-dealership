package com.guilhermereisapps.wscardealership.data.worker

import android.content.Context
import androidx.work.WorkerParameters
import com.guilhermereisapps.wscardealership.data.remote.api.ApiService
import com.guilhermereisapps.wscardealership.domain.repository.CompraRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CompraWorkerFactory @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val compraRepository: CompraRepository,
    private val apiService: ApiService
) : AssistedWorkerFactory<CompraWorker> {

    override fun create(appContext: Context, params: WorkerParameters): CompraWorker {
        return CompraWorker(appContext, params, compraRepository, apiService)
    }

    @AssistedFactory
    interface Factory : AssistedWorkerFactory<CompraWorker>
}
