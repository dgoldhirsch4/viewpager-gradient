package org.cornmuffin.viewpagergradient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_page.view.*

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {
    private val colors = intArrayOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        page_text.text = "Page ${position + 1}"
        container.setBackgroundResource(colors[position % colors.size])
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
