package com.patricksales.testwhitecard.features.detailsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patricksales.testwhitecard.R
import com.patricksales.testwhitecard.core.extensions.getDateFormatted
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import kotlinx.android.synthetic.main.item_repository.view.*

class DetailBooksAdapter(private var list: List<PullRepository>?) :
    RecyclerView.Adapter<DetailBooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind((list?.let { it[position] }))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: PullRepository?) = with(itemView) {
            itemView.tvTitlePR.text = book?.title
            itemView.tvBodyPR.text = book?.body

            itemView.tvAuthorName.text = book?.user?.login
            itemView.tvDataPR.text = book?.created_at?.let { it.getDateFormatted() }
        }
    }
}