package com.kevtech.intent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inputNama: EditText
    private lateinit var inputUmur: EditText
    private lateinit var btnIntentData: Button
    private lateinit var tvHasil:TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val hasil = result.data?.getIntExtra(MoveForResultActivity.EXTRA_HASIL, 0)
            tvHasil.text = "Hasil: $hasil"
            Log.d("ActivityResult", "Hasil: $hasil")
        }else {
            val hasil = result.data?.getIntExtra(MoveForResultActivity.EXTRA_HASIL, 0)
            Log.d("ActivityResult", "Hasil: $hasil result code = ${result.resultCode}")
            Log.d("ActivityResult", "Data kosong atau resultCode tidak sesuai.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExplisitInten: Button = findViewById(R.id.btn_explicit_intent)
        btnExplisitInten.setOnClickListener(this)

        btnIntentData = findViewById(R.id.btn_intent_data)
        btnIntentData.setOnClickListener(this)
        inputNama = findViewById(R.id.input_nama)
        inputUmur = findViewById(R.id.input_umur)

        val btnParcelIntent:Button = findViewById(R.id.btn_intent_parcelable)
        btnParcelIntent.setOnClickListener(this)

        val btnCallUs:Button = findViewById(R.id.btn_call_us)
        btnCallUs.setOnClickListener(this)

        val btnHitungLuas:Button = findViewById(R.id.btn_hitung_luas)
        btnHitungLuas.setOnClickListener(this)

        tvHasil = findViewById(R.id.tv_hasil)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_explicit_intent -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_intent_data -> {

                val dataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                dataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, inputNama.text.toString())
                dataIntent.putExtra(
                    MoveWithDataActivity.EXTRA_AGE,
                    inputUmur.text.toString().toInt()
                )

                startActivity(dataIntent)
            }

            R.id.btn_intent_parcelable -> {
                val dataPerson:Person = Person(
                    "Kevin Alfito",
                    22,
                    "kevinalfito000@gmail.com",
                    "Cirebon"
                )
                val intentParcel = Intent(this@MainActivity,MoveParceableActivity::class.java)
                    .putExtra(MoveParceableActivity.EXTRA_DATA,dataPerson)
                startActivity(intentParcel)
            }
            R.id.btn_call_us ->{
                val phoneNum = "0853811814141"

                val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNum"))
                startActivity(callIntent)
            }
            R.id.btn_hitung_luas ->{
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }

        }
    }

}