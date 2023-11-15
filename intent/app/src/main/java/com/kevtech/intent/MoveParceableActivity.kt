package com.kevtech.intent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MoveParceableActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_parceable)

        val tvParcel:TextView = findViewById(R.id.text_parcel)
        val person:Person? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA,Person::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }
        if (person != null) {
            val text= "Nama saya adalah ${person.name} umur saya ${person.age} saya tinggal di kota ${person.city}, email : ${person.email}"
            tvParcel.text = text
            }


    }

}