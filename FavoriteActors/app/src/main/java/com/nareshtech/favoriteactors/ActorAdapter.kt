package com.nareshtech.favoriteactors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

// TODO 4: Create an Adapter for your Recyclerview.

/** Why do we need an Adapter ?
 * - We have multiple items to show on the recyclerview
 * - We are supposed to use an adapter to populate these items on the recyclerview
 * **/
class ActorAdapter(private val context:Context, private val actors:MutableList<MainActivity.Actors>) :
    Adapter<ActorAdapter.ActorViewHolder>() {

    /**
     * Why do we need a ViewHolder ?
     * For each item on the recyclerview, we have three more views. ViewHolder is a class that holds these three views.*/
    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.imgActor)
        val textViewName:TextView = itemView.findViewById(R.id.tvActorName)
        val textViewYob:TextView = itemView.findViewById(R.id.tvActorYOB)
    }

    /**
     * This method is called when the recyclerview needs a new ViewHolder.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.one_item_design, parent, false)
        return ActorViewHolder(v)
    }

    /**
     * This method is called when the recyclerview needs to know the size of the data set.*/
    override fun getItemCount(): Int {
        return actors.size
    }

    /** This method is called when the recyclerview needs to populate the data on the views.*/
    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.imageView.setImageResource(actors[position].image)
        holder.textViewName.text = actors[position].name
        holder.textViewYob.text = "${actors[position].yob}"
    }

}