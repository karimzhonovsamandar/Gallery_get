package com.sammy.sardorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.sammy.sardorapp.databinding.ActivityAddBinding
import com.sammy.sardorapp.db.MyDbHelper
import com.sammy.sardorapp.models.User
import java.io.File
import java.io.FileOutputStream


private const val TAG = "AddActivity"
class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    lateinit var absolutUri:String

    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        binding.addPhoto.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.apply {
            saveButton.setOnClickListener {

                if (nameEditText.text.toString().isEmpty()){
                    Toast.makeText(this@AddActivity, "Toldiring", Toast.LENGTH_SHORT).show()
                }else{
                    val user = User(image = absolutUri, name = nameEditText.text.toString())
                    myDbHelper.add(user)
                    startActivity(Intent(this@AddActivity,MainActivity::class.java))
                    finish()
                }

            }
        }





    }

    val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.addPhoto.setImageURI(uri)

            val inputStream = contentResolver.openInputStream(uri)
            val fileName = "image_${System.currentTimeMillis()}.jpg" // Генерируем уникальное имя файла
            val file = File(filesDir, fileName)
            val outputStream = FileOutputStream(file)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            inputStream?.close()
            outputStream.close()

            val absolutePath = file.absolutePath
            absolutUri = absolutePath
            Log.d(TAG, "File saved at: $absolutePath")
        }
    }
}