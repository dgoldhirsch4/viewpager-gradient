package org.cornmuffin.viewpagergradient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var colorComputer: ColorComputer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colorComputer = ColorComputer(this)

        setContentView(R.layout.activity_main)
        pager.apply {
            adapter = ViewPagerAdapter().also {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        it.color = colorComputer.colorAt(positionOffset, position, position + 1)
                        pager.post { it.notifyDataSetChanged() }
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }
                })
            }
        }
    }
}
