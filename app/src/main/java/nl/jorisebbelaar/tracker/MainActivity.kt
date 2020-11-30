package nl.jorisebbelaar.tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        buttonWorkout.setOnClickListener{
            startActivity(Intent(this, WorkoutActivity::class.java))
        }

        buttonFood.setOnClickListener{
            startActivity(Intent(this, FoodActivity::class.java))
        }
    }
}