package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding
import io.github.muddz.styleabletoast.StyleableToast
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonBackSpace.setOnClickListener {
            backSpace()
        }

        binding.buttonModule.setOnClickListener {
            binding.input.text = addToInputText("%")
        }

        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }

        binding.button00.setOnClickListener {
            binding.input.text = addToInputText("00")
        }

        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }

        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }

        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }

        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }

        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }

        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }

        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }

        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }

        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }

        binding.buttonDot.setOnClickListener {
            binding.input.text = addToInputText(".")
        }

        binding.buttonAddition.setOnClickListener {
            binding.input.text = addToInputText("+")
        }

        binding.buttonSubtraction.setOnClickListener {
            binding.input.text = addToInputText("-")
        }

        binding.buttonMultiply.setOnClickListener {
            binding.input.text = addToInputText("×")
        }

        binding.buttonDivision.setOnClickListener {
            binding.input.text = addToInputText("÷")
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }

    }


    private fun addToInputText(buttonValue: String):String{
        return "${binding.input.text}$buttonValue"
    }

    private fun getInputExpression() : String {
        var expression = binding.input.text.replace(Regex("÷"),"/")
        expression = expression.replace(Regex("×"),"*")
        return expression
    }

    private fun showResult(){
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()){
                StyleableToast.makeText(this,"Invalid format used.",
                    R.style.customeToast).show()
            }
            else{
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        }
        catch (e: Exception){
            StyleableToast.makeText(this,"Invalid format used.",
                R.style.customeToast).show()
        }
    }

    private fun backSpace(){
        val length = binding.input.length()
        if (length > 0){
            binding.input.text = binding.input.text.subSequence(0,length -1)
        }
    }
}

