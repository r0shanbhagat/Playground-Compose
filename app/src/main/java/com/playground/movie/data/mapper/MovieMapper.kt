package com.playground.movie.data.mapper

import com.playground.movie.contract.ResponseMapper
import com.playground.movie.data.dto.MovieDetailUI
import com.playground.movie.data.dto.Search
import com.playground.movie.data.dto.SearchResultResponse


/**
 * @Details MovieMapper
 * @Author Roshan Bhagat
 *
 * @constructor
 */
class MovieMapper : ResponseMapper<Search, MovieDetailUI> {


    override fun responseToUIModel(response: Search): MovieDetailUI {
        return MovieDetailUI(
            title = response.title,
            body = response.type,
            image = response.poster,
            year = response.year,
            imdb = response.imdb
        )
    }

    /**
     * Map from entity list
     *
     * @param entities
     * @return
     */
    fun mapFromSearchResponse(entities: SearchResultResponse?): List<MovieDetailUI> {
        return entities?.searches?.map {
            responseToUIModel(it)
        } ?: emptyList()
    }

}