package com.twmaze.movies.util

import com.twmaze.movies.data.movies.remote.dto.EpisodeResponse
import com.twmaze.movies.data.movies.remote.dto.ImageResponse
import com.twmaze.movies.data.movies.remote.dto.RatingResponse
import com.twmaze.movies.data.movies.remote.dto.ShowResponse
import com.twmaze.movies.domain.shows.entity.Episode
import com.twmaze.movies.domain.shows.entity.Image
import com.twmaze.movies.domain.shows.entity.Rating
import com.twmaze.movies.domain.shows.entity.Show

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