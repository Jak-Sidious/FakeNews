package com.example.fakenews.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakenews.data.models.SourceX
import com.example.fakenews.R
import timber.log.Timber


class SourceAdapter(val sourceList: List<SourceX>?) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    override fun getItemCount()= sourceList!!.size
    private var sContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.sContext=parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.source_item,
                parent,
                false
            )
        )
    }

    private fun getImage(imageName: String): Int? {
        return sContext?.resources?.getIdentifier(imageName, "drawable", sContext!!.packageName)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mSource = sourceList!![position]
        val fixedText = mSource.id.replace("-","_")

        Glide.with(sContext!!)
            .load(getImage(fixedText))
            .into(holder.mSourceLogo)
        holder.mSourceName.text = mSource.name
        holder.mSourceDescription.text = mSource.description
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mSourceLogo:ImageView = itemView.findViewById(R.id.source_logo)
        val mSourceName:TextView = itemView.findViewById(R.id.source_name)
        val mSourceDescription:TextView = itemView.findViewById(R.id.source_description)
    }
}
