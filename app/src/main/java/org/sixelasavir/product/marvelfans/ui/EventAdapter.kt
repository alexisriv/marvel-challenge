package org.sixelasavir.product.marvelfans.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sixelasavir.product.marvelfans.EventWrapper
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.ItemEventsBinding


class EventAdapter(val events: MutableList<EventWrapper>, val block: (EventWrapper) -> Any) :
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

        fun bindData(event: EventWrapper) {
            itemEventsBinding.itemCardView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.zoom_in)
            itemEventsBinding.eventWrapper = event

            itemEventsBinding.nameTextView.text = event.event.title
            itemEventsBinding.startDateTextView.text = event.event.start
            itemEventsBinding.endDateTextView.text = event.event.end

            if (event.isExpanded) {
                itemEventsBinding.contentComicsOfEvent.visibility = View.VISIBLE
                itemEventsBinding.expandClosedImageView.setImageResource(R.drawable.ic_expand_more_black_24dp)
            } else {
                itemEventsBinding.contentComicsOfEvent.visibility = View.GONE
                itemEventsBinding.expandClosedImageView.setImageResource(R.drawable.ic_expand_less_black_24dp)
            }

            itemEventsBinding.itemCardView.setOnClickListener {
                event.isExpanded = !event.isExpanded
                if (event.isExpanded) {
                    itemEventsBinding.contentComicsOfEvent.visibility = View.VISIBLE
                    itemEventsBinding.expandClosedImageView.setImageResource(R.drawable.ic_expand_more_black_24dp)
                } else {
                    itemEventsBinding.contentComicsOfEvent.visibility = View.GONE
                    itemEventsBinding.expandClosedImageView.setImageResource(R.drawable.ic_expand_less_black_24dp)
                }
                if (event.comics.isNullOrEmpty())
                    block(event)
            }
        }
    }
}
