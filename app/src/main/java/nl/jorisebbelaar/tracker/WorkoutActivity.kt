package nl.jorisebbelaar.tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}