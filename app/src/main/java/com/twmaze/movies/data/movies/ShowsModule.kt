package com.twmaze.movies.data.movies

import com.twmaze.movies.data.common.module.NetworkModule
import com.twmaze.movies.data.movies.remote.ShowsApiService
import com.twmaze.movies.data.movies.repository.ShowsRepositoryImpl
import com.twmaze.movies.domain.shows.ShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ShowsModule {

    @Singleton
    @Provides
    fun provideShowsApi(retrofit: Retrofit): ShowsApiService {
        return retrofit.create(ShowsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideShowsRepository(showsApiService: ShowsApiService): ShowsRepository {
        return ShowsRepositoryImpl(showsApiService)
    }


}