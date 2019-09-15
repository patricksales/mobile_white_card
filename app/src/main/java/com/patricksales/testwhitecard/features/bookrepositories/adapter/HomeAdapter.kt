package com.patricksales.testwhitecard.features.bookrepositories.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patricksales.testwhitecard.R
import com.patricksales.testwhitecard.core.util.Model
import com.patricksales.testwhitecard.features.bookrepositories.model.Item
import com.patricksales.testwhitecard.features.detailsbook.view.DetailBookActivity
import kotlinx.android.synthetic.main.book_item.view.*

class HomeAdapter(private var context: Context, private var list: List<Item>?) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, (list?.let { it[position] }))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, book: Item?) = with(itemView) {
            itemView.tvRepositoyName.text = book?.name
            itemView.tvRepositoryDescription.text = book?.description
            itemView.tvCountForks.text = book?.forksCount.toString()
            itemView.tvCountStars.text = book?.stargazersCount.toString()

            Glide
                .with(context)
                .load(book?.owner?.avatarUrl)
                .into(itemView.ivAuthorImage)

            itemView.contentLayout.setOnClickListener {
                startDetailBookActiviy(context, book)
            }
        }

        private fun startDetailBookActiviy(
            context: Context,
            item: Item?
        ) {
            val intent = Intent(context, DetailBookActivity::class.java)
            intent.putExtra(Model.KEY_BOOK_INTENT, item)
            context.startActivity(intent)
        }
    }
}