package com.bignerdranch.android.redandbluefragmentrandomnumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [BlueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlueFragment : Fragment() {

    private lateinit var displayRandomNumberTextView: TextView

    private val randomNumberViewModel: RandomNumberViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RandomNumberViewModel::class.java)
        // ViewModelProvider checks the viewModels that the associated activity has, if there is a viewModel object
        // already created, it will return that one and if there isn't, it will create a new one
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blue, container, false)
        displayRandomNumberTextView = view.findViewById(R.id.random_number_textview)

        // showRandomNumber()
        randomNumberViewModel.randomNumber.observe(requireActivity()) { // requireActivity() represents the owner of the viewModel, mainactivity here
                // this code runs everytime this randomNumber in viewModel is changed and when the observer is created and registered
                // the new value -> task to do with the new value
                random -> showRandomNumber(random)
        }

        return view
    }

    private fun showRandomNumber(randomNumber: Int) {
        // val randomNumber = randomNumberViewModel.randomNumber
        displayRandomNumberTextView.text = randomNumber.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment BlueFragment.
         */
        @JvmStatic
        fun newInstance() = BlueFragment()
    }
}
