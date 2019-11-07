package com.nos

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.nos.Fragment.*
import eu.long1.spacetablayout.SpaceTabLayout
import java.util.*



class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: SpaceTabLayout
    private lateinit var greetImg: ImageView
    private lateinit var greetText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the fragments you want to display in a List
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(FragmentA())
        fragmentList.add(FragmentB())
        fragmentList.add(FragmentC())
        fragmentList.add(FragmentD())
        //fragmentList.add(FragmentE())

        val coordinatorLayout = findViewById(R.id.activity_main) as CoordinatorLayout

        val viewPager = findViewById(R.id.viewPager) as ViewPager
        tabLayout = findViewById(R.id.spaceTabLayout) as SpaceTabLayout

        tabLayout.initialize(viewPager, supportFragmentManager, fragmentList, savedInstanceState)

        tabLayout.tabOneOnClickListener = View.OnClickListener {
            val snackbar = Snackbar
                .make(coordinatorLayout, "Welcome to SpaceTabLayout", Snackbar.LENGTH_SHORT)

            snackbar.show()
        }



       /*tabLayout.setOnClickListener {
            Toast.makeText(
                application,
                "" + tabLayout.currentPosition,
                Toast.LENGTH_SHORT
            ).show()
        }*/

        //greetImg  = findViewById(R.id.greeting_img)
        //greetText = findViewById(R.id.greeting_text)

        //greeting()

        //val intent = Intent (this, Main::class.java)
        //startActivity(intent)
    }


   /* @SuppressLint("SetTextI18n")
    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.text = "Selamat Pagi\nThommy Cool"
            greetImg.setImageResource(R.drawable.img_default_half_morning)
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.text = "Selamat Siang\nThommy Cool"
            greetImg.setImageResource(R.drawable.img_default_half_afternoon)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.text = "Selamat Sore\nThommy Cool"
            greetImg.setImageResource(R.drawable.img_default_half_without_sun)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.text = "Selamat Malam\nThommy Cool"
            greetText.setTextColor(Color.WHITE)
            greetImg.setImageResource(R.drawable.img_default_half_night)
        }
    }*/






}
