package org.sixelasavir.product.marvelfans.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.rangeTo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.FragmentEventBinding
import org.sixelasavir.product.marvelfans.ui.viewmodel.EventViewModel

class EventFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()
    private lateinit var binding: FragmentEventBinding
    private var lastTotal: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        binding.eventVM = eventViewModel
        binding.lifecycleOwner = this

        binding.eventRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context).apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (itemCount != lastTotal && itemCount <= findLastVisibleItemPosition() + 2) {
                            lastTotal = itemCount
                            eventViewModel.loadEvents(itemCount)
                        }
                    }
                })
            }

            adapter = EventAdapter(mutableListOf()) {
                eventViewModel.loadComics(it)
            }

            eventViewModel.eventWrapper.observe(viewLifecycleOwner, Observer {
                (adapter as EventAdapter).events.also { eventWrappers ->
                    for (i: Int in  0 until eventWrappers.size - 1){
                        if(eventWrappers[i].event.id == it.event.id){
                            eventWrappers[i].comics = it.comics
                            (adapter as EventAdapter).notifyItemChanged(i)
                        }
                    }
                }
            })
        }

        return binding.root
    }
}
