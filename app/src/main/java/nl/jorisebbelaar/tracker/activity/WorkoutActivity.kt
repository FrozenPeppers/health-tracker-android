package nl.jorisebbelaar.tracker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.jorisebbelaar.tracker.R

class WorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}