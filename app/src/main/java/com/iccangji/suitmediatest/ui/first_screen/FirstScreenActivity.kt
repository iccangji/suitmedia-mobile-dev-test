package com.iccangji.suitmediatest.ui.first_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.iccangji.suitmediatest.R
import com.iccangji.suitmediatest.databinding.ActivityFirstScreenBinding
import com.iccangji.suitmediatest.ui.second_screen.SecondScreenActivity
import kotlinx.coroutines.launch

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    private val viewModel: FirstScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnNext.setOnClickListener {
                if(edNameInput.text.toString().isNotEmpty()){
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    intent.putExtra(SecondScreenActivity.USERNAME, binding.edNameInput.text.toString())
                    startActivity(intent)
                }
                else{
                    Snackbar.make(binding.root, getString(R.string.user_empty), Snackbar.LENGTH_SHORT).show()
                }
            }
            btnCheck.setOnClickListener {
                if(binding.edPalindromeInput.text.toString().isNotEmpty()){
                    viewModel.isPalindrome(binding.edPalindromeInput.text.toString())
                    lifecycleScope.launch {
                        viewModel.isPalindrome.collect{ palindrome ->
                            if(palindrome){
                                Snackbar.make(binding.root, getString(R.string.is_palindrome), Snackbar.LENGTH_SHORT).show()
                            }
                            else{
                                Snackbar.make(binding.root, getString(R.string.not_palindrome), Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else{
                    Snackbar.make(binding.root, getString(R.string.palindrome_empty), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}