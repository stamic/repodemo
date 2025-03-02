package com.milovan.repodemo.ui.details

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

//class EvsesAdapter :
//    ListAdapter<Evse, ViewHolderEvses>(ChargingSessionsAdapterCallback),
//    ViewItemEvses.Listener {
//
//    interface Listener {
//        fun onChargingClick(item: Evse)
//    }
//
//    private val expandedItems: MutableSet<String> = mutableSetOf()
//
//    private var activeSessions: List<ChargingSession> = mutableListOf()
//
//    private var lastClickedChargingEvse: Evse? = null
//
//    var listener: Listener? = null
//
//    fun isItemExpanded(uid: String): Boolean = expandedItems.contains(uid)
//
//    fun toggleItemExpanded(evseId: String) {
//        expandedItems.toggle(evseId)
//        notifySpotItemChanged(evseId)
//    }
//
//    fun setItemExpanded(
//        evseId: String,
//        expanded: Boolean
//    ) {
//        if (expanded) {
//            expandedItems.add(evseId)
//        } else {
//            expandedItems.remove(evseId)
//        }
//
//        notifySpotItemChanged(evseId)
//    }
//
//    private fun notifySpotItemChanged(evseId: String) {
//        val spotFound = currentList.firstOrNull { it.evseId == evseId }
//        if (spotFound != null) {
//            val position = currentList.indexOf(spotFound)
//            notifyItemChanged(position)
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolderEvses {
//        val vi = ViewItemEvses(parent.context)
//        vi.layoutParams =
//            ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//        return ViewHolderEvses(vi)
//    }
//
//    override fun onBindViewHolder(
//        holder: ViewHolderEvses,
//        position: Int
//    ) {
//        val item = getItem(position)
//        holder.bindData(this, item, activeSessions, lastClickedChargingEvse)
//    }
//
//    override fun onChargingClick(evseId: String) {
//        lastClickedChargingEvse = currentList.first { it.evseId == evseId }
//        lastClickedChargingEvse?.let {
//            listener?.onChargingClick(it)
//        }
//    }
//
//    override fun onEvseItemClick(evseId: String) {
//        toggleItemExpanded(evseId)
//    }
//
//    fun setActiveChargingSessions(activeSessions: List<ChargingSession>) {
//        this.activeSessions = activeSessions
//        notifyItemRangeChanged(0, itemCount)
//    }
//
//    fun updateLastClickedChargingEvseButton() {
//        val position = currentList.indexOf(lastClickedChargingEvse)
//        notifyItemChanged(position)
//    }
//
//    private object ChargingSessionsAdapterCallback :
//        DiffUtil.ItemCallback<Evse>() {
//        override fun areItemsTheSame(
//            oldItem: Evse,
//            newItem: Evse
//        ): Boolean {
//            return oldItem.uid == newItem.uid
//        }
//
//        override fun areContentsTheSame(
//            oldItem: Evse,
//            newItem: Evse
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }
//}
