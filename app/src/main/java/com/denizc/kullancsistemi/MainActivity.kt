package com.denizc.kullancsistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.denizc.kullancsistemi.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    // binding ekledim
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
// buttona tıklanınca ne olacaıgını burada ayarlıyorum
        binding.buttonKaydet.setOnClickListener{
            var etad = binding.etad.text.toString().trim()
            var etyas= binding.etyas.text.toString().trim()
            var etkendinitanit = binding.etkendinitanit.text.toString().trim()
            if (TextUtils.isEmpty(etad)){
                binding.etad.error="Lütfen boş bırakmaynız"
            }
            if (TextUtils.isEmpty(etyas)){
                binding.etyas.error="Lütfen boş bırakmaynız"
            }
            if (TextUtils.isEmpty(etkendinitanit)){
                binding.etkendinitanit.error="Lütfen boş bırakmaynız"
            }
            else{
                // data base veri ekleme işlemi
                var database = FirebaseDatabase.getInstance()
                var databaseReferance= database.reference.child("Users")
                var id = databaseReferance.push()
                id.child("id").setValue(id.key.toString())
                id.child("adisoyadi").setValue(etad)
                id.child("yasi").setValue(etyas)
                id.child("kendinitanit").setValue(etkendinitanit)
                Toast.makeText(applicationContext,"kayıt başırılı",Toast.LENGTH_LONG).show()

            }
        }
        binding.buttonKayitlariGoster.setOnClickListener{
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}