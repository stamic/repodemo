package com.milovan.repodemo.ui.details


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//
//class PaymentOptionAdapter(
//    private val onCardClicked: (paymentOptionId: String, isDefault: Boolean) -> Unit
//) : ListAdapter<PaymentOptionUI, PaymentOptionAdapter.PaymentHolder>(DiffCallback) {
//
//    class PaymentHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val binding = ItemPaymentOptionBinding.bind(view)
//
//        fun bind(
//            paymentOptionUI: PaymentOptionUI,
//            onCardClicked: (paymentOptionId: String, isDefault: Boolean) -> Unit,
//        ) {
//            binding.apply {
//                ivCardIcon.setImageResource(paymentOptionUI.typeView.icon)
//                tvCardType.text = cardView.resources.getString(paymentOptionUI.typeView.displayName)
//                tvDisplayNumber.text = cardView.resources.getString(
//                    R.string.cc_display_number,
//                    paymentOptionUI.displayName
//                )
//                defaultIcon.isChecked = paymentOptionUI.isDefault
//                cardView.setOnClickListener {
//                    paymentOptionUI.paymentOptionId?.let { id ->
//                        onCardClicked.invoke(
//                            id,
//                            paymentOptionUI.isDefault
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PaymentHolder {
//        return PaymentHolder(
//            LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.item_payment_option, viewGroup, false)
//        )
//    }
//
//    override fun onBindViewHolder(item: PaymentHolder, index: Int) {
//        item.bind(getItem(index), onCardClicked = onCardClicked)
//    }
//
//    fun getItemAtPosition(position: Int): PaymentOptionUI? {
//        return if (position in 0 until itemCount) getItem(position) else null
//    }
//
//    private object DiffCallback : DiffUtil.ItemCallback<PaymentOptionUI>() {
//        override fun areItemsTheSame(
//            oldItem: PaymentOptionUI,
//            newItem: PaymentOptionUI
//        ): Boolean {
//            return oldItem.paymentOptionId == newItem.paymentOptionId
//        }
//
//        override fun areContentsTheSame(
//            oldItem: PaymentOptionUI,
//            newItem: PaymentOptionUI
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }
//}
