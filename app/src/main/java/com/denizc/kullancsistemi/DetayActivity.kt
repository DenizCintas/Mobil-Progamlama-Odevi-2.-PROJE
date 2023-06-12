package com.denizc.kullancsistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denizc.kullancsistemi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val adisoyadi = intent.getStringExtra("putadisoyadi")
        val yasi = intent.getStringExtra("putyasi")
        val kendinitanit = intent.getStringExtra("putkendinitanit")

        // Değişkenleri sayfada yansıtıyoruz
        binding.detayadsoyad.text = adisoyadi
        binding.detayyas.text = yasi
        binding.detaykendinitanit.text = kendinitanit
    }
}
