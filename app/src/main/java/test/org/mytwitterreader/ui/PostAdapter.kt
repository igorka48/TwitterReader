package test.org.mytwitterreader.ui;

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_twitt.view.*
import test.org.mytwitterreader.R
import test.org.mytwitterreader.model.twitter.TwitterPost

class PostAdapter(private val callback: Callback) : PagedListAdapter<TwitterPost, PostAdapter.ViewHolder>(POST_COMPARATOR) {

    companion object {
        const val ITEM_LAYOUT = R.layout.cell_twitt
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<TwitterPost>() {
            override fun areContentsTheSame(oldItem: TwitterPost, newItem: TwitterPost): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: TwitterPost, newItem: TwitterPost): Boolean =
                    oldItem.id == newItem.id
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(ITEM_LAYOUT, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) as TwitterPost
        holder.itemView.setOnClickListener { callback.onItemClicked(item) }
        holder.itemView.contentText.text = item.text
        holder.itemView.userName.text = item.user?.name
        holder.itemView.dateLabel.text = item.createdAt
        Picasso.get()
                .load(item.user?.profileImageUrl)
                .into(holder.itemView.userImage)
    }


    interface Callback {
        fun onItemClicked(item: TwitterPost)
    }

}
