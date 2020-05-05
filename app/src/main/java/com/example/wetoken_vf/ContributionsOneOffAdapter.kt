package com.example.wetoken_vf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ContributionsOneOffAdapter(
    context: Context?,
    private val isGrid: Boolean,
    private val count: Int
) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return count
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        var view: View? = convertView

        if (view == null) {
            view = if (isGrid) {
                layoutInflater.inflate(R.layout.simple_grid_item, parent, false)
            } else {
                layoutInflater.inflate(R.layout.simple_list_item, parent, false)
            }

            viewHolder = ViewHolder(
                view!!.findViewById(R.id.text_view),
                view!!.findViewById(R.id.image_view)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val context = parent.context
        when (position) {
            0 -> {
                viewHolder.textView.text = context.getString(R.string.create_new_contribution)
                viewHolder.imageView.setImageResource(R.drawable.check)

            }
            1 -> {
                viewHolder.textView.text = context.getString(R.string.modify_existing_contribution)
                viewHolder.imageView.setImageResource(R.drawable.check)
            }
            else -> {
                viewHolder.textView.text = context.getString(R.string.delete_contribution)
                viewHolder.imageView.setImageResource(R.drawable.ic_facebook)
            }
        }

        return view!!
    }

    data class ViewHolder(val textView: TextView, val imageView: ImageView)
}