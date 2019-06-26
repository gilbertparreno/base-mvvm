package com.gp.base.screen.main

import com.gp.base.di.ActivityScope
import com.gp.base.network.repository.ProjectRepositoryImpl
import com.gp.base.network.repository.ProjectRepositoryInteractor
import com.gp.base.network.service.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun providesProjectRepository(retrofit: Retrofit): ProjectRepositoryInteractor {
        return ProjectRepositoryImpl(retrofit.create(GithubService::class.java))
    }
}