package com.digital.playground.data.mapper

import com.playground.movie.contract.EntityMapper
import com.playground.movie.data.dto.MovieModel
import com.playground.movie.data.dto.Search
import com.playground.movie.data.dto.SearchResults


/**
 * @Details MovieMapper
 * @Author Roshan Bhagat
 *
 * @constructor
 */
class MovieMapper : EntityMapper<Search, MovieModel> {

    override fun mapFromEntity(entity: Search): MovieModel {
        return MovieModel(
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
    fun mapFromEntityList(entities: SearchResults?): List<MovieModel> {
        return entities?.searches?.map {
            mapFromEntity(it)
        } ?: emptyList()
    }

}