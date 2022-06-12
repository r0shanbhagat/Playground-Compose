package com.playground.movie.data

import com.digital.playground.data.mapper.MovieMapper
import com.digital.playground.di.IoDispatcher
import com.playground.movie.BuildConfig
import com.playground.movie.contract.Repository
import com.playground.movie.data.api.MovieService
import com.playground.movie.data.dto.MovieModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * @Details BlogRepository
 * @Author Roshan Bhagat
 * @property movieMapper
 * @property ioDispatcher
 * @constructor Create [MoviesRepository]
 */
@ViewModelScoped
class MoviesRepository @Inject constructor(
    override val apiService: MovieService,
    private val movieMapper: MovieMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : Repository {

    /**
     * The app doesn't need that much information about the movies because it only displays the
     * content of the movies on the screen, along with basic information about its year of release
     * and rating. It's a good practice to separate model classes and have your repositories expose
     * only the data that the other layers of the hierarchy require. For example, here is how you
     * might trim down the SearchResults from the network in order to expose an Movie model
     * class to the domain and UI layers: With Helper of Mapper
     *
     * @param searchTitle
     * @param pageIndex
     * @return
     */
    override suspend fun getMovieList(): Flow<List<MovieModel>> {
        val builder = ParametersBuilder().apply {
            append("s", "Avenger")
            append("apiKey", BuildConfig.API_KEY)
            append("page", "1")
        }.build()
        return flow {
            val searchResults = apiService.getMovieData(builder)
            emit(movieMapper.mapFromEntityList(searchResults))
        }.flowOn(ioDispatcher)
    }


}