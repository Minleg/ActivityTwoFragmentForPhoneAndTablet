package com.bignerdranch.android.redandbluefragmentrandomnumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [RedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val RANDOM_NUMBER_GENERATED = "com.bignerdranch.android.redandbluefragmentrandomnumber.RedFragment.RandomNumber"
class RedFragment : Fragment() {

    private lateinit var sendRandomNumberButton: Button

    private val randomNumberViewModel: RandomNumberViewModel by lazy {
        /* lazy initialization : Initialiaze as soon as possible, val - so it can't be changed but we don't have a reference to the activity
        * that it needs to be associated with until the fragment is created and added to the activity
        * Lazy initialization describes how to create the randomNumberViewModel but it won't be accessed or initialization code
        * won't run until that viewModel is actually accessed - here it won't be initialized until the code in onCreateView is run*/
        ViewModelProvider(requireActivity()).get(RandomNumberViewModel::class.java)
        // asks the ViewModelProvider to create a RandomNumberViewModel object and associate it with the given activity
        // (requireActivity() here) and that makes it life cycle aware of the activity and can preserve its state during
        // lifecycle changes of the activity
        // requireActivity() refers to the activity that contains this fragment
    }

    override fun onCreateView(
        // view isn't inflated until onCreateView is called
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_red, container, false)
        // refer to the button in that particular view - because there can be multiple view objects of same name in different fragments
        sendRandomNumberButton = view.findViewById(R.id.send_random_number_button)

        sendRandomNumberButton.setOnClickListener {
            sendRandomNumber()
        }
        return view
    }

    private fun sendRandomNumber() {
        val random = Random.nextInt(100) // 0 - 99
        randomNumberViewModel.randomNumber.value = random // sets value in the viewModel
        // tell the containing activity that a random number has been generated
        parentFragmentManager.setFragmentResult(RANDOM_NUMBER_GENERATED, Bundle.EMPTY)
        // This method tells the associated Activity that this fragment has a result.Here, Bundle.EMPTY because we don't have a data
        // to send to the containerActivity, otherwise could be send through this Bundle
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() =
            RedFragment() // when newInstance is called, create and return a new RedFragment object
    }
}
