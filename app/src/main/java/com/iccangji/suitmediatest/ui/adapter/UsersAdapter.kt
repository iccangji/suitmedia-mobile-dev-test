package com.iccangji.suitmediatest.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iccangji.suitmediatest.data.network.model.DataItem
import com.iccangji.suitmediatest.databinding.UsersItemBinding
import com.iccangji.suitmediatest.ui.second_screen.SecondScreenActivity
import com.iccangji.suitmediatest.ui.third_screen.ThirdScreenActivity


class UsersAdapter:
    PagingDataAdapter<DataItem, UsersAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.KEY, "${user.firstName} ${user.lastName}")
                intent.putExtra(SecondScreenActivity.USERNAME, ThirdScreenActivity.USERNAME)
                it.context.startActivity(intent)
            }
        }
    }

    class ViewHolder(private val binding: UsersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(user.avatar)
                    .into(ivUser)
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvEmail.text = user.email
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}