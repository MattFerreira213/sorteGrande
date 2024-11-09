package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random



class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edt_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerator: Button = findViewById(R.id.btn_generate)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("resultId", null)

        // Utilizado para validar se um dados está nulo
        result?.let {
            txtResult.text = "Ultima aposta: $it"
        }

        btnGenerator.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }
    }

    private fun numberGenerator(text: String, textResult: TextView) {
        if (text.isEmpty()) {
            Toast.makeText(this, "Não é permitido campo vazio", Toast.LENGTH_SHORT).show()
            return
        }

        val numberValidator = text.toInt()
        if (numberValidator < 6 || numberValidator > 15) {
            Toast.makeText(
                this,
                "Valor não permitido. Informe um número entre 6 e 15!!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val listNumber = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val number = random.nextInt(60)
            listNumber.add(number + 1)

            if (listNumber.size == numberValidator) {
                break
            }
        }
        textResult.text = listNumber.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("resultId", textResult.text.toString())
        editor.apply()
    }
}