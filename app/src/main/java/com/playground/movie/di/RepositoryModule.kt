package com.playground.movie.di

import com.digital.playground.data.mapper.MovieMapper
import com.digital.playground.di.IoDispatcher
import com.playground.movie.contract.Repository
import com.playground.movie.data.MoviesRepository
import com.playground.movie.data.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

/**
 * @Details RepositoryModule
 * @Author Roshan Bhagat
 * @constructor Create Repository module
 */
@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    /**
     * Provide movie repository
     *
     * @param apiService
     * @param ioDispatcher
     * @return
     */
    @Provides
    fun provideMovieRepository(
        apiService: MovieService,
        movieMapper: MovieMapper,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): Repository =
        MoviesRepository(apiService, movieMapper, ioDispatcher)

    //@Provides
    //fun providePostsRepository(api: PostsApi): PostsRepository = PostsRepositoryImpl(api)
}
