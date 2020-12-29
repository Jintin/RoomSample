package com.jintin.roomsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jintin.roomsample.databinding.AdapterUserBinding


typealias OnSelectListener<T> = (T) -> Unit

class UserAdapter(
    private val onSelectListener: OnSelectListener<User>,
    private val onDeleteListener: OnSelectListener<User>,
) :
    ListAdapter<User, UserAdapter.ViewHolder>(DiffCheck) {

    class ViewHolder(
        private val binding: AdapterUserBinding,
        private val onSelectListener: OnSelectListener<User>,
        private val onDeleteListener: OnSelectListener<User>,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.root.setOnClickListener {
                onSelectListener.invoke(user.copy(age = user.age + 1))
            }
            binding.delete.setOnClickListener {
                onDeleteListener.invoke(user)
            }
            binding.age.text = user.age.toString()
            binding.name.text = user.name
        }
    }

    object DiffCheck : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            AdapterUserBinding.inflate(layoutInflater, parent, false),
            onSelectListener, onDeleteListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}