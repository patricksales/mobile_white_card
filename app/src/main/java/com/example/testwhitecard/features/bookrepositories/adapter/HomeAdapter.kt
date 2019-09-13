package com.example.testwhitecard.features.bookrepositories.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwhitecard.R
import com.example.testwhitecard.core.util.Model
import com.example.testwhitecard.features.bookrepositories.model.Item
import com.example.testwhitecard.features.bookrepositories.view.MainActivity
import com.example.testwhitecard.features.detailsbook.view.DetailBookActivity
import kotlinx.android.synthetic.main.book_item.view.*

class HomeAdapter(private var context: Context, private var list: List<Item>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, book: Item) = with(itemView) {
            itemView.tvRepositoyName.text = book.name
            itemView.tvRepositoryDescription.text = book.description
            //itemView.rvContactsItemUsername.text = user.username

            Glide
                .with(context)
                .load(book.owner.avatarUrl)
                .into(itemView.ivAuthorImage)

            itemView.contentLayout.setOnClickListener {
                startPaymentActivityForResult(context, book)
            }
        }

        private fun startPaymentActivityForResult(
            context: Context,
            item: Item
        ) {
            val intent = Intent(context, DetailBookActivity::class.java)
            intent.putExtra(Model.KEY_BOOK_INTENT, item)
            context.startActivity(intent)
        }
    }
}