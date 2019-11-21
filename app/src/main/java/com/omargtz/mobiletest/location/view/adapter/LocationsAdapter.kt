package com.omargtz.mobiletest.location.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omargtz.mobiletest.R
import com.omargtz.mobiletest.data.remote.firebase.model.LocationDTO
import com.omargtz.mobiletest.location.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.item_location.view.*

class LocationsAdapter(var locations: List<LocationDTO>,val locationViewModel:LocationViewModel):
    RecyclerView.Adapter<LocationViewHolder>() {

    override fun getItemCount(): Int {
      return  locations.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.tvDirection?.text = locations.get(holder.adapterPosition).direction
        holder.itemView.setOnClickListener(){
            _ -> locationViewModel.clickItemLocation(locations.get(holder.adapterPosition))
        }
    }
    public fun updateList(newlocations: List<LocationDTO>){
        locations = newlocations
        notifyDataSetChanged()
    }

}
    class LocationViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val tvDirection = view.item_tv_direction;
    }


