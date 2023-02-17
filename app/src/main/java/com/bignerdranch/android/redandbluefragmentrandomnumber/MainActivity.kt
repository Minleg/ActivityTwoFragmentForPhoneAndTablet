package com.bignerdranch.android.redandbluefragmentrandomnumber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // what size is the screen?
        val deviceSmallestWidth = resources.configuration.smallestScreenWidthDp
        // if smaller, less than 600dp, then use listener and swap fragments
        // otherwise, nothing else is needed
        if (deviceSmallestWidth < 600) {
            supportFragmentManager.setFragmentResultListener(RANDOM_NUMBER_GENERATED, this) {
                    requestKey, bundle ->
                // swap out red fragment for blue fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, BlueFragment.newInstance(), "BLUE") // what's on the screen?
                    .addToBackStack("BLUE") // where has the user been?  allows it to go back to the previous fragment
                    .commit()
            }
            // 1. add: Pause the red fragment, add the blue fragment "on top" - use this if fragments involved are light weighted
            // and transition between the fragments happen quite often
            // 2. replace: Replace the red fragment with the blue fragment <- red fragment is destroyed -  use this if fragments use
            // resources heavily. We have to recreate the red fragment again after it is destroyed
        }
    }
}
