package com.judrummer.androidreadermonadplayground

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //TODO: move to app context
        Paper.init(this)

        btnLogin.setOnClickListener {
            viewModel.login(etUsername.text.toString(), etPassword.text.toString())
        }

        //TODO: implment better ui render
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Initialize -> {

                }
                is MainState.Loading -> {

                }
                is MainState.Success -> {
                    Toast.makeText(this, "Login success ${state.userProfile}", Toast.LENGTH_SHORT).show()
                }
                is MainState.Error -> {
                    Toast.makeText(this, "Login error ${state.throwable}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
