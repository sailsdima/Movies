package com.twmaze.core.di

import android.content.Context
import com.twmaze.core.datastore.DataStoreManager
import com.twmaze.core.repository.ShowsRepository
import com.twmaze.core.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [
    AppBindingModule::class
])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataManager(@ApplicationContext context: Context) = DataStoreManager(context)


    @Singleton
    @Provides
    fun provideAddToFavoriteUseCase(dataStoreManager: DataStoreManager) =
        AddToFavoriteUseCase(dataStoreManager)

    @Singleton
    @Provides
    fun provideGetFavoriteShowsUseCase(dataStoreManager: DataStoreManager) =
        GetFavoriteShowsUseCase(dataStoreManager)

    @Singleton
    @Provides
    fun provideGetShowsUseCase(showsRepository: ShowsRepository) = GetShowsUseCase(showsRepository)

    @Singleton
    @Provides
    fun provideGetShowUseCase(showsRepository: ShowsRepository) = GetShowUseCase(showsRepository)

    @Singleton
    @Provides
    fun provideRemoveFromFavoriteUseCase(dataStoreManager: DataStoreManager) =
        RemoveFromFavoriteUseCase(dataStoreManager)
}