package com.airweb.contributionwenext

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wetoken_vf.Contributions
import com.example.wetoken_vf.R

class ContributionAdapterHistory (internal var context: Context, internal var list: List<Contributions>) :
    RecyclerView.Adapter<ContributionAdapterHistory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_row, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textviewCTR_ID.text = list[position].ctR_ID.toString()
        holder.textviewCTR_MOTIF.text = list[position].ctR_MOTIF
        holder.textviewCTR_FROM.text = list[position].ctR_FROM
        holder.textviewCTR_TO.text = list[position].ctR_TO.toString()
        holder.textviewCTR_AMOUNT.text = list[position].ctR_AMOUNT.toString()
        holder.textviewCTR_PLAGE.text = list[position].ctR_PLAGE.toString()
        holder.textviewCTR_DATE.text = list[position].ctR_DATE.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textviewCTR_TO: TextView
        internal var textviewCTR_FROM: TextView
        internal var textviewCTR_MOTIF: TextView
        internal var textviewCTR_ID: TextView
        internal var textviewCTR_AMOUNT: TextView
        internal var textviewCTR_PLAGE: TextView
        internal var contributionid: TextView
        internal var contributionfrom: TextView
        internal var contributionto: TextView
        internal var contributionpurpose: TextView
        internal var contributionamount: TextView
        internal var contributionplage: TextView
        internal var textviewCTR_DATE: TextView
        internal var contributiondate: TextView
        internal val tf1 = Typeface.createFromAsset(context.assets, "quicksand_regular.ttf")
        internal val tf2 = Typeface.createFromAsset(context.assets, "Quicksand-Medium.ttf")
        internal val tf3 = Typeface.createFromAsset(context.assets, "Quicksand-Bold.ttf")
        internal val tf4 = Typeface.createFromAsset(context.assets, "MiriamLibre-Bold.ttf")

        init {
            textviewCTR_ID = itemView.findViewById<View>(R.id.textviewCTR_ID) as TextView
            textviewCTR_MOTIF = itemView.findViewById<View>(R.id.textviewCTR_MOTIF) as TextView
            textviewCTR_FROM = itemView.findViewById<View>(R.id.textviewCTR_FROM) as TextView
            textviewCTR_TO = itemView.findViewById<View>(R.id.textviewCTR_TO) as TextView
            textviewCTR_AMOUNT = itemView.findViewById<View>(R.id.textviewCTR_AMOUNT) as TextView
            textviewCTR_PLAGE = itemView.findViewById<View>(R.id.textviewCTR_PLAGE) as TextView
            textviewCTR_DATE = itemView.findViewById<View>(R.id.textviewCTR_DATE) as TextView

            contributionid = itemView.findViewById(R.id.contributionid)
            contributionfrom = itemView.findViewById(R.id.contributionfrom)
            contributionto = itemView.findViewById(R.id.contributionto)
            contributionpurpose = itemView.findViewById(R.id.contributionpurpose)
            contributionamount = itemView.findViewById(R.id.contributionamount)
            contributionplage = itemView.findViewById(R.id.contributionplage)
            contributiondate = itemView.findViewById(R.id.contributiondate)


            contributionid.typeface = tf2
            contributionfrom.typeface = tf2
            contributionto.typeface = tf2
            contributionpurpose.typeface = tf2
            contributionamount.typeface = tf2
            contributionplage.typeface = tf2
            contributiondate.typeface = tf2
            textviewCTR_AMOUNT.typeface = tf2
            textviewCTR_PLAGE.typeface = tf2
            textviewCTR_TO.typeface = tf2
            textviewCTR_FROM.typeface = tf2
            textviewCTR_MOTIF.typeface = tf2
            textviewCTR_ID.typeface = tf2
            textviewCTR_DATE.typeface = tf2


        }
    }
}

