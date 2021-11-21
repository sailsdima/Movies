package com.twmaze.core.di

import com.twmaze.core.repository.ShowsRepository
import com.twmaze.core.repository.ShowsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindingModule {
    @Binds
    fun bindLoginRepository(repository: ShowsRepositoryImpl): ShowsRepository
}