package com.rogrigamer.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rogrigamer.motivation.R
import com.rogrigamer.motivation.databinding.ActivityMainBinding
import com.rogrigamer.motivation.infra.MotivationConstants
import com.rogrigamer.motivation.infra.SecurityPreferences
import com.rogrigamer.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    private lateinit var mSecurutyPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageMorning.setOnClickListener(this)

        mSecurutyPreferences = SecurityPreferences(this)
        val name = mSecurutyPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        val textHelloName = "olá, $name!"
        binding.textName.text = textHelloName

        //Lógica inicial de seleção
        binding.imageAll.setColorFilter(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorAccent
            )
        )
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

        with(binding) {
            imageAll.setColorFilter(ContextCompat.getColor(applicationContext, R.color.white))
            imageHappy.setColorFilter(ContextCompat.getColor(applicationContext, R.color.white))
            imageMorning.setColorFilter(ContextCompat.getColor(applicationContext, R.color.white))
        }

        when (id) {
            R.id.imageAll -> {
                binding.imageAll.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorAccent
                    )
                )
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                binding.imageHappy.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorAccent
                    )
                )
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                binding.imageMorning.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorAccent
                    )
                )
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        binding.textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }
}