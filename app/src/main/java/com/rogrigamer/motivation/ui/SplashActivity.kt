package com.rogrigamer.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.rogrigamer.motivation.R
import com.rogrigamer.motivation.infra.MotivationConstants
import com.rogrigamer.motivation.infra.SecurityPreferences

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var msecurityPreferences: SecurityPreferences

    private var buttonSave: AppCompatButton? = null
    private var editName: EditText? = null
    private var textToStart: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        msecurityPreferences = SecurityPreferences(this)

        buttonSave = findViewById(R.id.buttonSave)
        editName = findViewById(R.id.editName)
        textToStart = findViewById(R.id.textToStart)

        //to hide supportActionBar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave?.setOnClickListener(this)

        verifyName()

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("", "")
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun verifyName() {
        val name = msecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editName?.text.toString()

        if (name != "") {
            msecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)  //referencia da MainActivity
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }

    }
}