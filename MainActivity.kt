package com.govind8061.sevenminutesexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.govind8061.sevenminutesexercise.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts:TextToSpeech?=null
    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        tts = TextToSpeech(this,this)

        binding?.btnSpeak?.setOnClickListener { view->
            speakOut("Hello Ashok Kashyap")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts!=null){
            tts?.stop()
            tts?.shutdown()
        }
        binding=null
    }
    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int) {
        if (status==TextToSpeech.SUCCESS){
            val result=tts!!.setLanguage(Locale.ENGLISH)
            if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this@MainActivity,"Language Missing",Toast.LENGTH_SHORT).show()
            }
        }
    }
}