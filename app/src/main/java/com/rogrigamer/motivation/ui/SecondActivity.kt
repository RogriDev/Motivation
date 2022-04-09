package com.rogrigamer.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rogrigamer.motivation.R
import com.rogrigamer.motivation.databinding.ActivitySecondBinding
import com.rogrigamer.motivation.infra.MotivationConstants
import com.rogrigamer.motivation.infra.SecurityPreferences

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySecondBinding

    private lateinit var msecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        msecurityPreferences = SecurityPreferences(this)

        binding.buttonSave.setOnClickListener(this)

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
        val name = binding.editName.text.toString()

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