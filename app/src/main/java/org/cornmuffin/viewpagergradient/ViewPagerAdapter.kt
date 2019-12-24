package org.cornmuffin.viewpagergradient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_page.view.*

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {
    private lateinit var colorComputer: ColorComputer

    var color: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        colorComputer = ColorComputer(parent.context)
        color = colorComputer.colorAt(0)

        return PagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        page_text.text = "Page ${position + 1}"
        container.setBackgroundColor(color)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
