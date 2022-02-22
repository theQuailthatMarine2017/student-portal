package com.example.atlast

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button_login)

        btn.setOnClickListener {
            btn.text = "Loading.."
            loginUser()
        }

    }

    private fun loginUser() {

        val url = "http://192.168.100.16:3000/login/portal"
        val stringRequest: StringRequest = object : StringRequest( Method.POST, url,
            Response.Listener { response ->

                    val jsonObject = JSONObject(response)
                    val student = jsonObject.getString("STUDENT_FULL_NAMES")
                    if(student != null) {
                        setContentView(R.layout.verify)
                    }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                val mobile = findViewById<EditText>(R.id.editTextPhone) as EditText

                //Change with your post params
                params["mobile"] = mobile.text.toString()
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun verify(){

        val code = 12345

    }
    
}