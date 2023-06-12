package com.denizc.kullancsistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denizc.kullancsistemi.databinding.ActivityUserListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//KULLANICI LİSTELEME VE ARTI YA TIKLAYRAK KULLANICI EKLEME SAYFASI
class UserListActivity : AppCompatActivity() {
    lateinit var  binding: ActivityUserListBinding
    private lateinit var dbref:DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<user>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerView =binding.userList
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<user>()
        getuserData()
        // artı butonu ile kullanıcı ekleme
        binding.button3kullaniciekle.setOnClickListener{
            // buttona tıklanınca main activity sayfasına yönlendiriyorum
            val intent = Intent(this, MainActivity::class.java)
 startActivity(intent)
            finish()
        }
    }

    private fun getuserData() {
        // veri tabanı erişim izni istedim ve User tablosunu getir dedim
        dbref = FirebaseDatabase.getInstance().getReference("Users")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // veri tabınında olanb değişiklikerli buradan dinliyoruz
                //snapshotlar childarı dinleyip bize bir şey var mı yok mu diye iletiyor
                if(snapshot.exists()){
                    // eğer bil bilgi var ise for döngüsü ile bütün sayfa verileri yazdırıyoruz
                    for (userSnapshot in snapshot.children){
                        /*bilgileri get value ile getirdik ancak snapshot dinledi ve bilgiyi getirmek istiyor
                        peki  nasıl bir bilgi getirmek istiyorsun diyor bende user klasörünün içindeki bulunan değişkenlerimin
                        ismi ile aynı olan bilgileri getir diyorum*/
                        val user = userSnapshot.getValue(user::class.java)
                        // iki ünlem boş geçmesin demek
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = CustomAdapter(userArrayList, this@UserListActivity )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}