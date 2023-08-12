package com.playground.movie.data

import com.playground.movie.contract.MovieDataSource
import com.playground.movie.contract.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * @Details BlogRepository
 * @Author Roshan Bhagat
 * @property ioDispatcher
 * @constructor Create [MoviesRepositoryImpl]
 */
class MoviesRepositoryImpl(
    private val remoteDataSource: MovieDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    /**
     * The app doesn't need that much information about the movies because it only displays the
     * content of the movies on the screen, along with basic information about its year of release
     * and rating. It's a good practice to separate model classes and have your repositories expose
     * only the data that the other layers of the hierarchy require. For example, here is how you
     * might trim down the SearchResults from the network in order to expose an Movie model
     * class to the domain and UI layers: With Helper of Mapper
     ** @return
     */
    override suspend fun getMovieList() = flow {
        emit(remoteDataSource.getMovieList())
    }.flowOn(ioDispatcher)

}