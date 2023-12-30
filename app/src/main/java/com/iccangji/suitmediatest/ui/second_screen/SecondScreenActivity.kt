package com.iccangji.suitmediatest.ui.second_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iccangji.suitmediatest.R
import com.iccangji.suitmediatest.databinding.ActivitySecondScreenBinding
import com.iccangji.suitmediatest.ui.third_screen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val username = intent.extras?.getString(USERNAME)
        binding.apply {
            tvName.text = username
            tvSelected.text = intent.extras?.getString(KEY) ?: getString(R.string.selected_user_name)
            btnChooseUser.setOnClickListener {
                intent.putExtra(ThirdScreenActivity.USERNAME, username)
                startActivity(
                    Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                )
            }
            btnUp.setOnClickListener {
                onBackPressed()
            }
        }
    }

    companion object {
        const val USERNAME = "username"
        const val KEY = "key"
    }
}