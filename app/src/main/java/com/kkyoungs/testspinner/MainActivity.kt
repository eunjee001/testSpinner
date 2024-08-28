package com.kkyoungs.testspinner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val optionSpinner : Spinner = findViewById(R.id.optionSpinner)
        val option = resources.getStringArray(R.array.option_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, option)
        val decreaseButton: Button = findViewById(R.id.button_decrease)
        val increaseButton: Button = findViewById(R.id.button_increase)
        val quantityText: EditText = findViewById(R.id.quantity_text)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        optionSpinner.adapter = adapter

        // 초기 수량 설정
        quantityText.setText(quantity.toString())
        // 감소 버튼 클릭 시
        decreaseButton.setOnClickListener {
            if (quantity > 1) {  // 수량이 1 이상일 때만 감소
                quantity--
                quantityText.setText(quantity.toString())
            }
        }

        // 증가 버튼 클릭 시
        increaseButton.setOnClickListener {
            quantity++
            quantityText.setText(quantity.toString())
        }

        // 수량 직접 입력 시
        quantityText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().toIntOrNull()
                if (input != null && input >= 1) {
                    quantity = input
                } else {
                    quantityText.setText("1")
                    quantity = 1
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}