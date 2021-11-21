package com.twmaze.core.dto.local

import com.twmaze.core.dto.remote.EpisodeResponse
import com.twmaze.core.dto.remote.ImageResponse
import com.twmaze.core.dto.remote.RatingResponse
import com.twmaze.core.dto.remote.ShowResponse

fun ImageResponse.toImage(): Image {
    return Image(
        medium = medium,
        original = original
    )
}

fun RatingResponse.toRating(): Rating {
    return Rating(
        average = average,
    )
}

fun ShowResponse.toShow(): Show {
    return Show(
        id = id,
        url = url,
        name = name,
        type = type,
        genres = genres,
        language = language,
        status = status,
        runtime = runtime,
        averageRuntime = averageRuntime,
        premiered = premiered,
        ended = ended,
        rating = rating.toRating(),
        image = image.toImage(),
        summary = summary,
    )
}

fun EpisodeResponse.toEpisode(): Episode {
    return Episode(
        id = id,
        url = url,
        name = name,
        type = type,
        number = number,
        season = season,
        runtime = runtime,
        rating = rating.toRating(),
        image = image.toImage(),
        summary = summary,
    )
}