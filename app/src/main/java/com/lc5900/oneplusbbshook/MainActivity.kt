package com.lc5900.oneplusbbshook

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.android.zgj.utils.MultiprocessSharedPreferences


class MainActivity : AppCompatActivity() {
    @BindView(R.id.tailET)
    lateinit  var tailET : EditText
    @BindView(R.id.resetBtn)
    lateinit var resetBtn:Button
    @BindView(R.id.clearBtn)
    lateinit var clearBtn:Button
    @BindView(R.id.saveBtn)
    lateinit var saveBtn:Button
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        sharedPreferences= MultiprocessSharedPreferences.getSharedPreferences(
            this,
            "OnePlusBBS",
            Context.MODE_PRIVATE
        )
        var tail = sharedPreferences.getString("tail", this.getString(R.string.suffix_from_app))
        tailET.setText(tail)
    }
    @OnClick(R.id.saveBtn)
    fun save(){
        sharedPreferences.edit().putString("tail", tailET.text.toString()).commit()
        Toast.makeText(this,getString(R.string.saveSuccess),Toast.LENGTH_LONG).show()
    }
    @OnClick(R.id.clearBtn)
    fun clear(){
        tailET.setText("")
    }
    @OnClick(R.id.resetBtn)
    fun reset(){
       tailET.setText(this.getString(R.string.suffix_from_app))
    }

}