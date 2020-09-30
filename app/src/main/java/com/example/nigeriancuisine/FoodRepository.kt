package com.example.nigeriancuisine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api: ApiService,
    private val foodDao: FoodDao
) {

    private val _downloadedFood = MutableLiveData<List<Food>>()
    val downloadedFood: LiveData<List<Food>>
        get() = _downloadedFood

    val foodLiveData: LiveData<List<Food>> = foodDao.getfood()

//    suspend fun getFoods() = flow<State<List<Food?>>> {
//        emit(State.loading())
//        //delay(1000)
//        try{
//            val networkFoods = api.getApi()
//            //val foods = networkMapper.mapFromEntityList(networkFoods)
//            foodDao.upsert(networkFoods)
//
//            val cachedfoods = foodDao.getfood()
//            delay(5000)
//            emit(State.Success(cachedfoods))
//        }catch (e: Exception){
//            emit(State.failed("Error"))
//        }
//    }

    @ExperimentalCoroutinesApi
    fun refreshFoodWithFlow() = flow<State<List<Food?>>> {

        // Emit loading state
        emit(State.loading())

        //Timber.d("refresh courses is called")
        val food = api.getApi()
        //val snapshot = getting.get().await()
        //val posts = food.toObject(Food::class.java)

        foodDao.upsert(food)
        //val posts = quiz

        //_downloadedQuiz.postValue(quiz)

        //val cachedfoods = foodDao.getfood()
        // Emit success state with data
        //val foodEntities: LiveData<List<Food>> = foodDao.getfood()
        //emit(State.success(foodEntities))
        //_downloadedFood.postValue(cachedfoods)
        //delay(5000)
        emit(State.success(food))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}

//    suspend fun refreshFood() {
//        withContext(Dispatchers.IO) {
//            Log.i("Error","refresh courses is called")
//            val food = api.getApi()
//            _downloadedFood.postValue(food)
//            persistFetchedFood(food)
//        }
//    }


//    private fun persistFetchedFood(fetchedFoodEntring: List<Food>) {
//        GlobalScope.launch(Dispatchers.IO) {
//            foodDao.upsert(fetchedFoodEntring)
//        }
//    }

//    //getting and returning food from db
//    suspend fun getFood(): LiveData<Food> {
//        return withContext(Dispatchers.IO) {
//            delay(10000)
//            return@withContext foodDao.getfood()
//        }
//    }
