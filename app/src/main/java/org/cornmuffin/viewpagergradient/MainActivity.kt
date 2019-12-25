package org.cornmuffin.viewpagergradient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        pager.apply {
            adapter = ViewPagerAdapter().also {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        it.color =
                            ColorComputer.colorAt(positionOffset, position, position + 1, context)
                        pager.post { it.notifyDataSetChanged() }
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }
                })
            }
        }
    }
}
