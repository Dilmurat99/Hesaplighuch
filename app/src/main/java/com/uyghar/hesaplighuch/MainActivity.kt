package com.uyghar.hesaplighuch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.uyghar.hesaplighuch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var ekran: EditText? = null
    var txt_emel: TextView? = null
    var hatire = ""
    var san1 = 0.0
    var san2 = 0.0
    var netije = 0.0
    var emel = ""
    var ochur = false
    var button_text: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ekran = findViewById(R.id.ekran)
        txt_emel = findViewById(R.id.txt_emel)
    }

    fun bas(view: View) {
        var txt = (view as Button).text.toString()
        when(txt[0]) {
            in '0'..'9' -> {
                if ( ochur ) {
                    hatire = ""
                    ochur = false
                }
                hatire = hatire + txt
            }
            'A' -> hatire = ""
            '⌫' -> hatire = hatire.dropLast(1)
            '%' -> {
                san1 = hatire.toDouble()
                hatire = (san1/100).toString()
            }
            '+','-','×','÷' -> {
                if (button_text.equals(txt)) return
                if (emel!="") {
                    hesapla()
                    san1 = netije
                } else
                    if (!txt.equals("."))
                        san1 = hatire.toDouble()
                ochur = true
                emel = txt
            }
            '=' -> {
                hesapla()
                emel = ""
            }
            '.' -> {
                if (button_text.equals(txt)) return
                hatire = hatire + txt
            }
        }
        txt_emel?.setText(emel)
        ekran?.setText(hatire)
        button_text = txt

    }

    fun hesapla() {
        san2 = hatire.toDouble()
        when(emel) {
            "+" -> netije = san1 + san2
            "-" -> netije = san1 - san2
            "×" -> netije = san1 * san2
            "÷" -> netije = san1 / san2
        }
        hatire = netije.toString()
    }
}