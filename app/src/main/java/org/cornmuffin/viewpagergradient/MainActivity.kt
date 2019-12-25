package org.cornmuffin.viewpagergradient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openViewPagerFragment()
    }

    private fun openViewPagerFragment() {
        supportFragmentManager.beginTransaction().also {
            it.add(R.id.view_pager_fragment_container, ViewPagerFragment())
            it.commit()
        }
    }
}
