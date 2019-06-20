package com.example.fakenews.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakenews.Data.models.SourceX
import com.example.fakenews.R


class SourceAdapter(val sourceList: List<SourceX>?) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    override fun getItemCount()=sourceList!!.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext=parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.source_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mSource = sourceList!![position]

        holder.mSourceName.text = mSource.name

        holder.mSourceDescription.text = mSource.description

        holder.mSourceCategory.text = mSource.category

        holder.mSourceLanguage.text = mSource.language

        holder.mSourceCountry.text = mSource.country
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mSourceName:TextView = itemView.findViewById(R.id.source_name)
        val mSourceDescription:TextView = itemView.findViewById(R.id.source_description)
        val mSourceCategory:TextView = itemView.findViewById(R.id.source_category)
        val mSourceLanguage:TextView = itemView.findViewById(R.id.source_language)
        val mSourceCountry:TextView = itemView.findViewById(R.id.source_country)
    }
}