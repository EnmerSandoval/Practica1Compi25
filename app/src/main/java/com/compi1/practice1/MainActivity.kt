package com.compi1.practice1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.compi1.practice1.databinding.ActivityMainBinding
import com.compi1.practice1.errors.ErrorL
import com.compi1.practice1.lexer.Lexer
import com.compi1.practice1.lexer.LexerText
import com.compi1.practice1.parse.Parser
import com.compi1.practice1.parse.ParserCodigo
import com.compi1.practice1.strcuts.Instruction
import com.compi1.practice1.symbol.SymbolTable
import com.compi1.practice1.symbol.Tree
import java.io.BufferedReader
import java.io.StringReader
import java.util.LinkedList

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val inputButton = findViewById<Button>(R.id.textButton)
        val buttonCode = findViewById<Button>(R.id.codigoButton)
        val cont = findViewById<LinearLayout>(R.id.linearScroll)

        inputButton.setOnClickListener{
            val text = layoutInflater.inflate(R.layout.input_text, null);
            val generalText = text.findViewById<TextView>(R.id.generalText)
            generalText.setText("TEXTO")
            val edit = text.findViewById<EditText>(R.id.inputText)
            val play = text.findViewById<Button>(R.id.play)
            val textResponse2 = text.findViewById<TextView>(R.id.textViewResponse)

            cont.addView(text)

            play.setOnClickListener{
                val response = generalText.text.toString()
                val analyzer = playText(edit.toString())
                textResponse2.text = analyzer
            }

        }

        buttonCode.setOnClickListener{
            val text = layoutInflater.inflate(R.layout.input_text, null);
            val generalText = text.findViewById<TextView>(R.id.generalText)
            generalText.setText("CODIGO")
            val edit = text.findViewById<EditText>(R.id.inputText)
            val play = text.findViewById<Button>(R.id.play)
            val textResponse = text.findViewById<TextView>(R.id.textViewResponse)

            cont.addView(text)

            play.setOnClickListener{
                val response = generalText.text.toString();
                val analyzer = playCode(edit.toString())
                textResponse.text = analyzer
            }
        }


    }



    private fun playText(text: String): String {
        try {
            val lexer = LexerText(BufferedReader(StringReader(text)))
            val parse = ParserCodigo(lexer)
            val result = parse.parse()

            val ast = Tree(result.value as LinkedList<Instruction>)
            val table = SymbolTable()
            ast.setConsole("")
            val errors = mutableListOf<ErrorL>()
            errors.addAll(lexer.errorsLexers)
            errors.addAll(parse.getErrors())

            for(a in ast.instructions){
                if(a != null){
                    val res = a.interpretar(ast, table)
                    if(res is ErrorL){
                        errors.add(res)
                    }
                }
            }

            val finall = StringBuilder()
            finall.append(ast.console).append("\n")

            if(errors.isNotEmpty()){
                finall.append("Errors: ")
                for ( error in errors){
                    finall.append(error).append("\n")
                }
            }

            return finall.toString()


        } catch (ex: Exception){
            return ex.toString()
        }
    }

    private fun playCode(text: String): String {
        try {
            val text = "";
            val lexer = Lexer(BufferedReader(StringReader(text)))
            val parse = Parser(lexer)
            val result = parse.parse()

            val ast = Tree(result.value as LinkedList<Instruction>)
            val table = SymbolTable()
            ast.setConsole("")
            val errors = mutableListOf<ErrorL>()
            errors.addAll(lexer.errorsLexers)
            errors.addAll(parse.getErrors())

            for(a in ast.instructions){
                if(a != null){
                    val res = a.interpretar(ast, table)
                    if(res is ErrorL){
                        errors.add(res)
                    }
                }
            }

            val finall = StringBuilder()
            finall.append(ast.console).append("\n")

            if(errors.isNotEmpty()){
                finall.append("Errors: ")
                for ( error in errors){
                    finall.append(error).append("\n")
                }
            }

            return finall.toString();

        } catch (ex: Exception){
                return ex.toString()
        }
    }
}