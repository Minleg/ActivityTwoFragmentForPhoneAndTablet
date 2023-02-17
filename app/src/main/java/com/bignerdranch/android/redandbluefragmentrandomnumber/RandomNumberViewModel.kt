package com.bignerdranch.android.redandbluefragmentrandomnumber

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RandomNumberViewModel : ViewModel() {
    var randomNumber = MutableLiveData(0) // mutable live data can let the observers know of any update on the data
}
