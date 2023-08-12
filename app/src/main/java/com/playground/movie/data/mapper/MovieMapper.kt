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


    override fun responseToUIModel(entity: Search): MovieDetailUI {
        return MovieDetailUI(
            title = entity.title,
            body = entity.type,
            image = entity.poster,
            year = entity.year,
            imdb = entity.imdb
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