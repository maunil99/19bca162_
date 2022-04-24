package com.example.a19bca162

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        txtRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val db = DBHelper(this)
            var arr1 = ArrayList<User>()
            arr1 = db.getUser(edtUname.text.toString())

            if(arr1.size<0)
                Toast.makeText(this,"User Doesn't Exist or Incorrect Email",Toast.LENGTH_LONG).show()
            else
            {
                if(edtUname.text.toString().equals("") || edtpass.text.toString().equals("") )
                {
                    Toast.makeText(this,"Fill Data Accurately",Toast.LENGTH_LONG).show()
                }
                else if((edtUname.text.toString().equals(arr1[0].email)) && (edtpass.text.toString().equals(arr1[0].password)))
                {
                    val sp: SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
                    val prefedit = sp.edit()
                    prefedit.putString("UserName",edtUname.text.toString())
                    prefedit.commit()
                    val intent = Intent(this,HomePage::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"Incorrect Password",Toast.LENGTH_LONG).show()
            }
        }
    }
}