package com.test.blitz.data.mappers

import android.util.Log
import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.domain.models.Statistics

fun StatisticsDTO.toStatistics() = Statistics(
    id = id,
    downloads = downloads.total,
    views = views.total,
    likes = likes.total,
)