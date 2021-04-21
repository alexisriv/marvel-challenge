package org.sixelasavir.product.marvelfans.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.ItemEventsBinding

class EventAdapter(val events: MutableList<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_events,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bindData(events[position])

    inner class EventViewHolder(private val itemEventsBinding: ItemEventsBinding) :
        RecyclerView.ViewHolder(itemEventsBinding.root) {

        fun bindData(event: Event) {
            itemEventsBinding.itemCardView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.zoom_in)
            itemEventsBinding.event = event
        }
    }
}
