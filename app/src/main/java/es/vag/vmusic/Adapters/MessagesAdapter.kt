package es.vag.vmusic.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.vag.vmusic.Models.Message
import es.vag.vmusic.R


class MessagesAdapter(private val context: Context,
                      private val items: MutableList<Message>

) :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvTitle: TextView = view.findViewById(R.id.text_view_sender_name)
        private val tvContent: TextView = view.findViewById(R.id.text_view_message_content)

        fun bindItem(item: Message){
            tvTitle.text = item.userId
            tvContent.text = item.content
        }
    }
}