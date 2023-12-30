package com.iccangji.suitmediatest.ui.third_screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.iccangji.suitmediatest.R
import com.iccangji.suitmediatest.databinding.ActivityThirdScreenBinding
import com.iccangji.suitmediatest.ui.adapter.LoadingStateAdapter
import com.iccangji.suitmediatest.ui.adapter.UsersAdapter

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var adapter: UsersAdapter
    private lateinit var binding: ActivityThirdScreenBinding
    lateinit var username: String
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.extras?.getString(USERNAME).toString()

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.apply {
            btnUp.setOnClickListener {
                onBackPressed()
            }
        }

        adapter = UsersAdapter()
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
            Snackbar.make(binding.root, R.string.user_refresh, Snackbar.LENGTH_SHORT).show()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            binding.rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            adapter.addLoadStateListener { loadState ->
                if (loadState.append.endOfPaginationReached) {
                    if (adapter.itemCount < 1) {
                        tvError.isVisible = true
                        rvUser.isVisible = false
                    } else {
                        tvError.isVisible = false
                        rvUser.isVisible = true
                    }
                }
            }
            viewModel.listUser.observe(this@ThirdScreenActivity) {
                adapter.submitData(lifecycle, it)
                swipeRefresh.isRefreshing = false
                tvError.isVisible = false
            }
        }
    }

    companion object {
        const val USERNAME = "username"
    }
}