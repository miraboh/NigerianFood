package com.example.nigeriancuisine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    //val foodLiveData: LiveData<List<Food>> = repository.foodLiveData

    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val foodDferred = CompletableDeferred<Flow<State<List<Food?>>>>()

    init {
        refreshDataFromRepository()
    }
    private val _dataState: MutableLiveData<State<List<Food>>> = MutableLiveData()

    val dataState: LiveData<State<List<Food>>>
        get() = _dataState

        @ExperimentalCoroutinesApi
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                val food = repository.refreshFoodWithFlow()

                foodDferred.complete(food)

            } catch (networkError: IOException) { }
        }
    }
    suspend fun loadFood(): Flow<State<List<Food?>>> =foodDferred.await()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

//    fun setStateEvent(mainStateEvent: MainStateEvent) {
//        viewModelScope.launch {
//            when (mainStateEvent) {
//                is MainStateEvent.GetFoodsEvent -> {
//                    repository.getFoods()
//                        .onEach { dataState ->
//                            _dataState.value = dataState
//                        }
//                        .launchIn(viewModelScope)
//                }
//
//                MainStateEvent.None -> {
//                    // who cares
//                }
//            }
//        }
//
//

//    sealed class MainStateEvent {
//
//        object GetFoodsEvent : MainStateEvent()
//
//        object None : MainStateEvent()
//    }