package com.gp.base.screen.main

import androidx.lifecycle.*
import com.gp.base.app.App
import com.gp.base.network.model.ApiResponse
import com.gp.base.network.model.Project
import com.gp.base.network.repository.ProjectRepositoryImpl
import com.gp.base.network.repository.ProjectRepositoryInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val projectRepository: ProjectRepositoryInteractor) :
    ViewModel(),
    LifecycleObserver {

    val liveDataProjects = MutableLiveData<ApiResponse<List<Project>>>()

    private val disposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getProjectList()
    }

    fun getProjectList() {
        disposable.add(projectRepository.getProjectList("JakeWharton")
            .subscribe(
                { data ->
                    liveDataProjects.postValue(ApiResponse(data))
                }, { throwable ->
                    throwable.printStackTrace()
                    liveDataProjects.postValue(ApiResponse(throwable = throwable))
                }
            ))
    }
}
