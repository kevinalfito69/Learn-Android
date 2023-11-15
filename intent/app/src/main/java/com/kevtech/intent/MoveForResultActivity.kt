package com.kevtech.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MoveForResultActivity : AppCompatActivity() {
    private lateinit var btnHitung:Button
    private var hasil:Int = 0
    companion object{
        const val EXTRA_HASIL = "extra_hasil"
        const val RESULT_CODE = 110
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)
        btnHitung = findViewById(R.id.btn_hitung)
        val panjang:EditText = findViewById(R.id.edt_panjang)
        val luas:EditText = findViewById(R.id.edt_luas)


        btnHitung.setOnClickListener {
            try {
                hasil = panjang.text.toString().toInt() * luas.text.toString().toInt()
                val intent = Intent()
                intent.putExtra(EXTRA_HASIL, hasil)
                setResult(RESULT_CODE, intent)
                finish()
            } catch (e: NumberFormatException) {
                // Handle jika input tidak valid (bukan angka)
                // Contohnya, tampilkan pesan kesalahan atau lakukan tindakan yang sesuai
                e.printStackTrace()
            }
        }
    }
}