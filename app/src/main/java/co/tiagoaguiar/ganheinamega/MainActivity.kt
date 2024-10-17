package co.tiagoaguiar.ganheinamega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edt_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerator: Button = findViewById(R.id.btn_generate)

        btnGenerator.setOnClickListener{
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }
    }
    private fun numberGenerator(text: String, textResult: TextView){
        if (text.isNotEmpty()){
            val numberValidator = text.toInt()
            if (numberValidator in 6..15){

                val listNumber = mutableSetOf<Int>()
                val random = Random()

                while (true){
                    val number = random.nextInt(60)
                    listNumber.add(number + 1)

                    if (listNumber.size == numberValidator){
                        break
                    }
                }

                textResult.text = listNumber.joinToString(" - ")

            } else {
                Toast.makeText(this, "Valor não permitido. Informe um número entre 6 e 15!!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Não é permitido campo vazio", Toast.LENGTH_LONG).show()
        }
    }
}