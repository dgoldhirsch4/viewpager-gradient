package org.cornmuffin.viewpagergradient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_view_pager.*

class ViewPagerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
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
                        post { it.notifyDataSetChanged() }
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }
                })
            }
        }

        super.onActivityCreated(savedInstanceState)
    }
}
