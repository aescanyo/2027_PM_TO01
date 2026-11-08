package dam.pmdm.pmdm_to1 // Usa el paquete exacto de tu proyecto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Enlazar variables con los componentes del XML
        val etInput = findViewById<EditText>(R.id.etInputNumber)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // 2. Evento al pulsar el botón
        btnCalculate.setOnClickListener {
            val textoEntrada = etInput.text.toString().trim()

            // Control de error: si el campo está vacío
            if (textoEntrada.isEmpty()) {
                tvResult.text = "⚠️ Error: Debes introducir un número."
                return@setOnClickListener // Detiene la ejecución aquí de forma segura
            }

            // Control de error mediante try-catch
            try {
                // Convertimos el texto a número
                val numero = textoEntrada.toInt()

                // Validamos que sea mayor que cero
                if (numero <= 0) {
                    tvResult.text = "⚠️ Error: Introduce un número positivo mayor a 0."
                } else {
                    // Si todo es correcto, calculamos los primos
                    val listaPrimos = obtenerPrimosMenoresA(numero)

                    if (listaPrimos.isEmpty()) {
                        tvResult.text = "No hay primos menores a $numero."
                    } else {
                        tvResult.text = "Primos menores a $numero:\n\n" + listaPrimos.joinToString(", ")
                    }
                }

            } catch (e: NumberFormatException) {
                // Captura el error si introducen letras o un número gigante
                tvResult.text = "⚠️ Error: El dato introducido no es un número válido."
            }
        }
    }

    // Método que recorre todos los números desde 2 hasta n-1
    private fun obtenerPrimosMenoresA(n: Int): List<Int> {
        val primos = mutableListOf<Int>()

        for (i in 2 until n) {
            if (esPrimo(i)) {
                primos.add(i)
            }
        }

        return primos
    }

    // Método para comprobar si un solo número es primo
    private fun esPrimo(numero: Int): Boolean {
        if (numero < 2) return false

        for (i in 2 until numero) {
            if (numero % i == 0) {
                return false // Si es divisible por otro número, no es primo
            }
        }

        return true // Si no encontró ningún divisor, es primo
    }
}