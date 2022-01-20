package com.rogrigamer.motivation.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.rogrigamer.motivation.R
import com.rogrigamer.motivation.infra.MotivationConstants
import com.rogrigamer.motivation.infra.SecurityPreferences
import com.rogrigamer.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL
    private var textName: TextView? = null
    private var buttonNewPhrase: AppCompatButton? = null
    private var imageAll: ImageView? = null
    private var imageHappy: ImageView? = null
    private var imageMorning: ImageView? = null
    private var textPhrase: TextView? = null

    private lateinit var mSecurutyPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textName = findViewById(R.id.textName)
        buttonNewPhrase = findViewById(R.id.buttonNewPhrase)
        imageAll = findViewById(R.id.imageAll)
        imageHappy = findViewById(R.id.imageHappy)
        imageMorning = findViewById(R.id.imageMorning)
        textPhrase = findViewById(R.id.textPhrase)

        buttonNewPhrase?.setOnClickListener(this)
        imageAll?.setOnClickListener(this)
        imageHappy?.setOnClickListener(this)
        imageMorning?.setOnClickListener(this)


        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSecurutyPreferences = SecurityPreferences(this)
        val name = mSecurutyPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName?.text = "olá, $name!"

        //Lógica inicial de seleção
        imageAll?.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()
    }


    override fun onClick(view: View) {
        val id = view.id
        val lisFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in lisFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {

        imageAll?.setColorFilter(resources.getColor(R.color.white))
        imageHappy?.setColorFilter(resources.getColor(R.color.white))
        imageMorning?.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll?.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy?.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning?.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }


    }

    private fun handleNewPhrase() {
        textPhrase?.text = Mock().getPhrase(mPhraseFilter)
    }
}