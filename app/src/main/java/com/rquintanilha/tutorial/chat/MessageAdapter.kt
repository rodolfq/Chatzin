package com.rquintanilha.tutorial.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

const val SENT_MESSAGE = 0
const val RECEIVED_MESSAGE = 1

class MessageAdapter : Adapter<ViewHolder>() {

    private val items: MutableList<ChatMessage> = mutableListOf()

    fun addItem(message: ChatMessage) {
        items.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) =
        if (items[position].senderId == USER_ID) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(
                if (viewType == SENT_MESSAGE)
                    R.layout.sent_card
                else R.layout.received_card, parent, false)
        return MessageViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        if (holder is MessageViewHolder) {
            holder.messageTextView.text = currentItem.text
        }
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.message_textview)
    }
}