package com.example.messageservicesender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        // Имя ACTION для Broadcast, по нему Receiver и будет определяться
        const val ACTION_SEND_MSG = "ru.geekbrains.broadcastsender.message"
        // Имя передаваемого параметра
        const val NAME_MSG = "MSG"
        // Эта константа спрятана в Intent классе,
        // Но, именно посредством её можно поднять приложение
    }
    val FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtonSend()
    }

    private fun initButtonSend() {
        val message = findViewById<EditText>(R.id.textMessage)
        val send: Button = findViewById(R.id.buttonSend)
        send.setOnClickListener {
            // Формируем интент
            val msg = message.text.toString()
            val intent = Intent()
            // Укажем ACTION, по которому будем ловить сообщение
            intent.action = ACTION_SEND_MSG
            // Добавим параметр.
            intent.putExtra(NAME_MSG, msg)
            // Указываем флаг поднятия приложения
            // (без него будут получать уведомления только
            // загруженные приложения)
            intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND)
            // Отправка сообщения
            sendBroadcast(intent)
        }
    }
}