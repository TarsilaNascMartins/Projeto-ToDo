package com.generation.todo.DependencyInjection

import com.generation.todo.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.contracts.Returns

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton

        fun providesRepository () : Repository{
            return Repository()
        }

}
