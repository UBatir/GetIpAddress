
package com.example.getip.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.getip.R
import com.example.getip.core.ResourceState
import com.example.getip.core.onClick
import com.example.getip.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment:Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMainBinding.bind(view)
        binding.apply {
            btnMyIP.onClick {
                viewModel.getApi()
            }
        }
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.data.observe(viewLifecycleOwner,{
            when(it.status){
                ResourceState.LOADING->{}
                ResourceState.SUCCESS->{
                    binding.tvIP.text=it.data?.xForwardedFor
                }
                ResourceState.ERROR->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}