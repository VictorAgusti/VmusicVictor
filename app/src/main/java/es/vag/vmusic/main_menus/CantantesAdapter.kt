
package es.vag.vmusic.main_menus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.vag.vmusic.R

class CantantesAdapter(private val cantantes: MutableList<Cantante>, private val context: Context?, private  val mListener: (Cantante) -> Unit):
    RecyclerView.Adapter<CantantesAdapter.CantantesViewHolder>(){

    override fun onBindViewHolder(holder: CantantesViewHolder, position: Int){
        val item = cantantes[position]
        holder.bindItem(item, context)
        holder.itemView.setOnClickListener{mListener(item)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CantantesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_cantantes_layout,parent,false)
        return CantantesViewHolder(view)
    }


    override fun getItemCount(): Int{
        return cantantes.size
    }

    class CantantesViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvName: TextView = view.findViewById(R.id.nombre_cantante)
        private val imagen: ImageView= view.findViewById(R.id.logo_Cantante)

        fun bindItem(cantante: Cantante, context: Context?){
            imagen.setImageResource(cantante.imageid)
            tvName.text = cantante.name

        }
    }
}