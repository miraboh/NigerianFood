package com.example.nigeriancuisine

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nigeriancuisine.databinding.DetailsFragmentBinding


class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DetailsFragmentBinding.inflate(inflater)
        //(activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        //viewModel=ViewModelProvider(viewLifecycleOwner,)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel

        val data = DetailsFragmentArgs.fromBundle(requireArguments()).foodArgs
        binding.viewModel=data

    }

}