package com.judrummer.androidreadermonadplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //TODO: ui render
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Initialize -> {

                }
                is MainState.Loading -> {

                }
                is MainState.Success -> {

                }
                is MainState.Error -> {

                }
            }
        })
    }
}
