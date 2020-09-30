package com.example.nigeriancuisine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nigeriancuisine.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //@Inject lateinit var viewModelFactory: HomeViewmodelFactory
    @ExperimentalCoroutinesApi
    @Inject lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: FoodAdapter

    //@Inject lateinit var apiService: ApiService

    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = HomeFragmentBinding.inflate(inflater)
        //viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        // TODO: Use the ViewMode
        //subscribeObservers()
        //viewModel.setStateEvent(HomeViewModel.MainStateEvent.GetFoodsEvent)

        adapter = FoodAdapter()
        binding.recyclerViewNews.adapter = adapter


        val manager = LinearLayoutManager(context)
        binding.recyclerViewNews.layoutManager = manager

        lifecycleScope.launchWhenStarted {
            viewModel.loadFood().collect {
                when (it) {

                    is State.Loading -> {
                        //Toast.makeText(context,"Loading",Toast.LENGTH_LONG).show()
                        binding.groupLoading.visibility = View.VISIBLE

                    }

                    is State.Success -> {
                        it.apply {
                            //Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
                            binding.groupLoading.visibility = View.GONE
                            adapter.submitList(it.data)
                        }
                    }

                    is State.Failed ->{
                        //binding.cloudId.visibility = View.VISIBLE
                        Toast.makeText(context,"Failed${it.message}",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return binding.root
    }
//    @ExperimentalCoroutinesApi
//    fun fetchFromLocal(): List<Food>{
//        var listFood: List<Food>
//        viewModel.foodLiveData.observeForever {
//            if (it==null){
//                Toast.makeText(context,"Failed Loading data from locat db${it}",Toast.LENGTH_LONG).show()
//            }
//            //adapter.submitList(it.toList())
//            listFood=it
//        }
//        return listFood
//    }
    @ExperimentalCoroutinesApi
    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is State.Success<List<Food>> -> {
                    displayProgressBar(false)
                    //Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
                    binding.groupLoading.visibility = View.GONE
                    adapter.submitList(dataState.data)
                }
                is State.Failed -> {
                    displayProgressBar(false)
                    //viewModel.foodEntities.observe(viewLifecycleOwner, Observer {
                    //    adapter.submitList(it)
                    //})

                    displayError(dataState.message)
                }
                is State.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }
    private fun displayError(message: String?){
        if(message != null)
            binding.cloudId.visibility = View.VISIBLE
    }


//    private fun appendBlogTitles(blogs: List<Blog>){
//        val sb = StringBuilder()
//        for(blog in blogs){
//            sb.append(blog.title + "\n")
//        }
//        text.text = sb.toString()
//    }

    private fun displayProgressBar(isDisplayed: Boolean){
        binding.groupLoading.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}
