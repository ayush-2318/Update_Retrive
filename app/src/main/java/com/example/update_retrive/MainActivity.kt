package com.example.update_retrive

import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class MainActivity : AppCompatActivity() {
//    private lateinit var btnAddData:Button
//    private lateinit var btnRetriveData:Button
      private lateinit var btnselectImg:Button
      private lateinit var btnUploadImg:Button
      private lateinit var ivImg:ImageView
    private lateinit var databaseref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btnAddData=findViewById(R.id.btn_select_img)
//        btnRetriveData=findViewById(R.id.btn_upload_img)
        btnselectImg=findViewById(R.id.btn_select_img)
        btnUploadImg=findViewById(R.id.btn_upload_img)
        ivImg=findViewById(R.id.iv_preview)
        databaseref=Firebase.database.getReference("User")
       val user_1=User(
           "Ayush",
           20,
           "ayush@gmail.com"
       )
        val user_2=User(
            "Ayush Kumar",
            20,
           " ayush@gmail.com"
        )
//        btnAddData.setOnClickListener {
//           // add data to firebase
//            databaseref.child("user_1").setValue(user_1)
//            databaseref.child("user_2").setValue(user_2)
//        }
//        btnRetriveData.setOnClickListener {
//            getAllData()
//        }
        btnselectImg.setOnClickListener {
            // logic to select image form gallery
            val galleryIntent= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,101)
        }

    }
    //if start activity result call then on activity result is also called
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // data is image
        if(requestCode==101 && resultCode== RESULT_OK){
            ivImg.setImageURI(data?.data)
        }
        btnUploadImg.setOnClickListener {
            // function to upload image
            uploadImage(data?.data)
        }
    }
    // function to get all data
//    fun getAllData(){
//        // logic to retrive data
//        databaseref.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(data in snapshot.children){
//                    val curUser=data.getValue(User::class.java)
//                    Log.i("currentuser",curUser.toString())
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@MainActivity, "some error occured", Toast.LENGTH_SHORT).show()
//            }
//
//        })

  //  }

//logic to upload image
    fun uploadImage(imageuri:Uri?){
        val fileName=UUID.randomUUID().toString()+".jpg"
        val storageReference=FirebaseStorage.getInstance().reference.child("images/$fileName")
        storageReference.putFile(imageuri!!).addOnSuccessListener {
            Toast.makeText(this, "image upload successfully", Toast.LENGTH_SHORT).show()
        }

    }
}
data class User(
    val name:String=" ",
    val age:Int=0,
    val email:String=" "
)