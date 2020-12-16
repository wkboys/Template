package com.template.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadLogWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    init {

    }
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        val string = inputData.getString("input_data")
        var ouputData:Data=Data.Builder().put("outputData","Task Success").build()

        return Result.success(ouputData)
    }
}