package org.cornmuffin.viewpagergradient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_page.view.*

class ViewPagerAdapter(private val numberOfPages: Int) : RecyclerView.Adapter<PagerVH>() {
    var color: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        color = ColorComputer.colorAt(0, parent.context)

        return PagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )
    }

    override fun getItemCount(): Int = numberOfPages

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.itemView.run {
            page_text.text = context.getString(R.string.page, position + 1)
            container.setBackgroundColor(color)
        }
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
