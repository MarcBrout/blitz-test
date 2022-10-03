package com.test.blitz.data.mappers

import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.domain.models.Statistics

fun StatisticsDTO.toStatistics() = Statistics(
    id = id,
    downloads = downloads,
    views = views,
    likes = likes,
)