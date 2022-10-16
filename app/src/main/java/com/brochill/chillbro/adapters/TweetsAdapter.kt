package com.brochill.chillbro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brochill.chillbro.databinding.CardTweetBinding
import com.brochill.chillbro.mvm.TweetsModel

class TweetsAdapter(private val listener: AdapterItemClickListener): ListAdapter<TweetsModel, TweetsAdapter.TweetsViewHolder>(TweetsDC()){

    inner class TweetsViewHolder(private val binding: CardTweetBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(tweet: TweetsModel){
            binding.tweet = tweet
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsViewHolder {
        return TweetsViewHolder(CardTweetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TweetsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface AdapterItemClickListener{
        fun onTweetClick(titleModel: TweetsModel, position: Int)
    }

}

class TweetsDC : DiffUtil.ItemCallback<TweetsModel>() {

    override fun areItemsTheSame(
        oldItem: TweetsModel,
        newItem: TweetsModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TweetsModel,
        newItem: TweetsModel
    ): Boolean {
        return oldItem == newItem
    }
}

