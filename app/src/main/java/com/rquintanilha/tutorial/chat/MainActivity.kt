package com.rquintanilha.tutorial.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.rquintanilha.tutorial.chat.databinding.ActivityMainBinding

const val USER_ID = 0
const val OTHER_ID = 1

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var fromUser = true

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        //supportActionBar?.hide()
        binding.buttonSend.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        binding.buttonSend.setOnClickListener {
            val messageText = binding.editMessage.text.toString()
            binding.editMessage.setText("")

            val adapter = binding.messageList.adapter
            if (adapter is MessageAdapter) {
                val message = ChatMessage(messageText, if (fromUser) USER_ID else OTHER_ID)
                adapter.addItem(message)
                binding.messageList.scrollToPosition(adapter.itemCount - 1)
                fromUser = !fromUser

            }
        }
    }

    private fun setUpRecyclerView() {
        binding.messageList.layoutManager = LinearLayoutManager(this)
        binding.messageList.adapter = MessageAdapter()
    }
}

